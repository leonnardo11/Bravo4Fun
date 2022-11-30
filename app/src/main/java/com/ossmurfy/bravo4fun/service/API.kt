package com.ossmurfy.bravo4fun.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class API {
    private val retrofit: Retrofit
        get() {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
            return Retrofit
                .Builder()
                .baseUrl("https://Laravel-9.leonnardo1588.repl.co")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }

    val produto: ProdutoService
        get() {
            return retrofit.create(ProdutoService::class.java)
        }
    val cart: CartService
        get(){
            return retrofit.create(CartService::class.java)
        }
}