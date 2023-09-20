package com.example.examtest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object JokesRepo {

    var jokeService: JokeService? = null

    suspend fun getJokes(): Response<JokeData>? {
        if (jokeService == null){
            jokeService = createRetrofitInstance().create(JokeService::class.java)
        }

        return jokeService?.getJokes()
    }


    fun createRetrofitInstance(): Retrofit {

        val baseUrl = "https://official-joke-api.appspot.com/"

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}