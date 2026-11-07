package com.quranreader.aijavadevstalha.ui.util

object BengaliTransliterationHelper {

    private val commonPhrases = mapOf(
        "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ" to "বিসমিল্লাহির রাহমানির রাহীম",
        "بِسْمِ ٱللَّهِ ٱلرَّحْمَٰنِ ٱلرَّحِيمِ" to "বিসমিল্লাহির রাহমানির রাহীম",
        "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ" to "আলহামদু লিল্লাহি রাব্বিল আলামীন",
        "ٱلْحَمْدُ لِلَّهِ رَبِّ ٱلْعَٰلَمِينَ" to "আলহামদু লিল্লাহি রাব্বিল আলামীন",
        "الرَّحْمَٰنِ الرَّحِيمِ" to "আর-রাহমানির রাহীম",
        "ٱلرَّحْمَٰنِ ٱلرَّحِيمِ" to "আর-রাহমানির রাহীম",
        "مَالِكِ يَوْمِ الدِّينِ" to "মালিকি ইয়াওমিদ দ্বীন",
        "مَٰلِكِ يَوْمِ ٱلدِّينِ" to "মালিকি ইয়াওমিদ দ্বীন",
        "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ" to "ইয়্যাকা না'বুদু ওয়া ইয়্যাকা নাস্তাঈন",
        "ٱهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ" to "ইহদিনাস সিরাতাল মুস্তাকীম",
        "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ" to "ইহদিনাস সিরাতাল মুস্তাকীম",
        "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ" to "সিরাতাল্লাযীনা আন'আমতা আলাইহিম",
        "غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ" to "গাইরিল মাগদূবি আলাইহিম ওয়ালাদ-দোয়াল্লীন",
        "قُلْ هُوَ اللَّهُ أَحَدٌ" to "কুল হুয়াল্লাহু আহাদ",
        "اللَّهُ الصَّمَدُ" to "আল্লাহুস সামাদ",
        "لَمْ يَلِدْ وَلَمْ يُولَدْ" to "লাম ইয়ালিদ ওয়া লাম ইউলাদ",
        "وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ" to "ওয়া লাম ইয়াকুল্লাহু কুফুওয়ান আহাদ",
        "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ" to "কুল আউযু বিরাব্বিল ফালাক",
        "مِنْ شَرِّ مَا خَلَقَ" to "মিন শাররি মা খালাক",
        "وَمِنْ شَرِّ غَاسِقٍ إِذَا وَقَبَ" to "ওয়া মিন শাররি গাসিকিন ইযা ওয়াকাব",
        "وَمِنْ شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ" to "ওয়া মিন শাররিন নাফ্ফাছা-তি ফিল উকাদ",
        "وَمِنْ شَرِّ حَاسِدٍ إِذَا حَسَدَ" to "ওয়া মিন শাররি হাসিদিন ইযা হাসাদ",
        "قُلْ أَعُوذُ بِرَبِّ النَّاسِ" to "কুল আউযু বিরাব্বিন নাস",
        "مَلِكِ النَّاسِ" to "মালিকিন নাস",
        "إِلَٰهِ النَّاسِ" to "ইলাহিন নাস",
        "مِنْ شَرِّ الْوَسْوَاسِ الْخَنَّاسِ" to "মিন শাররিল ওয়াসওয়াসিল খান্নাস",
        "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ" to "আল্লাযী ইউওয়াসবিসু ফী ছুদুরিন নাস",
        "مِنَ الْجِنَّةِ وَالنَّاسِ" to "মিনাল জিন্নাতি ওয়ান নাস"
    )

    private val consonantMap = mapOf(
        'ء' to "আ", 'آ' to "আ", 'أ' to "আ", 'إ' to "ই", 'ا' to "আ", 'ٱ' to "",
        'ب' to "ব", 'ت' to "ত", 'ث' to "ছ", 'ج' to "জ", 'ح' to "হ", 'خ' to "খ",
        'د' to "দ", 'ذ' to "য", 'ر' to "র", 'ز' to "য", 'س' to "স", 'ش' to "শ",
        'ص' to "স", 'ض' to "দ", 'ط' to "ত", 'ظ' to "য", 'ع' to "'আ", 'غ' to "গ",
        'ف' to "ফ", 'ق' to "ক", 'ك' to "ক", 'ل' to "ল", 'م' to "ম", 'ن' to "ন",
        'ه' to "হ", 'و' to "ও", 'ي' to "ই", 'ى' to "আ", 'ة' to "ত", 'ئ' to "ই", 'ؤ' to "উ"
    )

    fun getPronunciation(arabicText: String, fallback: String = ""): String {
        if (fallback.isNotBlank()) return fallback

        val trimmed = arabicText.trim()
        commonPhrases[trimmed]?.let { return it }

        // Clean diacritics check
        val normalized = trimmed
            .replace("ٱ", "ا")
            .replace("ـ", "")

        commonPhrases[normalized]?.let { return it }

        return transliterateArabicToBengali(trimmed)
    }

    fun transliterateArabicToBengali(arabicText: String): String {
        if (arabicText.isBlank()) return ""

        val words = arabicText.split(Regex("\\s+"))
        val result = StringBuilder()

        for ((index, word) in words.withIndex()) {
            commonPhrases[word]?.let {
                result.append(it)
                if (index < words.size - 1) result.append(" ")
                return@let
            } ?: run {
                result.append(transliterateWord(word))
                if (index < words.size - 1) result.append(" ")
            }
        }

        return result.toString().trim()
    }

    private fun transliterateWord(word: String): String {
        val clean = StringBuilder()
        var i = 0
        val len = word.length

        while (i < len) {
            val ch = word[i]
            val next = if (i + 1 < len) word[i + 1] else null
            val next2 = if (i + 2 < len) word[i + 2] else null

            // Handle special Quranic combinations
            when {
                // Alif-Lam prefix
                i == 0 && (ch == 'ا' || ch == 'ٱ') && next == 'ل' -> {
                    clean.append("আল-")
                    i += 2
                    continue
                }
                // Fathatan, Kasratan, Dammatan
                ch == '\u064B' -> { // Fathatan
                    clean.append("আন")
                    i++
                    continue
                }
                ch == '\u064D' -> { // Kasratan
                    clean.append("ইন")
                    i++
                    continue
                }
                ch == '\u064C' -> { // Dammatan
                    clean.append("উন")
                    i++
                    continue
                }
                ch == '\u064E' -> { // Fatha
                    clean.append("া")
                    i++
                    continue
                }
                ch == '\u0650' -> { // Kasra
                    clean.append("ি")
                    i++
                    continue
                }
                ch == '\u064F' -> { // Damma
                    clean.append("ু")
                    i++
                    continue
                }
                ch == '\u0651' -> { // Shaddah
                    // Double the previous consonant if possible
                    i++
                    continue
                }
                ch == '\u0652' -> { // Sukun
                    clean.append("্")
                    i++
                    continue
                }
                ch == '\u0670' -> { // Dagger Alif
                    clean.append("া")
                    i++
                    continue
                }
                consonantMap.containsKey(ch) -> {
                    clean.append(consonantMap[ch])
                }
                ch.isWhitespace() -> {
                    clean.append(' ')
                }
            }
            i++
        }

        val rendered = clean.toString()
            .replace("্া", "")
            .replace("াা", "া")
            .replace("িি", "ী")
            .replace("ুু", "ূ")
            .replace("্্", "্")

        return if (rendered.isNotBlank()) rendered else word
    }
}
