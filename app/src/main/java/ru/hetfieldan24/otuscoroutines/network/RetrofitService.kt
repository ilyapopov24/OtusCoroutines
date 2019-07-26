package ru.hetfieldan24.otuscoroutines.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService private constructor() {
    private val mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun getJSONApi(): RetrofitAPI {
        return mRetrofit.create(RetrofitAPI::class.java)
    }

    companion object {
        private const val BASE_URL = "https://openwhyd.org/"

        @Volatile
        private var INSTANCE: RetrofitService? = null

        fun getInstance(): RetrofitService {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = RetrofitService()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}