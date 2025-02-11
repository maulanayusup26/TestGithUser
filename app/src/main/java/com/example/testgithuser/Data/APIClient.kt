package com.example.testgithuser.Data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://api.github.com/"

    fun getClient():Retrofit{
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)  // Set connection timeout
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)     // Set read timeout
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)    // Set write timeout
        okHttpClient.retryOnConnectionFailure(true)

        okHttpClient.addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .header("Accept", "application/vnd.github+json")
                .header("Authorization", "Bearer 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")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(interceptor)

        val client = okHttpClient.build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        return retrofit!!
    }
}