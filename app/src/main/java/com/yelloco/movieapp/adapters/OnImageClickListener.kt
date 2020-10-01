package com.yelloco.movieapp.adapters

import android.view.View

interface OnImageClickListener {
    operator fun invoke(view: View, imagePath: String)
}