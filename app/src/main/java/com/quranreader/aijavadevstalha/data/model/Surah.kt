package com.quranreader.aijavadevstalha.data.model

data class Surah(
    val number: Int,
    val nameArabic: String,
    val nameEnglish: String,
    val nameBengali: String,
    val meaningBengali: String,
    val revelationType: String, // "মাক্কী" or "মাদানী"
    val totalAyahs: Int
)
