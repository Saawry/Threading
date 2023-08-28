package com.mostafiz.android.multithreading

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mostafiz.android.multithreading.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Volatile
    var stopFlag = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun stopThread(view: View) {
        stopFlag = true
    }


    fun startThread(view: View) {
        //Easy way
        Thread(ExampleRunnable(10)).start()
    }


    inner class ExampleRunnable(private val counter:Int):Runnable{
        override fun run() {
            for (i in 0 until counter) {
                if (stopFlag)
                    return
                if (i == 5) {
                    runOnUiThread { binding.buttonStartThread.text = "Running" }
                }
                Log.d("Tag_Thread_Run", "Runnable is running: $i")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

    }

    class ExampleThread(private val counter:Int): Thread() {
        override fun run() {
            for (i in 0 until counter) {
                Log.d("Tag_Thread_Run", "Thread 1 is running: $i")
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}