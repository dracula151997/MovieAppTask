package com.yelloco.movieapp.adapters.listeners

import android.view.View

interface OnImageClickListener {
    operator fun invoke(view: View, imagePath: String)
}