package com.example.samplejson_jan10.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.samplejson_jan10.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}