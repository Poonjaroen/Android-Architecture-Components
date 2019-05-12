package com.workshop.androidarchitecturecomponents.timer

import android.os.CountDownTimer
import com.workshop.androidarchitecturecomponents.MainScreen

class SimpleAutoTrigger(
    private val start: Long = 10_000L, // 10 second
    private val countDownInterval: Long = 1_000, // 1 second
    private val callback: MainScreen
) {

    private var currentMillisUntilFinished: Long = start
    private var countDownTimer: CountDownTimer? = null

    fun start() {
        countDownTimer = timeTrigger(currentMillisUntilFinished, countDownInterval).start()
    }

    fun stop() {
        countDownTimer?.cancel()
    }

    private fun timeTrigger(millisInFuture: Long, countDownInterval: Long): CountDownTimer =
        object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                currentMillisUntilFinished = millisUntilFinished - 1_000L
                callback.showTimer("${millisUntilFinished / 1000}")
            }

            override fun onFinish() {
                currentMillisUntilFinished = start
                callback.showTimer("Time up!!!")
            }
        }

}