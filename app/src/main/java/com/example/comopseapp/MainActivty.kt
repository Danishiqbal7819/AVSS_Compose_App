package com.example.comopseapp


import android.os.Bundle
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


enum class Days(n:Int){
    MONDAY(1),
    TUESDAY(2),
    THURSDAY(3),
    WEDNESDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7)
}


fun main(){
    for (i in Days.values()){
        println(i)

    }
}
