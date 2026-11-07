package com.quranreader.aijavadevstalha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import com.quranreader.aijavadevstalha.ui.screens.BookmarkScreen
import com.quranreader.aijavadevstalha.ui.screens.SettingsScreen
import com.quranreader.aijavadevstalha.ui.screens.SurahDetailScreen
import com.quranreader.aijavadevstalha.ui.screens.SurahListScreen
import com.quranreader.aijavadevstalha.ui.theme.EmeraldDark
import com.quranreader.aijavadevstalha.ui.theme.EmeraldLight
import com.quranreader.aijavadevstalha.ui.theme.EmeraldPrimary
import com.quranreader.aijavadevstalha.ui.theme.QuranReaderTheme
import com.quranreader.aijavadevstalha.ui.viewmodel.MainTab
import com.quranreader.aijavadevstalha.ui.viewmodel.QuranViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: QuranViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            QuranReaderTheme {
                QuranAppRoot(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun QuranAppRoot(viewModel: QuranViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        // If a specific Surah is open, show SurahDetailScreen
        if (uiState.selectedSurah != null) {
            SurahDetailScreen(
                viewModel = viewModel,
                uiState = uiState,
                surah = uiState.selectedSurah!!
            )
        } else {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    QuranBottomNavigation(
                        currentTab = uiState.currentTab,
                        onTabSelected = { viewModel.selectTab(it) }
                    )
                }
            ) { innerPadding ->
                AnimatedContent(
                    targetState = uiState.currentTab,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "tab_transition",
                    modifier = Modifier.padding(innerPadding)
                ) { tab ->
                    when (tab) {
                        MainTab.SURAHS -> SurahListScreen(
                            viewModel = viewModel,
                            uiState = uiState
                        )
                        MainTab.BOOKMARKS -> BookmarkScreen(
                            viewModel = viewModel,
                            uiState = uiState
                        )
                        MainTab.SETTINGS -> SettingsScreen(
                            viewModel = viewModel,
                            uiState = uiState
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun QuranBottomNavigation(
    currentTab: MainTab,
    onTabSelected: (MainTab) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.testTag("bottom_nav_bar")
    ) {
        NavigationBarItem(
            selected = currentTab == MainTab.SURAHS,
            onClick = { onTabSelected(MainTab.SURAHS) },
            icon = {
                Icon(
                    imageVector = if (currentTab == MainTab.SURAHS) Icons.Filled.AutoStories else Icons.Outlined.AutoStories,
                    contentDescription = "সুরার তালিকা"
                )
            },
            label = { Text("সুরা") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldPrimary,
                indicatorColor = EmeraldLight
            ),
            modifier = Modifier.testTag("nav_tab_surahs")
        )

        NavigationBarItem(
            selected = currentTab == MainTab.BOOKMARKS,
            onClick = { onTabSelected(MainTab.BOOKMARKS) },
            icon = {
                Icon(
                    imageVector = if (currentTab == MainTab.BOOKMARKS) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder,
                    contentDescription = "বুকমার্ক"
                )
            },
            label = { Text("বুকমার্ক") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldPrimary,
                indicatorColor = EmeraldLight
            ),
            modifier = Modifier.testTag("nav_tab_bookmarks")
        )

        NavigationBarItem(
            selected = currentTab == MainTab.SETTINGS,
            onClick = { onTabSelected(MainTab.SETTINGS) },
            icon = {
                Icon(
                    imageVector = if (currentTab == MainTab.SETTINGS) Icons.Filled.Settings else Icons.Outlined.Settings,
                    contentDescription = "সেটিংস"
                )
            },
            label = { Text("সেটিংস") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = EmeraldDark,
                selectedTextColor = EmeraldPrimary,
                indicatorColor = EmeraldLight
            ),
            modifier = Modifier.testTag("nav_tab_settings")
        )
    }
}
