package com.Footba11ByCountry.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.Footba11ByCountry.R
import com.Footba11ByCountry.databinding.ActivitySettingsBinding
import com.Footba11ByCountry.managers.PrefManager
import com.Footba11ByCountry.service.SoundService
import com.Footba11ByCountry.utils.Extentions.click

class SettingsActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar)
        initViews()
    }

    private fun initViews() {


        binding.apply {
            if (PrefManager(this@SettingsActivity).getBoolean("isMuted")) {
                rbSOn.isChecked = true
                rbSOff.isChecked = false
            } else {
                rbSOn.isChecked = false
                rbSOff.isChecked = true
            }
            if (PrefManager(this@SettingsActivity).getBoolean("isVibrateOn")) {
                rbVOn.isChecked = true
                rbVOff.isChecked = false
            } else {
                rbVOn.isChecked = false
                rbVOff.isChecked = true
            }

            ivBack.click {
                finish()
            }
            rbSOn.click {
                if (rbSOn.isChecked) {
                    stopService(Intent(this@SettingsActivity, SoundService::class.java))
                    PrefManager(this@SettingsActivity).saveBoolean("isMuted", true)
                    if (PrefManager(this@SettingsActivity).getBoolean("isMuted"))
                        startService(
                            Intent(this@SettingsActivity, SoundService::class.java)
                        )
                }
            }
            rbSOff.click {
                if (rbSOff.isChecked) {
                    PrefManager(this@SettingsActivity).saveBoolean("isMuted", false)
                    stopService(Intent(this@SettingsActivity, SoundService::class.java))

                }
            }
            rbVOn.click {
                PrefManager(this@SettingsActivity).saveBoolean("isVibrateOn", true)
            }
            rbVOff.click {
                PrefManager(this@SettingsActivity).saveBoolean("isVibrateOn", false)
            }
        }
    }
}