package com.quranreader.aijavadevstalha.ui.util

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class AudioPlaybackState(
    val isPlaying: Boolean = false,
    val isLoading: Boolean = false,
    val currentSurahNumber: Int? = null,
    val currentAyahNumber: Int? = null,
    val surahName: String = "",
    val error: String? = null
)

class AudioPlayerManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private val _playbackState = MutableStateFlow(AudioPlaybackState())
    val playbackState: StateFlow<AudioPlaybackState> = _playbackState.asStateFlow()

    private var onAyahCompleteCallback: (() -> Unit)? = null

    fun playAyah(
        surahNumber: Int,
        ayahNumber: Int,
        audioUrl: String,
        surahName: String,
        onComplete: (() -> Unit)? = null
    ) {
        onAyahCompleteCallback = onComplete

        // If currently playing the same ayah, toggle pause
        if (_playbackState.value.isPlaying &&
            _playbackState.value.currentSurahNumber == surahNumber &&
            _playbackState.value.currentAyahNumber == ayahNumber
        ) {
            pause()
            return
        }

        // If paused on same ayah, resume
        if (!_playbackState.value.isPlaying &&
            _playbackState.value.currentSurahNumber == surahNumber &&
            _playbackState.value.currentAyahNumber == ayahNumber &&
            mediaPlayer != null
        ) {
            resume()
            return
        }

        stop()
        _playbackState.value = AudioPlaybackState(
            isLoading = true,
            currentSurahNumber = surahNumber,
            currentAyahNumber = ayahNumber,
            surahName = surahName
        )

        try {
            val url = if (audioUrl.isNotBlank()) {
                audioUrl
            } else {
                val sNum = String.format("%03d", surahNumber)
                val aNum = String.format("%03d", ayahNumber)
                "https://everyayah.com/data/Alafasy_128kbps/$sNum$aNum.mp3"
            }

            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(url)
                setOnPreparedListener { mp ->
                    mp.start()
                    _playbackState.value = AudioPlaybackState(
                        isPlaying = true,
                        isLoading = false,
                        currentSurahNumber = surahNumber,
                        currentAyahNumber = ayahNumber,
                        surahName = surahName
                    )
                }
                setOnCompletionListener {
                    _playbackState.value = _playbackState.value.copy(isPlaying = false)
                    onAyahCompleteCallback?.invoke()
                }
                setOnErrorListener { _, what, extra ->
                    Log.e("AudioPlayerManager", "MediaPlayer error: what=$what, extra=$extra")
                    _playbackState.value = AudioPlaybackState(
                        isPlaying = false,
                        isLoading = false,
                        error = "অডিও প্লেব্যাকের সময় ত্রুটি হয়েছে"
                    )
                    true
                }
                prepareAsync()
            }
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Exception preparing audio", e)
            _playbackState.value = AudioPlaybackState(
                isPlaying = false,
                isLoading = false,
                error = "অডিও শুরু করা সম্ভব হয়নি"
            )
        }
    }

    fun pause() {
        try {
            if (mediaPlayer?.isPlaying == true) {
                mediaPlayer?.pause()
                _playbackState.value = _playbackState.value.copy(isPlaying = false)
            }
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Error pausing audio", e)
        }
    }

    fun resume() {
        try {
            mediaPlayer?.start()
            _playbackState.value = _playbackState.value.copy(isPlaying = true)
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Error resuming audio", e)
        }
    }

    fun stop() {
        try {
            mediaPlayer?.stop()
            mediaPlayer?.release()
        } catch (e: Exception) {
            Log.e("AudioPlayerManager", "Error stopping audio", e)
        } finally {
            mediaPlayer = null
            _playbackState.value = AudioPlaybackState()
        }
    }

    fun release() {
        stop()
    }
}
