package com.example.examtest


import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class JokeDataRemote(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("punchline")
    val punchline: String?,
    @SerializedName("setup")
    val setup: String?,
    @SerializedName("type")
    val type: String?
)

fun Response<JokeDataRemote>.toJokeDataLocal(): ResponseWrapper<JokeDataLocal> {
    return if( this.isSuccessful ){
        val response = this.body()
        ResponseWrapper.Success(JokeDataLocal(response?.punchline,response?.setup))
    } else{
        ResponseWrapper.Error(this.errorBody()?.string().orEmpty(),this.code())
    }
}