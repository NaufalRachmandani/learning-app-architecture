package com.example.moviecatalogue.data.source.remote

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {
    companion object {

        private const val API_KEY = "86bb65de1345ed0d39c7f97d5c18b7d8"

        fun getApiService(ctx: Context): ApiService {

            val client = OkHttpClient.Builder().addInterceptor { chain ->
                val url = chain.request().url().newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build()
                val newRequest = chain.request().newBuilder().url(url).build()
                chain.proceed(newRequest)
            }.connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(ChuckInterceptor(ctx)).build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}