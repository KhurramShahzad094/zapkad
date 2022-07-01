package com.khurram.zapkadtest.util

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun View.showSnackBar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()
}

fun Context.showToast(text: String){
    Toast.makeText(this, text , Toast.LENGTH_SHORT).show()
}

fun View.visible(){
    this.visibility= View.VISIBLE
}

fun View.invisible(){
    this.visibility= View.INVISIBLE
}

fun View.gone(){
    this.visibility= View.GONE
}

fun Any?.ifNotNullThenReturn(): String {
    if (this == null) return "N/A"
    return toString()
}