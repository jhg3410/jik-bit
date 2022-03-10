package org.inu.jikbit.di

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import org.inu.jikbit.AuthorizationToken
import org.inu.jikbit.data.httpservice.AccountHttpService
import org.inu.jikbit.data.repository.AccountRepository
import org.inu.jikbit.data.repository.AccountRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val AppModule = module {
    single<AccountHttpService> {
        buildRetrofitService()
    }

    single<AccountRepository>{
        AccountRepositoryImpl(httpService = get())
    }
}

inline fun <reified T> buildRetrofitService(): T {
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

class OkHttpClientFactory {
    companion object {
        fun create(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .addInterceptor(AuthInterceptor())
                .build()
        }


        private fun createLoggingInterceptor(): HttpLoggingInterceptor {
            val interceptor = HttpLoggingInterceptor { message ->
                if (!message.contains("�")) {
                    Platform.get().log(message)
                }
            }
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        class AuthInterceptor : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val req = chain.request().newBuilder()
                    .addHeader("Authorization", AuthorizationToken.AUTHORIZATION_TOKEN).build()
                return chain.proceed(req)
            }
        }
    }
}