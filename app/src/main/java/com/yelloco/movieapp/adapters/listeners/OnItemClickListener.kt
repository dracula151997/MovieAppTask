package com.yelloco.movieapp.adapters.listeners

import android.view.View

interface OnItemClickListener {
    operator fun invoke(view: View, personId: Int)
}