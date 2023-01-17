package org.csystem.android.app.veterinarian.api

import org.csystem.android.app.veterinarian.api.data.entity.CountInfo
import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianSave
import org.csystem.android.app.veterinarian.api.data.entity.VeterinariansInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface IVeterinarianService {
    @GET("/api/read/vets/lastname")
    fun findByLastName(@Query("n")lastName: String) : Call<VeterinariansInfo>

    @GET("/api/read/vets/count")
    fun count() : Call<CountInfo>

    @POST("/api/update/vets/vet/save")
    fun save(@Body veterinarian: VeterinarianSave) : Call<VeterinarianSave>
}