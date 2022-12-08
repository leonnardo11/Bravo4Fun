package com.ossmurfy.bravo4fun.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class API {
    private val retrofit: Retrofit
        get() {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS)
                .build()
            return Retrofit
                .Builder()
                .baseUrl("https://bravo4fun-2.leonnardo1588.repl.co")
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

    val pedido: PedidoService
        get(){
            return retrofit.create(PedidoService::class.java)
        }
}