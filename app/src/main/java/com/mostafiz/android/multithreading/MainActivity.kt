package com.mostafiz.android.multithreading

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    Another way to access ui component from background thread is to use uiComponent.post() method directly.
    Theres another way that is using runOnUiThread{} which is an activity function.
      */
    fun startThread(view: View) {
        val exampleRunnable = ExampleRunnable(10)
        Thread(exampleRunnable).start()

    }


    inner class ExampleRunnable(private val counter:Int):Runnable{
        override fun run() {
            for (i in 0 until counter) {

                if (i==5){
                    //way 1
                    binding.buttonStartThread.post(object:Runnable{
                        override fun run() {
                            binding.buttonStartThread.text = "Running"
                        }
                    })
                    //way 1 lambda
                    binding.buttonStartThread.post { binding.buttonStartThread.text = "Running" }

                    //way 2
                    runOnUiThread(object:Runnable{
                        override fun run() {
                            binding.buttonStartThread.text = "Running"
                        }

                    })
                    //way 2 lambda
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