package com.noreplypratap.wassha.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("setWordCount")
fun setWordCount(textView: TextView, value: String?) {
    if (!value.isNullOrBlank() && value != "null"){
        textView.visibility = View.VISIBLE
        textView.text = "Words : ${countWords(value)}"
    } else {
        textView.visibility = View.GONE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setLengthAndView")
fun setLengthAndView(textView: TextView, string: String?) {
    string?.length?.let {
        if (it > 80){
            textView.text = "Length : $it"
            textView.visibility = View.VISIBLE
        }else {
            textView.visibility = View.GONE
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("isLocal")
fun isLocal(textView: TextView, value: Boolean) {
    if (value){
        textView.visibility = View.VISIBLE
        textView.text = "Data was fetched from local"
    }else {
        textView.visibility = View.GONE
    }
}

fun countWords(input: String): Int {
    val words = input.trim().split("\\s+".toRegex())
    return words.size
}