package ru.hetfieldan24.otuscoroutines.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import ru.hetfieldan24.otuscoroutines.model.SongsResponse


interface RetrofitAPI {
    @GET("hot/metal?format=json&limit=50/")
    fun getSongsResponse(): Deferred<SongsResponse>
}