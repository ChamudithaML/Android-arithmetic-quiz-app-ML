package com.example.arithmeticquizchamuditha

// importing required packages
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

// Inheriting from DialogFragment
class Pop_Up_Window_C: DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var rootView : View = inflater.inflate(R.layout.activity_pop_up_window_c,container,false)

        return rootView
    }
}


// References for popup window
// https://www.youtube.com/watch?v=SkFcDWt9GV4

//ML Chamuditha - w1809779
