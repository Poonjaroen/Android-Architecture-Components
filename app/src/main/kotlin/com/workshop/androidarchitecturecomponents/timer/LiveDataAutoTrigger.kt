package com.workshop.androidarchitecturecomponents.timer

import android.arch.lifecycle.MutableLiveData
import android.os.CountDownTimer

class LiveDataAutoTrigger(
    private val start: Long = 10_000L, // 10 second
    private val countDownInterval: Long = 1_000 // 1 second
) {

    private var currentMillisUntilFinished: Long = start
    private var countDownTimer: CountDownTimer? = null

    val countDownTimerLiveData = MutableLiveData<String>()

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
                countDownTimerLiveData.value = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                currentMillisUntilFinished = start
                countDownTimerLiveData.value = "Time up!!!"
            }
        }

}