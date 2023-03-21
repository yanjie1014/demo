package com.example.demo.view.component

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.example.demo.R

class WebImageView(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageView(context, attrs) {
    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.WebImageView)
        val url = array.getString(R.styleable.WebImageView_url)
        if (url != null || url != "") {
            Glide.with(this).load(url).into(this)
        } else {
            setImageResource(R.mipmap.image_notfound)
        }
        array.recycle()
    }

    var url: String = ""
        set(value) {
            field = value
//            Glide.with(this).load(value).into(this)
            if (value != "") {
                Glide.with(this).load(value).into(this)
            } else {
                setImageResource(R.mipmap.image_notfound)
            }
        }
}