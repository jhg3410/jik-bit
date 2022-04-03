package org.inu.jikbit.di.factory

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import org.inu.jikbit.BuildConfig

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
                    .addHeader("Authorization", BuildConfig.AUTHORIZATION_TOKEN).build()
                return chain.proceed(req)
            }
        }
    }
}