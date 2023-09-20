package com.example.examtest


import com.google.gson.annotations.SerializedName

data class JokeData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("punchline")
    val punchline: String?,
    @SerializedName("setup")
    val setup: String?,
    @SerializedName("type")
    val type: String?
)