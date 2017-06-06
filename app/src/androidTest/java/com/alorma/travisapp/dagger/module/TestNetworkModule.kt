package com.alorma.travisapp.dagger.module

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.core.deps.dagger.Provides
import android.support.test.espresso.idling.CountingIdlingResource
import okhttp3.Interceptor
import okhttp3.OkHttpClient

open class TestNetworkModule(val port: Int) : NetworkModule() {
    private val idlingResource = CountingIdlingResource("OkHttpClient")

    fun getTestInterceptor(): Interceptor {
        return Interceptor { chain ->
            executeIdle(ExecutorImpl(chain, port))
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

    class ExecutorImpl(val chain: Interceptor.Chain, val port: Int)
        : TestNetworkModule.Executor<okhttp3.Response> {

        private val SCHEME = "http"
        private val HOST = "localhost"

        override fun execute(): okhttp3.Response {
            val requestBuilder = chain.request().newBuilder()

            val newUrl = chain.request().url().newBuilder()
                    .scheme(SCHEME)
                    .host(HOST)
                    .port(port)
                    .build()

            val request = requestBuilder.url(newUrl).build()

            return chain.proceed(request)
        }
    }

    interface Executor<out R> {
        fun execute(): R
    }

    @Provides
    override fun getOkClient(chuckInterceptor: Interceptor, travisInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(getTestInterceptor())
                .addInterceptor(chuckInterceptor)
                .build()
    }

    fun getIdlingResource(): IdlingResource {
        return idlingResource
    }

}