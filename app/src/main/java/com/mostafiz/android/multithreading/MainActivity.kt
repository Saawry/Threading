package com.mostafiz.android.multithreading

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun stopThread(view: View) {

    }

    /*
    *The task is long and running on main/UI thread. After running the thread, the UI gets frozen
    *until the thread completes the execution. during that time If user try to access any UI component,
    *ANR occurs if the UI takes more than 5 seconds to respond.  */
    fun startThread(view: View) {
        for (i in 0 until 10) {
            Log.d("Tag_Thread_Run", "startThread: $i")
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}