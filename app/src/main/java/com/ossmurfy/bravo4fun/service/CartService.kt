package com.ossmurfy.bravo4fun.service

import com.ossmurfy.bravo4fun.model.Cart
import com.ossmurfy.bravo4fun.model.Produto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartService {

    @GET("/api/cart")
    fun listAll(): Call<List<Cart>>

    @POST("/api/cart/produto")
    fun inserir(@Body produto: Produto): Call<Produto>

}