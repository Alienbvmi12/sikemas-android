package com.example.sikemasapp.ui.component

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast

class DoubleTapGestureListener(val context: Context, val exec: () -> Any) : GestureDetector.SimpleOnGestureListener() {

    override fun onDoubleTap(e: MotionEvent): Boolean {
        // Handle the double-tap event
        // Run your function here
        exec()
        return true
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        Toast.makeText(context, "Tekan sekali lagi", Toast.LENGTH_SHORT).show()
        return super.onSingleTapUp(e)
    }

    private fun yourFunctionToRunOnDoubleClick() {
        // Implement your logic for double-click action
    }
}