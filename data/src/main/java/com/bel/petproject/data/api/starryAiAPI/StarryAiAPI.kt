package com.bel.petproject.data.api.starryAiAPI

import com.bel.petproject.data.model.CreationRequestDTO
import com.bel.petproject.data.model.CreationResponseDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface StarryAiAPI {

    @POST("creations/")
    fun createImage(@Body request: CreationRequestDTO): CreationResponseDTO

    @GET("creations/{id}")
    fun getCreation(@Path("id") id: String): CreationResponseDTO

}