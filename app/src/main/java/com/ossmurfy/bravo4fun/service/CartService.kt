package com.ossmurfy.bravo4fun.service

import com.ossmurfy.bravo4fun.model.Cart
import retrofit2.Call
import retrofit2.http.GET

interface CartService {

    //USADO NO PROJETO
    @GET("/api/cart")
    fun listAll(): Call<List<Cart>>

}