package com.quranreader.aijavadevstalha.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.quranreader.aijavadevstalha.data.model.Ayah
import com.quranreader.aijavadevstalha.data.model.Surah
import com.quranreader.aijavadevstalha.ui.theme.*
import com.quranreader.aijavadevstalha.ui.util.AudioPlaybackState
import com.quranreader.aijavadevstalha.ui.viewmodel.QuranUiState
import com.quranreader.aijavadevstalha.ui.viewmodel.QuranViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SurahDetailScreen(
    viewModel: QuranViewModel,
    uiState: QuranUiState,
    surah: Surah,
    modifier: Modifier = Modifier
) {
    // Intercept back navigation to return cleanly to surah list
    BackHandler {
        viewModel.closeSurahDetail()
    }

    val playbackState by viewModel.playbackState.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("surah_detail_screen"),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = surah.nameBengali,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "${surah.nameEnglish} • ${surah.revelationType} (${surah.totalAyahs} আয়াত)",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.closeSurahDetail() },
                        modifier = Modifier.testTag("back_to_surahs_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Text(
                        text = surah.nameArabic,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        color = GoldPrimary,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = EmeraldPrimary
                )
            )
        },
        bottomBar = {
            // Floating Audio Recitation Controls
            if (playbackState.isPlaying || playbackState.isLoading || playbackState.currentSurahNumber == surah.number) {
                AudioBottomBar(
                    playbackState = playbackState,
                    onPause = { viewModel.pauseAudio() },
                    onResume = { viewModel.resumeAudio() },
                    onStop = { viewModel.stopAudio() }
                )
            }
        }
    ) { innerPadding ->
        if (uiState.isAyahsLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(
                        color = EmeraldPrimary,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "আয়াতসমূহ লোড হচ্ছে…",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .testTag("ayah_list_lazy_column"),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Surah Introduction Header Banner
                item {
                    SurahHeaderCard(surah = surah)
                }

                // Bismillah Header (except for Surah At-Tawbah, #9)
                if (surah.number != 9) {
                    item {
                        BismillahHeader(
                            showPronunciation = uiState.showPronunciation,
                            arabicFontSize = uiState.arabicFontSize,
                            bengaliFontSize = uiState.bengaliFontSize
                        )
                    }
                }

                // Ayahs list
                items(
                    items = uiState.currentAyahs,
                    key = { "${it.surahNumber}_${it.ayahNumber}" }
                ) { ayah ->
                    AyahCardItem(
                        ayah = ayah,
                        surahName = surah.nameBengali,
                        showPronunciation = uiState.showPronunciation,
                        arabicFontSize = uiState.arabicFontSize,
                        bengaliFontSize = uiState.bengaliFontSize,
                        isPlaying = playbackState.isPlaying &&
                            playbackState.currentSurahNumber == ayah.surahNumber &&
                            playbackState.currentAyahNumber == ayah.ayahNumber,
                        isLoadingAudio = playbackState.isLoading &&
                            playbackState.currentSurahNumber == ayah.surahNumber &&
                            playbackState.currentAyahNumber == ayah.ayahNumber,
                        onPlayAudio = { viewModel.playAyahAudio(ayah, surah.nameBengali) },
                        onBookmarkToggle = { viewModel.toggleBookmark(ayah) },
                        onCopy = { viewModel.copyAyahToClipboard(ayah, surah.nameBengali) },
                        onShare = { viewModel.shareAyah(ayah, surah.nameBengali) }
                    )
                }
            }
        }
    }
}

@Composable
fun SurahHeaderCard(surah: Surah) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("surah_detail_header"),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(EmeraldDark, EmeraldPrimary)
                    )
                )
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = surah.nameArabic,
                    fontFamily = FontFamily.Serif,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = GoldPrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "${surah.nameBengali} (${surah.nameEnglish})",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "অর্থ: ${surah.meaningBengali}",
                    color = GoldLight,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = surah.revelationType,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "${surah.totalAyahs} আয়াত",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BismillahHeader(
    showPronunciation: Boolean,
    arabicFontSize: Float,
    bengaliFontSize: Float
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("bismillah_header"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Arabic Bismillah
            Text(
                text = "بِسْمِ اللَّهِ الرَّحْمَٰنِ الرَّحِيمِ",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontFamily = FontFamily.Serif,
                    fontSize = arabicFontSize.sp,
                    lineHeight = (arabicFontSize * 1.5).sp,
                    color = EmeraldPrimary
                ),
                textAlign = TextAlign.Center
            )

            // Bengali pronunciation directly above Bengali meaning
            if (showPronunciation) {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(EmeraldLight)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "উচ্চারণ: বিসমিল্লাহির রাহমানির রাহীম",
                        color = EmeraldDark,
                        fontSize = (bengaliFontSize - 1).sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    )
                }
            }

            // Bengali Meaning
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "পরম করুণাময় ও অসীম দয়ালু আল্লাহর নামে শুরু করছি।",
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = bengaliFontSize.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AyahCardItem(
    ayah: Ayah,
    surahName: String,
    showPronunciation: Boolean,
    arabicFontSize: Float,
    bengaliFontSize: Float,
    isPlaying: Boolean,
    isLoadingAudio: Boolean,
    onPlayAudio: () -> Unit,
    onBookmarkToggle: () -> Unit,
    onCopy: () -> Unit,
    onShare: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("ayah_card_${ayah.ayahNumber}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPlaying) EmeraldLight.copy(alpha = 0.35f)
            else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Top action bar inside card
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ayah badge number
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(EmeraldLight)
                        .border(1.dp, GoldAccent.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${ayah.ayahNumber}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = EmeraldDark
                    )
                }

                // Actions: Audio, Bookmark, Copy, Share
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Play Audio
                    IconButton(
                        onClick = onPlayAudio,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("play_audio_${ayah.ayahNumber}")
                    ) {
                        if (isLoadingAudio) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                color = EmeraldPrimary,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = if (isPlaying) "Pause audio" else "Play audio",
                                tint = if (isPlaying) EmeraldPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    // Bookmark
                    IconButton(
                        onClick = onBookmarkToggle,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("bookmark_${ayah.ayahNumber}")
                    ) {
                        Icon(
                            imageVector = if (ayah.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (ayah.isBookmarked) GoldAccent else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Copy
                    IconButton(
                        onClick = onCopy,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("copy_ayah_${ayah.ayahNumber}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCopy,
                            contentDescription = "Copy Ayah",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                    // Share
                    IconButton(
                        onClick = onShare,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("share_ayah_${ayah.ayahNumber}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share Ayah",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 1. ARABIC TEXT (Right-to-Left)
            Text(
                text = ayah.textArabic,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = FontFamily.Serif,
                    fontSize = arabicFontSize.sp,
                    lineHeight = (arabicFontSize * 1.6).sp,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("arabic_text_${ayah.ayahNumber}"),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f),
                thickness = 1.dp
            )
            Spacer(modifier = Modifier.height(12.dp))

            // 2. BENGALI PRONUNCIATION (বাংলা উচ্চারণ) — Positioned directly ABOVE Bengali meaning!
            if (showPronunciation && ayah.textPronunciation.isNotBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (isPlaying) EmeraldPrimary.copy(alpha = 0.15f)
                            else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                        )
                        .border(
                            1.dp,
                            EmeraldPrimary.copy(alpha = 0.25f),
                            RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                        .testTag("pronunciation_box_${ayah.ayahNumber}")
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(EmeraldPrimary)
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "উচ্চারণ",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = ayah.textPronunciation,
                            fontSize = (bengaliFontSize + 0.5f).sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = ((bengaliFontSize + 0.5f) * 1.4f).sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            // 3. BENGALI MEANING (বাংলা অর্থ) — Positioned directly BELOW the pronunciation!
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("translation_box_${ayah.ayahNumber}")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(GoldAccent)
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "বাংলা অর্থ",
                            color = Color.White,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = ayah.textBengali,
                    fontSize = bengaliFontSize.sp,
                    lineHeight = (bengaliFontSize * 1.45f).sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
fun AudioBottomBar(
    playbackState: AudioPlaybackState,
    onPause: () -> Unit,
    onResume: () -> Unit,
    onStop: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .testTag("audio_bottom_bar"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = EmeraldDark
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(GoldPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (playbackState.isPlaying) Icons.Default.PlayArrow else Icons.Default.Pause,
                        contentDescription = null,
                        tint = EmeraldDark,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "তিলাওয়াত চলছে: ${playbackState.surahName}",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "আয়াত নম্বর: ${playbackState.currentAyahNumber ?: 1} (মিশারি রশিদ আফাসী)",
                        color = GoldLight,
                        fontSize = 11.sp
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        if (playbackState.isPlaying) onPause() else onResume()
                    },
                    modifier = Modifier.testTag("audio_play_pause_toggle")
                ) {
                    Icon(
                        imageVector = if (playbackState.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = "Toggle play pause",
                        tint = GoldPrimary
                    )
                }

                IconButton(
                    onClick = onStop,
                    modifier = Modifier.testTag("audio_stop_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = "Stop recitation",
                        tint = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}
