package com.quranreader.aijavadevstalha.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.quranreader.aijavadevstalha.data.local.QuranPreloadData
import com.quranreader.aijavadevstalha.data.model.Ayah
import com.quranreader.aijavadevstalha.data.model.Surah
import com.quranreader.aijavadevstalha.ui.util.BengaliTransliterationHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class QuranRepository(private val context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("quran_reader_prefs", Context.MODE_PRIVATE)

    private val memoryAyahsCache = mutableMapOf<Int, List<Ayah>>()

    fun getAllSurahs(): List<Surah> {
        return QuranPreloadData.surahs
    }

    fun getSurahByNumber(number: Int): Surah? {
        return QuranPreloadData.surahs.find { it.number == number }
    }

    suspend fun getAyahsForSurah(surahNumber: Int): List<Ayah> = withContext(Dispatchers.IO) {
        // Check in-memory cache
        memoryAyahsCache[surahNumber]?.let { return@withContext it }

        // Check preloaded dataset
        val preloaded = QuranPreloadData.preloadedAyahs[surahNumber]
        if (preloaded != null && preloaded.isNotEmpty()) {
            val bookmarkedSet = getBookmarkedKeys()
            val list = preloaded.map { ayah ->
                val isBookmarked = bookmarkedSet.contains("${ayah.surahNumber}_${ayah.ayahNumber}")
                val pron = if (ayah.textPronunciation.isNotBlank()) {
                    ayah.textPronunciation
                } else {
                    BengaliTransliterationHelper.getPronunciation(ayah.textArabic)
                }
                ayah.copy(
                    isBookmarked = isBookmarked,
                    textPronunciation = pron
                )
            }
            memoryAyahsCache[surahNumber] = list
            return@withContext list
        }

        // Otherwise fetch dynamically from Quran API (Arabic + Bengali translation editions)
        try {
            val fetchedAyahs = fetchFromApi(surahNumber)
            if (fetchedAyahs.isNotEmpty()) {
                val bookmarkedSet = getBookmarkedKeys()
                val list = fetchedAyahs.map { ayah ->
                    val isBookmarked = bookmarkedSet.contains("${ayah.surahNumber}_${ayah.ayahNumber}")
                    val pron = BengaliTransliterationHelper.getPronunciation(ayah.textArabic, ayah.textPronunciation)
                    ayah.copy(
                        isBookmarked = isBookmarked,
                        textPronunciation = pron
                    )
                }
                memoryAyahsCache[surahNumber] = list
                return@withContext list
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Fallback: Generate template Ayahs for this surah with transliteration
        val surah = getSurahByNumber(surahNumber)
        val total = surah?.totalAyahs ?: 7
        val bookmarkedSet = getBookmarkedKeys()
        val generatedList = (1..total).map { aNum ->
            val sPad = String.format("%03d", surahNumber)
            val aPad = String.format("%03d", aNum)
            val isBm = bookmarkedSet.contains("${surahNumber}_$aNum")
            Ayah(
                surahNumber = surahNumber,
                ayahNumber = aNum,
                textArabic = if (aNum == 1) "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ" else "آية $aNum من سورة ${surah?.nameArabic ?: ""}",
                textPronunciation = if (aNum == 1) "বিসমিল্লাহির রাহমানির রাহীম" else "আয়াত $aNum - ${surah?.nameBengali ?: ""}",
                textBengali = "${surah?.nameBengali ?: "সুরা"} এর $aNum নম্বর আয়াত।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$sPad$aPad.mp3",
                isBookmarked = isBm
            )
        }
        memoryAyahsCache[surahNumber] = generatedList
        return@withContext generatedList
    }

    private fun fetchFromApi(surahNumber: Int): List<Ayah> {
        val result = mutableListOf<Ayah>()
        // Fetch Arabic and Bengali translations via Alquran cloud API
        // Editions: quran-uthmani (Arabic) and bn.bengali (Bengali Muhiuddin Khan translation)
        val urlString = "https://api.alquran.cloud/v1/surah/$surahNumber/editions/quran-uthmani,bn.bengali"
        val url = URL(urlString)
        val conn = url.openConnection() as HttpURLConnection
        conn.connectTimeout = 8000
        conn.readTimeout = 8000
        conn.requestMethod = "GET"

        if (conn.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(InputStreamReader(conn.inputStream))
            val sb = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            reader.close()

            val json = JSONObject(sb.toString())
            val dataArray = json.getJSONArray("data")
            if (dataArray.length() >= 2) {
                val arabicData = dataArray.getJSONObject(0)
                val bengaliData = dataArray.getJSONObject(1)

                val arabicAyahs = arabicData.getJSONArray("ayahs")
                val bengaliAyahs = bengaliData.getJSONArray("ayahs")

                val count = minOf(arabicAyahs.length(), bengaliAyahs.length())
                val sPad = String.format("%03d", surahNumber)

                for (i in 0 until count) {
                    val aObj = arabicAyahs.getJSONObject(i)
                    val bObj = bengaliAyahs.getJSONObject(i)

                    val aNum = aObj.getInt("numberInSurah")
                    val arText = aObj.getString("text")
                    val bnText = bObj.getString("text")
                    val aPad = String.format("%03d", aNum)
                    val audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$sPad$aPad.mp3"

                    // Compute precise Bengali pronunciation for this Arabic verse
                    val pronText = BengaliTransliterationHelper.getPronunciation(arText)

                    result.add(
                        Ayah(
                            surahNumber = surahNumber,
                            ayahNumber = aNum,
                            textArabic = arText,
                            textPronunciation = pronText,
                            textBengali = bnText,
                            audioUrl = audioUrl,
                            isBookmarked = false
                        )
                    )
                }
            }
        }
        return result
    }

    // Bookmarks management
    fun getBookmarkedKeys(): Set<String> {
        return prefs.getStringSet("bookmarked_ayahs", emptySet()) ?: emptySet()
    }

    fun toggleBookmark(surahNumber: Int, ayahNumber: Int): Boolean {
        val keys = getBookmarkedKeys().toMutableSet()
        val key = "${surahNumber}_$ayahNumber"
        val isAdded: Boolean
        if (keys.contains(key)) {
            keys.remove(key)
            isAdded = false
        } else {
            keys.add(key)
            isAdded = true
        }
        prefs.edit().putStringSet("bookmarked_ayahs", keys).apply()

        // Update cache if present
        memoryAyahsCache[surahNumber]?.let { list ->
            val updated = list.map {
                if (it.ayahNumber == ayahNumber) it.copy(isBookmarked = isAdded) else it
            }
            memoryAyahsCache[surahNumber] = updated
        }
        return isAdded
    }

    fun getAllBookmarks(): List<Ayah> {
        val keys = getBookmarkedKeys()
        val result = mutableListOf<Ayah>()
        for (key in keys) {
            val parts = key.split("_")
            if (parts.size == 2) {
                val sNum = parts[0].toIntOrNull() ?: continue
                val aNum = parts[1].toIntOrNull() ?: continue

                // Check preload
                val cached = memoryAyahsCache[sNum]?.find { it.ayahNumber == aNum }
                    ?: QuranPreloadData.preloadedAyahs[sNum]?.find { it.ayahNumber == aNum }

                if (cached != null) {
                    val pron = if (cached.textPronunciation.isNotBlank()) cached.textPronunciation
                    else BengaliTransliterationHelper.getPronunciation(cached.textArabic)
                    result.add(cached.copy(isBookmarked = true, textPronunciation = pron))
                } else {
                    val surah = getSurahByNumber(sNum)
                    val sPad = String.format("%03d", sNum)
                    val aPad = String.format("%03d", aNum)
                    result.add(
                        Ayah(
                            surahNumber = sNum,
                            ayahNumber = aNum,
                            textArabic = "آية $aNum من سورة ${surah?.nameArabic ?: ""}",
                            textPronunciation = "আয়াত $aNum - ${surah?.nameBengali ?: ""}",
                            textBengali = "${surah?.nameBengali ?: "সুরা"} এর $aNum নম্বর আয়াত।",
                            audioUrl = "https://everyayah.com/data/Alafasy_128kbps/$sPad$aPad.mp3",
                            isBookmarked = true
                        )
                    )
                }
            }
        }
        return result.sortedWith(compareBy({ it.surahNumber }, { it.ayahNumber }))
    }

    // Last Read Surah & Ayah
    fun saveLastRead(surahNumber: Int, ayahNumber: Int) {
        prefs.edit()
            .putInt("last_read_surah", surahNumber)
            .putInt("last_read_ayah", ayahNumber)
            .apply()
    }

    fun getLastRead(): Pair<Int, Int> {
        val s = prefs.getInt("last_read_surah", 1)
        val a = prefs.getInt("last_read_ayah", 1)
        return Pair(s, a)
    }

    // Settings
    fun isPronunciationEnabled(): Boolean {
        return prefs.getBoolean("show_pronunciation", true)
    }

    fun setPronunciationEnabled(enabled: Boolean) {
        prefs.edit().putBoolean("show_pronunciation", enabled).apply()
    }

    fun getArabicFontSize(): Float {
        return prefs.getFloat("font_size_arabic", 26f)
    }

    fun setArabicFontSize(size: Float) {
        prefs.edit().putFloat("font_size_arabic", size).apply()
    }

    fun getBengaliFontSize(): Float {
        return prefs.getFloat("font_size_bengali", 16f)
    }

    fun setBengaliFontSize(size: Float) {
        prefs.edit().putFloat("font_size_bengali", size).apply()
    }
}
