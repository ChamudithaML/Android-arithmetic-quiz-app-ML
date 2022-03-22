package com.example.arithmeticquizchamuditha

// importing required packages
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Calling pop window function
        pop_up_window_func()

        // reading view and storing to variable
        val bt2 = findViewById<Button>(R.id.bt2)

        // setting click listener to bt2
        bt2.setOnClickListener {
            // calling new game function to open game activity
            new_game_func()
        }

    }

    // function for popup window for clarity
    fun pop_up_window_func(){


        val bt1 = findViewById<Button>(R.id.bt1)

        bt1.setOnClickListener {
            var popUpView = Pop_Up_Window_C()
            popUpView.show(supportFragmentManager,"myDialog")       // showing popup when button pressed
        }
    }

    // function to start the game
    fun new_game_func(){
        // creating intent for New_Game_C activity
        val new_game_intent_c = Intent(this,New_Game_C::class.java)
        startActivity(new_game_intent_c)        // Starting activity

    }

}

// References for popup window
// https://www.youtube.com/watch?v=SkFcDWt9GV4

//ML Chamuditha - w1809779