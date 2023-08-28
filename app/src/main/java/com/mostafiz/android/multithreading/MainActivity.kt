package com.mostafiz.android.multithreading

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mostafiz.android.multithreading.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun stopThread(view: View) {

    }

    /*
    The Solution is executing long running task in a background thread. One way of creating background
     thread is Extending Tread class. Another way is implementing Runnable Interface and passing that in a
     new thread.
      */
    fun startThread(view: View) {
        val exampleThread =ExampleThread(10)
        exampleThread.start()

        val exampleRunnable =ExampleRunnable(10)
        Thread(exampleRunnable).start()
    }


    class ExampleRunnable(private val counter:Int):Runnable{
        override fun run() {
            for (i in 0 until counter) {
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