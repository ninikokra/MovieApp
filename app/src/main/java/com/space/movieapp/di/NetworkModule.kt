package com.space.movieapp.di

import com.space.movieapp.data.remote.network_utils.NetworkKeys
import com.space.movieapp.data.remote.service.ServiceApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createInterceptor())
        .build()
}

private fun createRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(NetworkKeys.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createOkHttpClient())
        .build()
}

private fun createInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Authorization", NetworkKeys.API_KEY_TOKEN)
            .build()
        chain.proceed(request)
    }
}

val networkModule = module {
    single { createRetrofit() }
    single { get<Retrofit>().create(ServiceApi::class.java) }
}