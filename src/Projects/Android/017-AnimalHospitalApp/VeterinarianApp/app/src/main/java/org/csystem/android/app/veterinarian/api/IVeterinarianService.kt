package org.csystem.android.app.veterinarian.api

import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianSave
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IVeterinarianService {
    @GET("/api/read/vets/lastname")
    fun findByLastName(lastName: String)

    @GET("/api/read/vets/count")
    fun count() : Call<Long>

    @POST("/api/update/vets/vet/save")
    fun save(@Body veterinarian: VeterinarianSave) : Call<VeterinarianSave>
}