package com.example.demo.view.component

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.example.demo.R

class WebImageView(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    var url: String? = ""
        set(value) {
            field = value
            if (value == null || value == "") {
                setImageResource(R.mipmap.image_notfound)
            } else {
                Glide.with(this).load(value).into(this)
            }
        }
}