package com.picpay.desafio.android.presentation.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addToDispose(disposables: CompositeDisposable) = apply { disposables.add(this) }