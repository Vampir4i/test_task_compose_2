package com.example.test_task_compose_2.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import com.example.test_task_compose_2.App

object VibrateUtil {
    private val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            App.instance.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        App.instance.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    fun addToDbVibrationEffect(): VibrationEffect? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createWaveform(
                listOf(50L, 150L, 100L).toLongArray(),
                listOf(200, 0, 50).toIntArray(),
                -1
            )
        } else null
    }

    fun deleteFromDbVibrationEffect(): VibrationEffect? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createWaveform(
                listOf(100L, 150L, 50L).toLongArray(),
                listOf(50, 0, 200).toIntArray(),
                -1
            )
        } else null
    }

    fun simpleClickVibrationEffect(): VibrationEffect? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            VibrationEffect.createOneShot(
                10L, 10
            )
        } else null
    }

    fun vibrate(vibrationEffect: VibrationEffect?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.cancel()
            vibrator.vibrate(vibrationEffect)
        }
    }
}