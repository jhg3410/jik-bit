package org.inu.jikbit.di.factory

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceFactory{
    companion object{
        inline fun <reified T> create(): T {
            return Retrofit.Builder()
                .baseUrl("https://api.upbit.com/")
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().serializeNulls().create()
                    )
                )
                .client(OkHttpClientFactory.create())
                .build()
                .create(T::class.java)
        }
    }
}
