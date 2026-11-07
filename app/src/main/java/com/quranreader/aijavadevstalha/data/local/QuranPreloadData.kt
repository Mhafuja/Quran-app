package com.quranreader.aijavadevstalha.data.local

import com.quranreader.aijavadevstalha.data.model.Ayah
import com.quranreader.aijavadevstalha.data.model.Surah

object QuranPreloadData {

    val surahs: List<Surah> = listOf(
        Surah(1, "الفاتحة", "Al-Fatihah", "আল-ফাতিহা", "সূচনা", "মাক্কী", 7),
        Surah(2, "البقرة", "Al-Baqarah", "আল-বাক্বারাহ", "গাভী", "মাদানী", 286),
        Surah(3, "آل عمران", "Ali 'Imran", "আলে ইমরান", "ইমরানের পরিবার", "মাদানী", 200),
        Surah(4, "النساء", "An-Nisa", "আন-নিসা", "নারী", "মাদানী", 176),
        Surah(5, "المائدة", "Al-Ma'idah", "আল-মায়িদাহ", "খাদ্য পরিবেশিত টেবিল", "মাদানী", 120),
        Surah(6, "الأنعام", "Al-An'am", "আল-আনআম", "গৃহপালিত পশু", "মাক্কী", 165),
        Surah(7, "الأعراف", "Al-A'raf", "আল-আরাফ", "উঁচু স্থান", "মাক্কী", 206),
        Surah(8, "الأنفال", "Al-Anfal", "আল-আনফাল", "যুদ্ধলব্ধ ধন-সম্পদ", "মাদানী", 75),
        Surah(9, "التوبة", "At-Tawbah", "আত-তাওবাহ", "অনুশোচনা", "মাদানী", 129),
        Surah(10, "يونس", "Yunus", "ইউনুস", "ইউনুস (আঃ)", "মাক্কী", 109),
        Surah(11, "هود", "Hud", "হূদ", "হূদ (আঃ)", "মাক্কী", 123),
        Surah(12, "يوسف", "Yusuf", "ইউসুফ", "ইউসুফ (আঃ)", "মাক্কী", 111),
        Surah(13, "الرعد", "Ar-Ra'd", "আর-রাদ", "বজ্রপাত", "মাদানী", 43),
        Surah(14, "إبراهيم", "Ibrahim", "ইবরাহীম", "ইবরাহীম (আঃ)", "মাক্কী", 52),
        Surah(15, "الحجر", "Al-Hijr", "আল-হিজর", "পাথুরে পাহাড়", "মাক্কী", 99),
        Surah(16, "النحل", "An-Nahl", "আন-নাহল", "মৌমাছি", "মাক্কী", 128),
        Surah(17, "الإسراء", "Al-Isra", "আল-ইসরা", "নৈশভ্রমণ", "মাক্কী", 111),
        Surah(18, "الكهف", "Al-Kahf", "আল-কাহফ", "গুহা", "মাক্কী", 110),
        Surah(19, "مريم", "Maryam", "মারইয়াম", "মারইয়াম (আঃ)", "মাক্কী", 98),
        Surah(20, "طه", "Taha", "ত্বোয়া-হা", "ত্বোয়া-হা", "মাক্কী", 135),
        Surah(21, "الأنبياء", "Al-Anbiya", "আল-আম্বিয়া", "নবীগণ", "মাক্কী", 112),
        Surah(22, "الحج", "Al-Hajj", "আল-হাজ্জ", "হজ্জ", "মাদানী", 78),
        Surah(23, "المؤمنون", "Al-Mu'minun", "আল-মুমিনূন", "বিশ্বাসীগণ", "মাক্কী", 118),
        Surah(24, "النور", "An-Nur", "আন-নূর", "আলো", "মাদানী", 64),
        Surah(25, "الفرقان", "Al-Furqan", "আল-ফুরকান", "মানদণ্ড", "মাক্কী", 77),
        Surah(26, "الشعراء", "Ash-Shu'ara", "আশ-শুআরা", "কবিগণ", "মাক্কী", 227),
        Surah(27, "النمل", "An-Naml", "আন-নামল", "পিঁপড়া", "মাক্কী", 93),
        Surah(28, "القصص", "Al-Qasas", "আল-ক্বাসাস", "কাহিনি", "মাক্কী", 88),
        Surah(29, "العنكبوت", "Al-'Ankabut", "আল-আনকাবূত", "মাকড়সা", "মাক্কী", 69),
        Surah(30, "الروم", "Ar-Rum", "আর-রূম", "রোমীয়রা", "মাক্কী", 60),
        Surah(31, "لقمان", "Luqman", "লুক্বমান", "লুকমান", "মাক্কী", 34),
        Surah(32, "السجدة", "As-Sajdah", "আস-সাজদাহ", "সিজদা", "মাক্কী", 30),
        Surah(33, "الأحزاب", "Al-Ahzab", "আল-আহযাব", "জোট", "মাদানী", 73),
        Surah(34, "سبإ", "Saba", "সাবা", "সাবা", "মাক্কী", 54),
        Surah(35, "فاطر", "Fatir", "ফাতির", "সৃষ্টিকর্তা", "মাক্কী", 45),
        Surah(36, "يس", "Yasin", "ইয়াসীন", "ইয়াসীন", "মাক্কী", 83),
        Surah(37, "الصافات", "As-Saffat", "আস-সাফফাত", "সারিবদ্ধ দল", "মাক্কী", 182),
        Surah(38, "ص", "Sad", "সোয়াদ", "সোয়াদ", "মাক্কী", 88),
        Surah(39, "الزمر", "Az-Zumar", "আয-যুমার", "দলসমূহ", "মাক্কী", 75),
        Surah(40, "غافر", "Ghafir", "গাফির", "ক্ষমাশীল", "মাক্কী", 85),
        Surah(41, "فصلت", "Fussilat", "ফুসসিলাত", "বিশদ বিবরণ", "মাক্কী", 54),
        Surah(42, "الشورى", "Ash-Shura", "আশ-শূরা", "পরামর্শ", "মাক্কী", 53),
        Surah(43, "الزخرف", "Az-Zukhruf", "আয-যুখরুফ", "স্বর্ণালঙ্কার", "মাক্কী", 89),
        Surah(44, "الدخان", "Ad-Dukhan", "আদ-দুখান", "ধোঁয়া", "মাক্কী", 59),
        Surah(45, "الجاثية", "Al-Jathiyah", "আল-জাসিয়াহ", "নতজানু", "মাক্কী", 37),
        Surah(46, "الأحقاف", "Al-Ahqaf", "আল-আহকাফ", "বালিয়াড়ি", "মাক্কী", 35),
        Surah(47, "محمد", "Muhammad", "মুহাম্মদ", "মুহাম্মদ (সাঃ)", "মাদানী", 38),
        Surah(48, "الفتح", "Al-Fath", "আল-ফাতহ", "বিজয়", "মাদানী", 29),
        Surah(49, "الحجرات", "Al-Hujurat", "আল-হুজুরাত", "বাসগৃহসমূহ", "মাদানী", 18),
        Surah(50, "ق", "Qaf", "ক্বাফ", "ক্বাফ", "মাক্কী", 45),
        Surah(51, "الذاريات", "Adh-Dhariyat", "আয-যারিয়াত", "বিক্ষেপকারী বাতাস", "মাক্কী", 60),
        Surah(52, "الطور", "At-Tur", "আত-তূর", "তূর পাহাড়", "মাক্কী", 49),
        Surah(53, "النجم", "An-Najm", "আন-নাজম", "তারা", "মাক্কী", 62),
        Surah(54, "القمر", "Al-Qamar", "আল-ক্বামার", "চাঁদ", "মাক্কী", 55),
        Surah(55, "الرحمن", "Ar-Rahman", "আর-রাহমান", "পরম দয়ালু", "মাদানী", 78),
        Surah(56, "الواقعة", "Al-Waqi'ah", "আল-ওয়াকিয়াহ", "মহাঘটনা", "মাক্কী", 96),
        Surah(57, "الحديد", "Al-Hadid", "আল-হাদীদ", "লোহা", "মাদানী", 29),
        Surah(58, "المجادلة", "Al-Mujadila", "আল-মুজাদালাহ", "বিতর্ককারিণী", "মাদানী", 22),
        Surah(59, "الحشر", "Al-Hashr", "আল-হাশর", "নির্বাসন", "মাদানী", 24),
        Surah(60, "الممتحنة", "Al-Mumtahanah", "আল-মুমতাহিনাহ", "পরীক্ষিতা নারী", "মাদানী", 13),
        Surah(61, "الصف", "As-Saff", "আস-সফ", "সারিবদ্ধ সৈন্য", "মাদানী", 14),
        Surah(62, "الجمعة", "Al-Jumu'ah", "আল-জুমুআহ", "শুক্রবার", "মাদানী", 11),
        Surah(63, "المنافقون", "Al-Munafiqun", "আল-মুনাফিকূন", "কপট বিশ্বাসীগণ", "মাদানী", 11),
        Surah(64, "التغابن", "At-Taghabun", "আত-তাগাবুন", "ক্ষতি ও লাভ", "মাদানী", 18),
        Surah(65, "الطلاق", "At-Talaq", "আত-ত্বালাক্ব", "তালাক", "মাদানী", 12),
        Surah(66, "التحريم", "At-Tahrim", "আত-তাহরীম", "নিষিদ্ধকরণ", "মাদানী", 12),
        Surah(67, "الملك", "Al-Mulk", "আল-মুলক", "সার্বভৌম কর্তৃত্ব", "মাক্কী", 30),
        Surah(68, "القلم", "Al-Qalam", "আল-কলম", "কলম", "মাক্কী", 52),
        Surah(69, "الحاقة", "Al-Haqqah", "আল-হাক্বক্বাহ", "নিশ্চিত সত্য", "মাক্কী", 52),
        Surah(70, "المعارج", "Al-Ma'arij", "আল-মাআরিজ", "উন্নয়নের সোপান", "মাক্কী", 44),
        Surah(71, "نوح", "Nuh", "নূহ", "নূহ (আঃ)", "মাক্কী", 28),
        Surah(72, "الجن", "Al-Jinn", "আল-জ্বিন", "জ্বিন জাতি", "মাক্কী", 28),
        Surah(73, "المزمل", "Al-Muzzammil", "আল-মুযযাম্মিল", "বস্ত্রাবৃত", "মাক্কী", 20),
        Surah(74, "المدثر", "Al-Muddathir", "আল-মুদ্দাসসির", "পোশাক পরিহিত", "মাক্কী", 56),
        Surah(75, "القيامة", "Al-Qiyamah", "আল-ক্বিয়ামাহ", "পুনরুত্থান", "মাক্কী", 40),
        Surah(76, "الإنسان", "Al-Insan", "আল-ইনসান", "মানবজাতি", "মাদানী", 31),
        Surah(77, "المرسلات", "Al-Mursalat", "আল-মুরসালাত", "প্রেরিত বাতাস", "মাক্কী", 50),
        Surah(78, "النبإ", "An-Naba", "আন-নাবা", "মহাসংবাদ", "মাক্কী", 40),
        Surah(79, "النازعات", "An-Nazi'at", "আন-নাযিআত", "উৎপাটনকারী", "মাক্কী", 46),
        Surah(80, "عبس", "Abasa", "আবাসা", "ভ্রূকুটি করল", "মাক্কী", 42),
        Surah(81, "التكوير", "At-Takwir", "আত-তাকভীর", "সূর্য অন্ধকারাচ্ছন্ন", "মাক্কী", 29),
        Surah(82, "الانفطار", "Al-Infitar", "আল-ইনফিতার", "বিদীর্ণ হওয়া", "মাক্কী", 19),
        Surah(83, "المطففين", "Al-Mutaffifin", "আল-মুতাফফিফীন", "প্রতারকগণ", "মাক্কী", 36),
        Surah(84, "الانشقاق", "Al-Inshiqaq", "আল-ইনশিক্বাক্ব", "খন্ড-বিখন্ড হওয়া", "মাক্কী", 25),
        Surah(85, "البروج", "Al-Buruj", "আল-বুরূজ", "নক্ষত্রমণ্ডল", "মাক্কী", 22),
        Surah(86, "الطارق", "At-Tariq", "আত-ত্বারিক্ব", "রাতের আগমনকারী", "মাক্কী", 17),
        Surah(87, "الأعلى", "Al-A'la", "আল-আ'লা", "সর্বোচ্চ", "মাক্কী", 19),
        Surah(88, "الغاشية", "Al-Ghashiyah", "আল-গাশিয়াহ", "সর্বগ্রাসী সংকট", "মাক্কী", 26),
        Surah(89, "الفجر", "Al-Fajr", "আল-ফজর", "ভোরবেলা", "মাক্কী", 30),
        Surah(90, "البلد", "Al-Balad", "আল-বালাদ", "নগরী", "মাক্কী", 20),
        Surah(91, "الشمس", "Ash-Shams", "আশ-শামস", "সূর্য", "মাক্কী", 15),
        Surah(92, "الليل", "Al-Layl", "আল-লাইল", "রাত", "মাক্কী", 21),
        Surah(93, "الضحى", "Ad-Duha", "আদ-দুহা", "পূর্বাহ্ণ", "মাক্কী", 11),
        Surah(94, "الشرح", "Ash-Sharh", "আল-ইনশিরাহ", "বক্ষ প্রশস্তকরণ", "মাক্কী", 8),
        Surah(95, "التين", "At-Tin", "আত-তীন", "ডুমুর", "মাক্কী", 8),
        Surah(96, "العلق", "Al-'Alaq", "আল-আলাক্ব", "রক্তপিণ্ড", "মাক্কী", 19),
        Surah(97, "القدر", "Al-Qadr", "আল-ক্বদর", "মহিমান্বিত রাত", "মাক্কী", 5),
        Surah(98, "البينة", "Al-Bayyinah", "আল-বাইয়্যিনাহ", "সুস্পষ্ট প্রমাণ", "মাদানী", 8),
        Surah(99, "الزلزلة", "Az-Zalzalah", "আয-যালযালাহ", "ভূমিকম্প", "মাদানী", 8),
        Surah(100, "العاديات", "Al-'Adiyat", "আল-আদিয়াত", "অভিযানকারী ঘোড়া", "মাক্কী", 11),
        Surah(101, "القارعة", "Al-Qari'ah", "আল-কারিয়াহ", "মহা বিপর্যয়", "মাক্কী", 11),
        Surah(102, "التكاثر", "At-Takathur", "আত-তাকাসুর", "প্রাচুর্যের প্রতিযোগিতা", "মাক্কী", 8),
        Surah(103, "العصر", "Al-'Asr", "আল-আসর", "মহাকাল", "মাক্কী", 3),
        Surah(104, "الهمزة", "Al-Humazah", "আল-হুমাযাহ", "পরনিন্দুক", "মাক্কী", 9),
        Surah(105, "الفيل", "Al-Fil", "আল-ফীল", "হাতি", "মাক্কী", 5),
        Surah(106, "قريش", "Quraysh", "কুরাইশ", "কুরাইশ বংশ", "মাক্কী", 4),
        Surah(107, "الماعون", "Al-Ma'un", "আল-মাউন", "নিত্যব্যবহার্য জিনিস", "মাক্কী", 7),
        Surah(108, "الكوثر", "Al-Kawthar", "আল-কাউসার", "প্রচুর প্রাচুর্য", "মাক্কী", 3),
        Surah(109, "الكافرون", "Al-Kafirun", "আল-কাফিরূন", "অবিশ্বাসীগণ", "মাক্কী", 6),
        Surah(110, "النصر", "An-Nasr", "আন-নাসর", "সাহায্য ও বিজয়", "মাদানী", 3),
        Surah(111, "المسد", "Al-Masad", "আল-মাসাদ", "খেজুরের রশি", "মাক্কী", 5),
        Surah(112, "الإخلاص", "Al-Ikhlas", "আল-ইখলাস", "একত্ববাদ", "মাক্কী", 4),
        Surah(113, "الفلق", "Al-Falaq", "আল-ফালাক্ব", "নিশিভোর", "মাক্কী", 5),
        Surah(114, "الناس", "An-Nas", "আন-নাস", "মানবজাতি", "মাক্কী", 6)
    )

    // Preloaded full Ayahs for core Surahs with verified Bengali pronunciation and translation
    val preloadedAyahs: Map<Int, List<Ayah>> = mapOf(
        // 1. Al-Fatihah (7 Ayahs)
        1 to listOf(
            Ayah(
                surahNumber = 1,
                ayahNumber = 1,
                textArabic = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                textPronunciation = "বিসমিল্লাহির রাহমানির রাহীম",
                textBengali = "পরম করুণাময় ও অসীম দয়ালু আল্লাহর নামে শুরু করছি।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001001.mp3"
            ),
            Ayah(
                surahNumber = 1,
                ayahNumber = 2,
                textArabic = "الْحَمْدُ لِلَّهِ رَبِّ الْعَالَمِينَ",
                textPronunciation = "আলহামদু লিল্লাহি রাব্বিল আলামীন",
                textBengali = "যাবতীয় প্রশংসা শুধুমাত্র মহান আল্লাহ্‌ তাআলার জন্য, যিনি সমগ্র বিশ্বজগতের প্রতিপালক।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001002.mp3"
            ),
            Ayah(
                surahNumber = 1,
                ayahNumber = 3,
                textArabic = "الرَّحْمَٰنِ الرَّحِيمِ",
                textPronunciation = "আর-রাহমানির রাহীম",
                textBengali = "যিনি পরম করুণাময় ও পরম দয়ালু।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001003.mp3"
            ),
            Ayah(
                surahNumber = 1,
                ayahNumber = 4,
                textArabic = "مَالِكِ يَوْمِ الدِّينِ",
                textPronunciation = "মালিকি ইয়াওমিদ দ্বীন",
                textBengali = "যিনি বিচার দিবসের একমাত্র মালিক ও অধিপতি।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001004.mp3"
            ),
            Ayah(
                surahNumber = 1,
                ayahNumber = 5,
                textArabic = "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
                textPronunciation = "ইয়্যাকা না'বুদু ওয়া ইয়্যাকা নাস্তাঈন",
                textBengali = "আমরা শুধুমাত্র আপনারই ইবাদত করি এবং কেবলমাত্র আপনারই কাছে সাহায্য প্রার্থনা করি।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001005.mp3"
            ),
            Ayah(
                surahNumber = 1,
                ayahNumber = 6,
                textArabic = "اهْدِنَا الصِّرَاطَ الْمُسْتَقِيمَ",
                textPronunciation = "ইহদিনাস সিরাতাল মুস্তাকীম",
                textBengali = "আমাদেরকে সরল-সঠিক পথ প্রদর্শন করুন।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001006.mp3"
            ),
            Ayah(
                surahNumber = 1,
                ayahNumber = 7,
                textArabic = "صِرَاطَ الَّذِينَ أَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوبِ عَلَيْهِمْ وَلَا الضَّالِّينَ",
                textPronunciation = "সিরাতাল্লাযীনা আন'আমতা আলাইহিম, গাইরিল মাগদূবি আলাইহিম ওয়ালাদ-দোয়াল্লীন",
                textBengali = "সেসব মানুষের পথ, যাদেরকে আপনি পুরস্কৃত করেছেন; তাদের পথ নয় যাদের ওপর আপনার ক্রোধ পতিত হয়েছে এবং যারা পথভ্রষ্ট হয়েছে।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/001007.mp3"
            )
        ),

        // 103. Al-Asr (3 Ayahs)
        103 to listOf(
            Ayah(
                surahNumber = 103,
                ayahNumber = 1,
                textArabic = "وَالْعَصْرِ",
                textPronunciation = "ওয়াল 'আসর",
                textBengali = "মহাকালের শপথ!",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/103001.mp3"
            ),
            Ayah(
                surahNumber = 103,
                ayahNumber = 2,
                textArabic = "إِنَّ الْإِنْسَانَ لَفِي خُسْرٍ",
                textPronunciation = "ইন্নাল ইনসানা লাফী খুসর",
                textBengali = "নিশ্চয় মানুষ চরম ক্ষতির মধ্যে নিমজ্জিত রয়েছে।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/103002.mp3"
            ),
            Ayah(
                surahNumber = 103,
                ayahNumber = 3,
                textArabic = "إِلَّا الَّذِينَ آمَنُوا وَعَمِلُوا الصَّالِحَاتِ وَتَوَاصَوْا بِالْحَقِّ وَتَوَاصَوْا بِالصَّبْرِ",
                textPronunciation = "ইল্লাল্লাযীনা আমানূ ওয়া আমিলুস সালিহাতি ওয়া তাওয়া সাও বিল হাক্কি ওয়া তাওয়া সাও বিস সাবর",
                textBengali = "তারা ব্যতীত, যারা ঈমান এনেছে, সৎকর্ম করেছে এবং একে অপরকে সত্য ও ধৈর্যের উপদেশ দিয়েছে।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/103003.mp3"
            )
        ),

        // 108. Al-Kawthar (3 Ayahs)
        108 to listOf(
            Ayah(
                surahNumber = 108,
                ayahNumber = 1,
                textArabic = "إِنَّا أَعْطَيْنَاكَ الْكَوْثَرَ",
                textPronunciation = "ইন্না আ'তাইনা কাল কাওসার",
                textBengali = "নিশ্চয় আমি আপনাকে কাউসার (প্রচুর কল্যাণ ও সুসংবাদ) দান করেছি।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/108001.mp3"
            ),
            Ayah(
                surahNumber = 108,
                ayahNumber = 2,
                textArabic = "فَصَلِّ لِرَبِّكَ وَانْحَرْ",
                textPronunciation = "ফাসাল্লি লিরাব্বিকা ওয়ানহার",
                textBengali = "অতএব আপনার প্রতিপালকের উদ্দেশ্যে সালাত আদায় করুন এবং কুরবানী করুন।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/108002.mp3"
            ),
            Ayah(
                surahNumber = 108,
                ayahNumber = 3,
                textArabic = "إِنَّ شَانِئَكَ هُوَ الْأَبْتَرُ",
                textPronunciation = "ইন্না শা-নি'আকা হুয়াল আবতার",
                textBengali = "নিশ্চয় আপনার শত্রুই নির্বংশ, নামনিশানাহীন।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/108003.mp3"
            )
        ),

        // 109. Al-Kafirun (6 Ayahs)
        109 to listOf(
            Ayah(109, 1, "قُلْ يَا أَيُّهَا الْكَافِرُونَ", "কুল ইয়া আইয়্যূহাল কা-ফিরূন", "বলুন, হে অবিশ্বাসী কাফেররা!", "https://everyayah.com/data/Alafasy_128kbps/109001.mp3"),
            Ayah(109, 2, "لَا أَعْبُدُ مَا تَعْبُدُونَ", "লা আ'বুদু মা তা'বুদূন", "আমি তাদের ইবাদত করি না যাদের ইবাদত তোমরা করো,", "https://everyayah.com/data/Alafasy_128kbps/109002.mp3"),
            Ayah(109, 3, "وَلَا أَنْتُمْ عَابِدُونَ مَا أَعْبُدُ", "ওয়ালা আনতুম 'আ-বিদূনা মা আ'বুদ", "এবং তোমরাও তাঁর ইবাদতকারী নও যাঁর ইবাদত আমি করি,", "https://everyayah.com/data/Alafasy_128kbps/109003.mp3"),
            Ayah(109, 4, "وَلَا أَنَا عَابِدٌ مَا عَبَدْتُمْ", "ওয়ালা আনা 'আ-বিদুম মা 'আবাদতুম", "আর আমি কখনো তাদের উপাসনা করব না যাদের উপাসনা তোমরা করে এসেছ,", "https://everyayah.com/data/Alafasy_128kbps/109004.mp3"),
            Ayah(109, 5, "وَلَا أَنْتُمْ عَابِدُونَ مَا أَعْبُدُ", "ওয়ালা আনতুম 'আ-বিদূনা মা আ'বুদ", "এবং তোমরাও তাঁর ইবাদতকারী নও যাঁর ইবাদত আমি করি।", "https://everyayah.com/data/Alafasy_128kbps/109005.mp3"),
            Ayah(109, 6, "لَكُمْ دِينُكُمْ وَلِيَ دِينِ", "লাকুম দীনুকুম ওয়ালিয়া দীন", "তোমাদের দীন তোমাদের জন্য, আর আমার দীন আমার জন্য।", "https://everyayah.com/data/Alafasy_128kbps/109006.mp3")
        ),

        // 110. An-Nasr (3 Ayahs)
        110 to listOf(
            Ayah(110, 1, "إِذَا جَاءَ نَصْرُ اللَّهِ وَالْفَتْحُ", "ইযা জা-আ নাসরুল্লাহি ওয়াল ফাতহ", "যখন আল্লাহর সাহায্য ও বিজয় এসে পৌঁছবে,", "https://everyayah.com/data/Alafasy_128kbps/110001.mp3"),
            Ayah(110, 2, "وَرَأَيْتَ النَّاسَ يَدْخُلُونَ فِي دِينِ اللَّهِ أَفْوَاجًا", "ওয়া রাআইতান নাসা ইয়াদখুলূনা ফী দীনিল্লাহি আফওয়াজা", "এবং আপনি মানুষদের দলে দলে আল্লাহর দীনে প্রবেশ করতে দেখবেন,", "https://everyayah.com/data/Alafasy_128kbps/110002.mp3"),
            Ayah(110, 3, "فَسَبِّحْ بِحَمْدِ رَبِّكَ وَاسْتَغْفِرْهُ ۚ إِنَّهُ كَانَ تَوَّابًا", "ফাসাব্বিহ বিহামদি রাব্বিকা ওয়াস্তাগফিরহ, ইন্নাহু কা-না তাওওয়াবা", "তখন আপনি আপনার প্রতিপালকের প্রশংসার সাথে তাসবীহ পাঠ করুন এবং তাঁর কাছে ক্ষমা প্রার্থনা করুন; নিশ্চয় তিনি তওবা কবুলকারী।", "https://everyayah.com/data/Alafasy_128kbps/110003.mp3")
        ),

        // 111. Al-Masad (5 Ayahs)
        111 to listOf(
            Ayah(111, 1, "تَبَّتْ يَدَا أَبِي لَهَبٍ وَتَبَّ", "তাব্বাত ইয়াদা আবী লাহাবিওঁ ওয়া তাব্ব", "আবু লাহাবের দু’হাত ধ্বংস হোক এবং সে নিজেও ধ্বংস হোক।", "https://everyayah.com/data/Alafasy_128kbps/111001.mp3"),
            Ayah(111, 2, "مَا أَغْنَىٰ عَنْهُ مَالُهُ وَمَا كَسَبَ", "মা আগনা 'আনহু মালুহু ওয়ামা কাসাব", "তার ধন-সম্পদ ও তার অর্জন তার কোনো উপকারে আসল না।", "https://everyayah.com/data/Alafasy_128kbps/111002.mp3"),
            Ayah(111, 3, "سَيَصْلَىٰ نَارًا ذَاتَ لَهَبٍ", "সাইয়াসলা না-রান যা-তা লাহাব", "অচিরেই সে লেলিহান আগুনে প্রবেশ করবে,", "https://everyayah.com/data/Alafasy_128kbps/111003.mp3"),
            Ayah(111, 4, "وَامْرَأَتُهُ حَمَّالَةَ الْحَطَبِ", "ওয়ামরাআতুহু হাম্মা-লাতাল হাতাব", "এবং তার স্ত্রীও, যে জ্বালানি কাঠ বহনকারিণী,", "https://everyayah.com/data/Alafasy_128kbps/111004.mp3"),
            Ayah(111, 5, "فِي جِيدِهَا حَبْلٌ مِنْ مَسَدٍ", "ফী জীদিহা হাবলুম মিম মাসাদ", "তার গলায় শক্ত খেজুরের পাকানো রশি থাকবে।", "https://everyayah.com/data/Alafasy_128kbps/111005.mp3")
        ),

        // 112. Al-Ikhlas (4 Ayahs)
        112 to listOf(
            Ayah(
                surahNumber = 112,
                ayahNumber = 1,
                textArabic = "قُلْ هُوَ اللَّهُ أَحَدٌ",
                textPronunciation = "কুল হুয়াল্লাহু আহাদ",
                textBengali = "বলুন: তিনিই আল্লাহ, একক ও অদ্বিতীয়।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/112001.mp3"
            ),
            Ayah(
                surahNumber = 112,
                ayahNumber = 2,
                textArabic = "اللَّهُ الصَّمَدُ",
                textPronunciation = "আল্লাহুস সামাদ",
                textBengali = "আল্লাহ কারও মুখাপেক্ষী নন, সবাই তাঁর মুখাপেক্ষী।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/112002.mp3"
            ),
            Ayah(
                surahNumber = 112,
                ayahNumber = 3,
                textArabic = "لَمْ يَلِدْ وَلَمْ يُولَدْ",
                textPronunciation = "লাম ইয়ালিদ ওয়া লাম ইউলাদ",
                textBengali = "তিনি কাউকে জন্ম দেননি এবং তাঁকেও কেউ জন্ম দেয়নি।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/112003.mp3"
            ),
            Ayah(
                surahNumber = 112,
                ayahNumber = 4,
                textArabic = "وَلَمْ يَكُنْ لَهُ كُفُوًا أَحَدٌ",
                textPronunciation = "ওয়া লাম ইয়াকুল্লাহু কুফুওয়ান আহাদ",
                textBengali = "এবং তাঁর সমতুল্য কেউই নেই।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/112004.mp3"
            )
        ),

        // 113. Al-Falaq (5 Ayahs)
        113 to listOf(
            Ayah(
                surahNumber = 113,
                ayahNumber = 1,
                textArabic = "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ",
                textPronunciation = "কুল আউযু বিরাব্বিল ফালাক",
                textBengali = "বলুন, আমি আশ্রয় প্রার্থনা করছি ভোরের পালনকর্তার,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/113001.mp3"
            ),
            Ayah(
                surahNumber = 113,
                ayahNumber = 2,
                textArabic = "مِنْ شَرِّ مَا خَلَقَ",
                textPronunciation = "মিন শাররি মা খালাক",
                textBengali = "তিনি যা সৃষ্টি করেছেন তার অনিষ্ট হতে,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/113002.mp3"
            ),
            Ayah(
                surahNumber = 113,
                ayahNumber = 3,
                textArabic = "وَمِنْ شَرِّ غَاسِقٍ إِذَا وَقَبَ",
                textPronunciation = "ওয়া মিন শাররি গাসিকিন ইযা ওয়াকাব",
                textBengali = "এবং অন্ধকারের অনিষ্ট হতে যখন তা সমাগত হয়,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/113003.mp3"
            ),
            Ayah(
                surahNumber = 113,
                ayahNumber = 4,
                textArabic = "وَمِنْ شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ",
                textPronunciation = "ওয়া মিন শাররিন নাফ্ফাছা-তি ফিল উকাদ",
                textBengali = "এবং গ্রন্থিতে ফুৎকারকারিণীদের অনিষ্ট হতে,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/113004.mp3"
            ),
            Ayah(
                surahNumber = 113,
                ayahNumber = 5,
                textArabic = "وَمِنْ شَرِّ حَاسِدٍ إِذَا حَسَدَ",
                textPronunciation = "ওয়া মিন শাররি হাসিদিন ইযা হাসাদ",
                textBengali = "এবং হিংসুকের অনিষ্ট হতে যখন সে হিংসা করে।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/113005.mp3"
            )
        ),

        // 114. An-Nas (6 Ayahs)
        114 to listOf(
            Ayah(
                surahNumber = 114,
                ayahNumber = 1,
                textArabic = "قُلْ أَعُوذُ بِرَبِّ النَّاسِ",
                textPronunciation = "কুল আউযু বিরাব্বিন নাস",
                textBengali = "বলুন, আমি আশ্রয় চাই মানুষের প্রতিপালকের কাছে,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/114001.mp3"
            ),
            Ayah(
                surahNumber = 114,
                ayahNumber = 2,
                textArabic = "مَلِكِ النَّاسِ",
                textPronunciation = "মালিকিন নাস",
                textBengali = "মানুষের অধিপতির কাছে,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/114002.mp3"
            ),
            Ayah(
                surahNumber = 114,
                ayahNumber = 3,
                textArabic = "إِلَٰهِ النَّاسِ",
                textPronunciation = "ইলাহিন নাস",
                textBengali = "মানুষের প্রকৃত উপাস্যের কাছে,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/114003.mp3"
            ),
            Ayah(
                surahNumber = 114,
                ayahNumber = 4,
                textArabic = "مِنْ شَرِّ الْوَسْوَاسِ الْخَنَّاسِ",
                textPronunciation = "মিন শাররিল ওয়াসওয়াসিল খান্নাস",
                textBengali = "কুপ্ররোচনা দানকারীর অনিষ্ট থেকে, যে আত্মগোপন করে থাকে,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/114004.mp3"
            ),
            Ayah(
                surahNumber = 114,
                ayahNumber = 5,
                textArabic = "الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ",
                textPronunciation = "আল্লাযী ইউওয়াসবিসু ফী ছুদুরিন নাস",
                textBengali = "যে মানুষের অন্তরে প্ররোচনা সৃষ্টি করে,",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/114005.mp3"
            ),
            Ayah(
                surahNumber = 114,
                ayahNumber = 6,
                textArabic = "مِنَ الْجِنَّةِ وَالنَّاسِ",
                textPronunciation = "মিনাল জিন্নাতি ওয়ান নাস",
                textBengali = "জ্বিনের মধ্য হতে এবং মানুষের মধ্য হতে।",
                audioUrl = "https://everyayah.com/data/Alafasy_128kbps/114006.mp3"
            )
        )
    )
}
