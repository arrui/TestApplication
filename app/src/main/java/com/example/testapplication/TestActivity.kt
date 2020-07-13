package com.example.testapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lib.LogUtil

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtil.log()
    }
    override fun onStart() {
        super.onStart()
        LogUtil.log()
    }

    override fun onStop() {
        super.onStop()
        LogUtil.log()
    }

    override fun onResume() {
        super.onResume()
        LogUtil.log()
    }

    override fun onPause() {
        super.onPause()
        LogUtil.log()
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.log()
    }

    override fun onRestart() {
        super.onRestart()
        LogUtil.log()
    }
}