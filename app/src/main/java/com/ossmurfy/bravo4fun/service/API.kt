package com.ossmurfy.bravo4fun.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    private val retrofit: Retrofit
        get() {
            return Retrofit
                .Builder()
                .baseUrl("https://Bravo4FunWeb-leonnardo1588.bravo4fun-smurfies.repl.co")
                .addConverterFactory(GsonConverterFactory.create())
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