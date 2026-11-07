package com.quranreader.aijavadevstalha.ui.viewmodel

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.quranreader.aijavadevstalha.data.model.Ayah
import com.quranreader.aijavadevstalha.data.model.Surah
import com.quranreader.aijavadevstalha.data.repository.QuranRepository
import com.quranreader.aijavadevstalha.ui.util.AudioPlaybackState
import com.quranreader.aijavadevstalha.ui.util.AudioPlayerManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class MainTab {
    SURAHS,
    BOOKMARKS,
    SETTINGS
}

enum class RevelationFilter {
    ALL,
    MAKKI,
    MADANI
}

data class QuranUiState(
    val currentTab: MainTab = MainTab.SURAHS,
    val surahs: List<Surah> = emptyList(),
    val filteredSurahs: List<Surah> = emptyList(),
    val searchQuery: String = "",
    val revelationFilter: RevelationFilter = RevelationFilter.ALL,
    val selectedSurah: Surah? = null,
    val currentAyahs: List<Ayah> = emptyList(),
    val isAyahsLoading: Boolean = false,
    val bookmarks: List<Ayah> = emptyList(),
    val lastReadSurahNumber: Int = 1,
    val lastReadAyahNumber: Int = 1,
    val showPronunciation: Boolean = true,
    val arabicFontSize: Float = 26f,
    val bengaliFontSize: Float = 16f
)

class QuranViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = QuranRepository(application)
    private val audioManager = AudioPlayerManager(application)

    private val _uiState = MutableStateFlow(QuranUiState())
    val uiState: StateFlow<QuranUiState> = _uiState.asStateFlow()

    val playbackState: StateFlow<AudioPlaybackState> = audioManager.playbackState

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        val allSurahs = repository.getAllSurahs()
        val (lastS, lastA) = repository.getLastRead()
        _uiState.value = _uiState.value.copy(
            surahs = allSurahs,
            filteredSurahs = allSurahs,
            lastReadSurahNumber = lastS,
            lastReadAyahNumber = lastA,
            showPronunciation = repository.isPronunciationEnabled(),
            arabicFontSize = repository.getArabicFontSize(),
            bengaliFontSize = repository.getBengaliFontSize(),
            bookmarks = repository.getAllBookmarks()
        )
    }

    fun selectTab(tab: MainTab) {
        if (tab == MainTab.BOOKMARKS) {
            refreshBookmarks()
        }
        _uiState.value = _uiState.value.copy(currentTab = tab)
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        applyFilters()
    }

    fun setRevelationFilter(filter: RevelationFilter) {
        _uiState.value = _uiState.value.copy(revelationFilter = filter)
        applyFilters()
    }

    private fun applyFilters() {
        val query = _uiState.value.searchQuery.trim().lowercase()
        val filter = _uiState.value.revelationFilter

        val filtered = _uiState.value.surahs.filter { surah ->
            val matchesFilter = when (filter) {
                RevelationFilter.ALL -> true
                RevelationFilter.MAKKI -> surah.revelationType == "মাক্কী"
                RevelationFilter.MADANI -> surah.revelationType == "মাদানী"
            }

            val matchesQuery = if (query.isEmpty()) {
                true
            } else {
                surah.number.toString().contains(query) ||
                    surah.nameArabic.contains(query) ||
                    surah.nameEnglish.lowercase().contains(query) ||
                    surah.nameBengali.contains(query) ||
                    surah.meaningBengali.contains(query)
            }

            matchesFilter && matchesQuery
        }
        _uiState.value = _uiState.value.copy(filteredSurahs = filtered)
    }

    fun openSurah(surah: Surah, targetAyah: Int = 1) {
        _uiState.value = _uiState.value.copy(
            selectedSurah = surah,
            isAyahsLoading = true,
            currentAyahs = emptyList()
        )
        repository.saveLastRead(surah.number, targetAyah)
        _uiState.value = _uiState.value.copy(
            lastReadSurahNumber = surah.number,
            lastReadAyahNumber = targetAyah
        )

        viewModelScope.launch {
            val ayahs = repository.getAyahsForSurah(surah.number)
            _uiState.value = _uiState.value.copy(
                currentAyahs = ayahs,
                isAyahsLoading = false
            )
        }
    }

    fun closeSurahDetail() {
        _uiState.value = _uiState.value.copy(selectedSurah = null)
        audioManager.stop()
    }

    fun toggleBookmark(ayah: Ayah) {
        val isAdded = repository.toggleBookmark(ayah.surahNumber, ayah.ayahNumber)
        val updatedAyahs = _uiState.value.currentAyahs.map {
            if (it.surahNumber == ayah.surahNumber && it.ayahNumber == ayah.ayahNumber) {
                it.copy(isBookmarked = isAdded)
            } else it
        }
        _uiState.value = _uiState.value.copy(
            currentAyahs = updatedAyahs,
            bookmarks = repository.getAllBookmarks()
        )
        val msg = if (isAdded) "বুকমার্কে যোগ করা হয়েছে" else "বুকমার্ক থেকে সরানো হয়েছে"
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
    }

    fun refreshBookmarks() {
        _uiState.value = _uiState.value.copy(bookmarks = repository.getAllBookmarks())
    }

    // Audio Playback
    fun playAyahAudio(ayah: Ayah, surahName: String) {
        val sPad = String.format("%03d", ayah.surahNumber)
        val aPad = String.format("%03d", ayah.ayahNumber)
        val audioUrl = ayah.audioUrl ?: "https://everyayah.com/data/Alafasy_128kbps/$sPad$aPad.mp3"

        audioManager.playAyah(
            surahNumber = ayah.surahNumber,
            ayahNumber = ayah.ayahNumber,
            audioUrl = audioUrl,
            surahName = surahName
        ) {
            // Play next ayah if available in current surah
            val currentList = _uiState.value.currentAyahs
            val nextAyah = currentList.find { it.ayahNumber == ayah.ayahNumber + 1 }
            if (nextAyah != null) {
                playAyahAudio(nextAyah, surahName)
            }
        }
    }

    fun pauseAudio() {
        audioManager.pause()
    }

    fun resumeAudio() {
        audioManager.resume()
    }

    fun stopAudio() {
        audioManager.stop()
    }

    // Copy to clipboard with Bangla pronunciation and Bangla meaning
    fun copyAyahToClipboard(ayah: Ayah, surahName: String) {
        val clipboard = getApplication<Application>().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val text = buildString {
            append("$surahName [আয়াত: ${ayah.ayahNumber}]\n\n")
            append("${ayah.textArabic}\n\n")
            if (ayah.textPronunciation.isNotBlank()) {
                append("উচ্চারণ:\n${ayah.textPronunciation}\n\n")
            }
            append("অর্থ:\n${ayah.textBengali}")
        }
        val clip = ClipData.newPlainText("Quran Ayah", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(getApplication(), "আয়াত ক্লিপবোর্ডে কপি করা হয়েছে", Toast.LENGTH_SHORT).show()
    }

    // Share Ayah
    fun shareAyah(ayah: Ayah, surahName: String) {
        val shareText = buildString {
            append("$surahName [আয়াত: ${ayah.ayahNumber}]\n\n")
            append("${ayah.textArabic}\n\n")
            if (ayah.textPronunciation.isNotBlank()) {
                append("উচ্চারণ:\n${ayah.textPronunciation}\n\n")
            }
            append("অর্থ:\n${ayah.textBengali}\n\n")
            append("-- Quran Reader App")
        }
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val chooser = Intent.createChooser(intent, "আয়াত শেয়ার করুন").apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        getApplication<Application>().startActivity(chooser)
    }

    // Settings actions
    fun togglePronunciation(enabled: Boolean) {
        repository.setPronunciationEnabled(enabled)
        _uiState.value = _uiState.value.copy(showPronunciation = enabled)
    }

    fun updateArabicFontSize(size: Float) {
        repository.setArabicFontSize(size)
        _uiState.value = _uiState.value.copy(arabicFontSize = size)
    }

    fun updateBengaliFontSize(size: Float) {
        repository.setBengaliFontSize(size)
        _uiState.value = _uiState.value.copy(bengaliFontSize = size)
    }

    override fun onCleared() {
        super.onCleared()
        audioManager.release()
    }
}
