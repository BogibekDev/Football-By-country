package com.Footba11ByCountry.utils

import android.content.Context
import android.media.MediaPlayer
import com.Footba11ByCountry.R

object PlaySound {
    private lateinit var player: MediaPlayer

    fun playAllScreens(context: Context) {
        player = MediaPlayer.create(context, R.raw.all_screens)
        player.start()
    }

}