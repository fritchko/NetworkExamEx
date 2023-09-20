package com.example.examtest.network.repositories

import com.example.examtest.model.local.JokeDataLocal
import com.example.examtest.model.remote.toJokeDataLocal
import com.example.examtest.network.ResponseWrapper
import com.example.examtest.network.service.JokeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object JokesRepo {

    var jokeService: JokeService? = null

    suspend fun getJokes(): ResponseWrapper<JokeDataLocal>? {
        if (jokeService == null) {
            jokeService = createRetrofitInstance().create(JokeService::class.java)
        }

        val response = jokeService?.getJokes()

        return response?.toJokeDataLocal()


    }


    fun createRetrofitInstance(): Retrofit {

        val baseUrl = "https://official-joke-api.appspot.com/"

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}