package com.example.examtest

import retrofit2.Response
import retrofit2.http.GET

interface JokeService {

    @GET("jokes/random")
    suspend fun getJokes(): Response<JokeDataRemote>

}