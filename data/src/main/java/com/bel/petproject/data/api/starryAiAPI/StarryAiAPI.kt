package com.bel.petproject.data.api.starryAiAPI

import com.bel.petproject.data.models.CreationRequestDTO
import com.bel.petproject.data.models.CreationResponseDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StarryAiAPI {

    @POST("creations/")
    suspend fun newCreation(@Body request: CreationRequestDTO): CreationResponseDTO

    @GET("creations/{creation_id}")
    suspend fun getCreationByID(@Path("creation_id") id: Long): CreationResponseDTO

}