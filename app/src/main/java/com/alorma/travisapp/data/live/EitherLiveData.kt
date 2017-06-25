package com.alorma.travisapp.data.live

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import org.funktionale.either.Either

class EitherLiveData<T> : LiveData<Either<Throwable, T>>() {

    fun observe(owner: LifecycleOwner?,
                successFunction: (t: T) -> Unit?,
                errorFunction: (t: Throwable) -> Unit?) {
        observe(owner, Observer {
            when (it) {
                is Either.Right -> successFunction.invoke(it.right().get())
                is Either.Left -> errorFunction.invoke(it.left().get())
                else -> {
                }
            }
        })
    }

    fun observeSuccess(owner: LifecycleOwner, observeFunction: (t: T) -> Unit?) {
        observe(owner, Observer {
            when (it) {
                is Either.Right -> observeFunction.invoke(it.right().get())
                else -> {
                }
            }
        })
    }

    fun observeError(owner: LifecycleOwner?, observeFunction: (t: Throwable) -> Unit?) {
        observe(owner, Observer {
            when (it) {
                is Either.Left -> observeFunction.invoke(it.left().get())
                else -> {
                }
            }
        })
    }

    fun post(value: T) {
        postValue(Either.Right(value))
    }

    fun post(t: Throwable) {
        postValue(Either.Left(t))
    }

}
