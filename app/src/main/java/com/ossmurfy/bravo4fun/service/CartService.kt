package com.ossmurfy.bravo4fun.service

import com.ossmurfy.bravo4fun.model.Cart
import com.ossmurfy.bravo4fun.model.CartResponse
import com.ossmurfy.bravo4fun.model.Produto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CartService {

    //USADO NO PROJETO
    @GET("/api/cart/getCart")
    fun listar(): Call<CartResponse>

    @POST("/api/cart/createCart")
    fun inserir(@Body produto: Produto): Call<Produto>


}