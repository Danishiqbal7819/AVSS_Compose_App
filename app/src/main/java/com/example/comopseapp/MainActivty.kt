package com.example.comopseapp


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi

import com.example.comopseapp.ui.theme.ComopseAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComopseAppTheme{
//                HomeScreen()
//                SaveDataScreen()
            }
        }
        setContent {
        }
    }



}
