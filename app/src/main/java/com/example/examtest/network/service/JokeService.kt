package com.example.examtest.network.service

import com.example.examtest.model.remote.JokeDataRemote
import retrofit2.Response
import retrofit2.http.GET

interface JokeService {

    @GET("jokes/random")
    suspend fun getJokes(): Response<JokeDataRemote>

}