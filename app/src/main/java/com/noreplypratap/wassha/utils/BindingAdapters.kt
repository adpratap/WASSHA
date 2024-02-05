package com.noreplypratap.wassha.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

@SuppressLint("SetTextI18n")
@BindingAdapter("setWordCount")
fun setWordCount(textView: TextView, count: Int) {
    textView.visibility = View.VISIBLE
    textView.text = "Words : $count"
}

@SuppressLint("SetTextI18n")
@BindingAdapter("setLengthAndView")
fun setLengthAndView(textView: TextView, length: Int) {
    if (length > 80) {
        textView.text = "Length : $length"
        textView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.GONE
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("isLocal")
fun isLocal(textView: TextView, value: Boolean) {
    if (value) {
        textView.visibility = View.VISIBLE
        textView.text = "Data was fetched from local"
    } else {
        textView.visibility = View.GONE
    }
}