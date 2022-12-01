package com.ossmurfy.bravo4fun.service

import com.ossmurfy.bravo4fun.fragments.CartFragment
import com.ossmurfy.bravo4fun.model.Cart
import com.ossmurfy.bravo4fun.model.CartResponse
import com.ossmurfy.bravo4fun.model.Produto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface CartService {

    @GET("/api/cart")
    fun listAll(): Call<List<Cart>>

    @POST("/api/cart")
    fun inserir(@Query("PRODUTO_ID") PRODUTO_ID: Int, @Query("ITEM_QTD") ITEM_QTD: Int, @Header ("USUARIO_ID") USUARIO_ID: Int ): Call<CartResponse>

    @GET("/api/cart/{id}")
    fun listCart(@Path("id") id: Int): Call<CartResponse>

}