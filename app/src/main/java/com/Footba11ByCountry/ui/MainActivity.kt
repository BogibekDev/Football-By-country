package com.Footba11ByCountry.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.Footba11ByCountry.R
import com.Footba11ByCountry.databinding.ActivityMainBinding
import com.Footba11ByCountry.managers.PrefManager
import com.Footba11ByCountry.service.SoundService
import com.Footba11ByCountry.utils.Extentions.click

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar)
        if (PrefManager(this).getBoolean("isMuted")) {
            startService(Intent(this, SoundService::class.java))
        }
        initViews()
    }

    private fun initViews() {
        binding.apply {
            bSettings.click {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }
            bStart.click {
                startActivity(Intent(this@MainActivity, GameActivity::class.java))

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, SoundService::class.java))
    }
}