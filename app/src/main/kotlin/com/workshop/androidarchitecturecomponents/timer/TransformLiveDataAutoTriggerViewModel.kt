package com.workshop.androidarchitecturecomponents.timer

import android.arch.lifecycle.*
import android.os.CountDownTimer

class TransformLiveDataAutoTriggerViewModel(
    private val start: Long = 10_000L, // 10 second
    private val countDownInterval: Long = 1_000 // 1 second
) : ViewModel() {


    private var currentMillisUntilFinished: Long = start

    private var countDownTimer: CountDownTimer? = null
    private val countDownTimerLiveData = MutableLiveData<Long>()


    var mediatorLiveData = MediatorLiveData<String>()

    val autoTriggerLiveData: LiveData<String> = Transformations.map(countDownTimerLiveData) { millisUntilFinished ->
        when (val sec = millisUntilFinished.div(1000)) {
            in 1..10 -> "$sec"
            else -> "Time up!!!"
        }
    }

    init {
        mediatorLiveData.addSource(countDownTimerLiveData) {
            mediatorLiveData.value = "Long : ${it?.div(1000)}"
        }
        mediatorLiveData.addSource(autoTriggerLiveData) {
            mediatorLiveData.value =  "String : $it"
        }
    }

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
                countDownTimerLiveData.value = millisUntilFinished
            }

            override fun onFinish() {
                currentMillisUntilFinished = start
                countDownTimerLiveData.value = 0
            }
        }

}