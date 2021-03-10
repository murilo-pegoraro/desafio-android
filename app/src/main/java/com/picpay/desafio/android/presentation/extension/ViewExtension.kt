package com.picpay.desafio.android.presentation.extension

import android.view.View

private fun View.show() {
    this.visibility = View.VISIBLE
}

private fun View.hide() {
    this.visibility = View.GONE
}

fun View.visible(isVisible: Boolean) {
    if (isVisible) this.show() else this.hide()
}