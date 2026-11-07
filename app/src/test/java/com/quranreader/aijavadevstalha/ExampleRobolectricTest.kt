package com.quranreader.aijavadevstalha

import com.quranreader.aijavadevstalha.data.local.QuranPreloadData
import com.quranreader.aijavadevstalha.ui.util.BengaliTransliterationHelper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun testBengaliTransliteration() {
        val bismillah = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ"
        val pron = BengaliTransliterationHelper.getPronunciation(bismillah)
        assertEquals("বিসমিল্লাহির রাহমানির রাহীম", pron)

        val ikhlas = "قُلْ هُوَ اللَّهُ أَحَدٌ"
        val pronIkhlas = BengaliTransliterationHelper.getPronunciation(ikhlas)
        assertEquals("কুল হুয়াল্লাহু আহাদ", pronIkhlas)
    }

    @Test
    fun testSurahListCount() {
        val surahs = QuranPreloadData.surahs
        assertEquals(114, surahs.size)
        assertEquals("Al-Fatihah", surahs.first().nameEnglish)
        assertEquals("An-Nas", surahs.last().nameEnglish)
    }

    @Test
    fun testPreloadedAyahsHavePronunciationAndMeaning() {
        val fatihahAyahs = QuranPreloadData.preloadedAyahs[1]
        assertNotNull(fatihahAyahs)
        assertEquals(7, fatihahAyahs?.size)

        fatihahAyahs?.forEach { ayah ->
            assertTrue("Ayah ${ayah.ayahNumber} Arabic text should not be blank", ayah.textArabic.isNotBlank())
            assertTrue("Ayah ${ayah.ayahNumber} Pronunciation should not be blank", ayah.textPronunciation.isNotBlank())
            assertTrue("Ayah ${ayah.ayahNumber} Translation should not be blank", ayah.textBengali.isNotBlank())
        }
    }
}
