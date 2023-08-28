package com.mostafiz.android.multithreading

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mostafiz.android.multithreading.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
     lateinit var binding: ActivityMainBinding
     private val mainHandler= Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun stopThread(view: View) {

    }

    /*
    To access a ui component from background thread, we need Handler. UI components are not thread safe.
    They can only be accessed from the thread where they are created. Handler communicates with different threads.
    I brings the works to the message queue. Only the Handlers are created in main thread, can access the main thread.
      */
    fun startThread(view: View) {
        val exampleRunnable = ExampleRunnable(10)
        Thread(exampleRunnable).start()

    }


    inner class ExampleRunnable(private val counter:Int):Runnable{
        override fun run() {
            for (i in 0 until counter) {

                if (i==5){
                    mainHandler.post(object:Runnable{
                        override fun run() {
                            binding.buttonStartThread.text = "Running"
                        }
                    })

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