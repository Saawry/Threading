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
    Only the Handlers are created in main thread, can access the main thread. The attempt to access
     the main thread component without main Looper will show error "Can't create handler inside thread
      Thread[Thread-2,5,main] that has not called Looper.prepare()". To Solve it, We use Looper.getMainLooper.
      */
    fun startThread(view: View) {
        val exampleRunnable = ExampleRunnable(10)
        Thread(exampleRunnable).start()

    }


    inner class ExampleRunnable(private val counter:Int):Runnable{
        override fun run() {
            for (i in 0 until counter) {

                if (i==5){
                    val handler=Handler(Looper.getMainLooper())
                    handler.post(object:Runnable{
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