package com.mostafiz.android.multithreading

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mostafiz.android.multithreading.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun stopThread(view: View) {

    }

    /*
    If we try to access the UI component from background thread, CalledFromWrongThreadException occurs.
    It says "Only the original thread that created a view hierarchy can touch its views"
      */
    fun startThread(view: View) {
        val exampleRunnable = ExampleRunnable(10)
        Thread(exampleRunnable).start()

    }


    inner class ExampleRunnable(private val counter:Int):Runnable{
        override fun run() {
            for (i in 0 until counter) {

                if (i==5){
                    binding.buttonStartThread.text = "Running"
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