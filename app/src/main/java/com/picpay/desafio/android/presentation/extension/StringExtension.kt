package com.picpay.desafio.android.presentation.extension

import android.content.Context
import android.widget.Toast

fun String.toToast(context: Context) = Toast.makeText(context, this, Toast.LENGTH_SHORT).show()