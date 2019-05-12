package com.workshop.androidarchitecturecomponents

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.workshop.androidarchitecturecomponents.timer.LiveDataAutoTrigger
import com.workshop.androidarchitecturecomponents.timer.SimpleAutoTrigger
import com.workshop.androidarchitecturecomponents.timer.TransformLiveDataAutoTriggerViewModel
import com.workshop.androidarchitecturecomponents.timer.ViewModelAutoTrigger
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainScreen {

    private lateinit var simpleAutoTrigger: SimpleAutoTrigger
    private lateinit var liveDataAutoTrigger: LiveDataAutoTrigger
    private lateinit var viewModeAutoTrigger: ViewModelAutoTrigger
    private lateinit var transformLiveDataAutoTriggerViewModel: TransformLiveDataAutoTriggerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModeAutoTrigger =
            ViewModelProviders.of(this).get(ViewModelAutoTrigger::class.java)

        transformLiveDataAutoTriggerViewModel =
            ViewModelProviders.of(this).get(TransformLiveDataAutoTriggerViewModel::class.java)

        simpleAutoTrigger = SimpleAutoTrigger(callback = this)
        liveDataAutoTrigger = LiveDataAutoTrigger()

        val timerObserver = Observer<String> {
            Log.d("---->","UI : $it")
            text_time.text = it
        }


        //liveDataAutoTrigger.countDownTimerLiveData.observe(this, timerObserver)

        //CustomLiveDataAutoTrigger().observe(this, timerObserver)

        viewModeAutoTrigger.countDownTimerLiveData.observe(this, timerObserver)

        transformLiveDataAutoTriggerViewModel.autoTriggerLiveData.observe(this, timerObserver)
        transformLiveDataAutoTriggerViewModel.mediatorLiveData.observe(this, timerObserver)
    }

    override fun onStart() {
        super.onStart()
        //simpleAutoTrigger.start()
        //liveDataAutoTrigger.start()
        //viewModeAutoTrigger.start()
        //transformLiveDataAutoTriggerViewModel.start()
    }

    override fun onStop() {
        super.onStop()
        //simpleAutoTrigger.stop()
        //liveDataAutoTrigger.stop()
        //viewModeAutoTrigger.stop()
        //transformLiveDataAutoTriggerViewModel.stop()
    }

    override fun showTimer(time: String) {
        text_time.text = time
    }

}