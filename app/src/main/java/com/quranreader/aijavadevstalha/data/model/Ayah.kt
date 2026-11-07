package com.quranreader.aijavadevstalha.data.model

data class Ayah(
    val surahNumber: Int,
    val ayahNumber: Int,
    val textArabic: String,
    val textPronunciation: String, // বাংলা উচ্চারণ (Bangla pronunciation)
    val textBengali: String,       // বাংলা অর্থ (Bangla meaning)
    val audioUrl: String? = null,
    val isBookmarked: Boolean = false
)
