package com.example.imageloader.ui.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.imageloader.R

fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(object : CustomTarget<Drawable>() {
        override fun onLoadFailed(errorDrawable: Drawable?) {
            super.onLoadFailed(errorDrawable)
            this@loadImage.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_image_not_found
                )
            )
        }

        override fun onLoadCleared(placeholder: Drawable?) {
        }

        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            this@loadImage.setImageDrawable(resource)
        }
    })
}