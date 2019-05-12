package com.workshop.androidarchitecturecomponents.timer

import android.arch.lifecycle.LiveData
import android.os.CountDownTimer

class CustomLiveDataAutoTrigger(
    private val start: Long = 10_000L, // 10 second
    private val countDownInterval: Long = 1_000 // 1 second
) : LiveData<String>() {

    private var currentMillisUntilFinished: Long = start
    private var countDownTimer: CountDownTimer? = null

    override fun onActive() {
        countDownTimer = timeTrigger(currentMillisUntilFinished, countDownInterval).start()
    }

    override fun onInactive() {
        countDownTimer?.cancel()
    }

    private fun timeTrigger(millisInFuture: Long, countDownInterval: Long): CountDownTimer =
        object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                currentMillisUntilFinished = millisUntilFinished - 1_000L
                value = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                currentMillisUntilFinished = start
                value = "Time up!!!"
            }
        }

}