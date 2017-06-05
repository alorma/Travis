package com.alorma.travisapp.dagger.module

import android.R.attr.port
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request


class TestNetworkModule : NetworkModule() {

    private val SCHEME = "http"
    private val HOST = "localhost"

    private val idlingResource = CountingIdlingResource("OkHttpClient")

    fun getTestInterceptor(): Interceptor {
        return Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()

            val newUrl = chain.request().url().newBuilder()
                    .scheme(SCHEME)
                    .host(HOST)
                    .port(port)
                    .build()

            val request = requestBuilder.url(newUrl).build()

            executeIdle(ExecutorImpl(chain, request))
        }
    }

    fun <R> executeIdle(executor: Executor<R>): R {
        incrementIdling()
        val response: R = executor.execute()
        decrementIdling()
        return response
    }

    private fun incrementIdling() {
        idlingResource.increment()
    }

    private fun decrementIdling() {
        idlingResource.decrement()
    }

    class ExecutorImpl(val chain: Interceptor.Chain, val request: Request)
        : TestNetworkModule.Executor<okhttp3.Response> {
        override fun execute(): okhttp3.Response {
            return chain.proceed(request)
        }
    }

    interface Executor<R> {
        fun execute(): R
    }

    override fun getOkClient(chuckInterceptor: Interceptor, travisInterceptor: Interceptor): OkHttpClient {
        return super.getOkClient(chuckInterceptor, travisInterceptor).newBuilder()
                .addInterceptor(getTestInterceptor())
                .build()
    }

    fun getIdlingResource(): IdlingResource {
        return idlingResource
    }

}