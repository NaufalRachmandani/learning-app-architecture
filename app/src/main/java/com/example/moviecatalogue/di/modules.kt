package com.example.moviecatalogue.di

import android.content.Context
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.data.MovieRepository
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.room.MovieDatabase
import com.example.moviecatalogue.data.source.remote.ApiService
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.viewmodel.DetailMovieViewModel
import com.example.moviecatalogue.viewmodel.FavoriteViewModel
import com.example.moviecatalogue.viewmodel.HomeViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//private const val API_KEY = "86bb65de1345ed0d39c7f97d5c18b7d8"

val viewModelModule = module {
    viewModel {
        DetailMovieViewModel(get())
    }
    viewModel {
        FavoriteViewModel(get())
    }
    viewModel {
        HomeViewModel(get())
    }
}

val repositoryModule = module {
    single { AppExecutors() }
    single {
        MovieRepository(get(), get(), get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    single { provideUseApi(get()) }
    single { RemoteDataSource.getInstance(get()) }
}

val localModule = module {
    single { MovieDatabase.getInstance(get()).movieDao() }
    single { LocalDataSource.getInstance(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(context: Context): OkHttpClient {

        return OkHttpClient.Builder().addInterceptor { chain ->
            val url = chain.request().url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()
            val newRequest = chain.request().newBuilder().url(url).build()
            chain.proceed(newRequest)
        }.connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(context)).build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
}