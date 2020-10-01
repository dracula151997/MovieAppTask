package com.yelloco.movieapp.adapters

import android.view.View

interface OnItemClickListener {
    operator fun invoke(view: View, personId: Int)
}