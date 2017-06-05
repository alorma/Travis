package com.alorma.travisapp.dagger.module

import android.content.Context
import com.alorma.travisapp.data.account.AccountTokenProvider
import com.alorma.travisapp.data.network.TravisEndpoints
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class NetworkModule {

    private val ENDPOINT: String = "api.travis-ci.org"

    @Singleton
    @Provides
    @Named("chuck")
    fun getChuckInterceptor(context: Context): Interceptor {
        return ChuckInterceptor(context)
    }

    @Singleton
    @Provides
    @Named("travis")
    fun getTravisInterceptor(accountTokenProvider: AccountTokenProvider): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder()
                    .addHeader("User-Agent", "TravisApp")
                    .addHeader("Accept", "application/vnd.travis-ci.2+json")

            accountTokenProvider.getToken().let { builder.addHeader("Authorization", "token " + it) }
            chain.proceed(builder.build())
        }
    }

    @Singleton
    @Provides
    open fun getOkClient(@Named("chuck") chuckInterceptor: Interceptor,
                         @Named("travis") travisInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(chuckInterceptor)
                .addInterceptor(travisInterceptor)
                .build()
    }

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(okHttpClient)
                .build()
    }

    @Singleton
    @Provides
    fun getEndpoints(retrofit: Retrofit): TravisEndpoints {
        return retrofit.create(TravisEndpoints::class.java)
    }
}