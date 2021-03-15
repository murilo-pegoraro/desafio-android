package com.picpay.desafio.android.presentation.extension

import android.view.View

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.visible(isVisible: Boolean) {
    if (isVisible) this.show() else this.hide()
}