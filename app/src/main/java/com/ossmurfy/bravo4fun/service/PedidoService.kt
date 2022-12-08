package com.ossmurfy.bravo4fun.service

import com.ossmurfy.bravo4fun.model.CartResponse
import com.ossmurfy.bravo4fun.model.PedidoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PedidoService {

    @POST("/api/orders")
    fun inserirPedido(@Header("USUARIO_ID") USUARIO_ID: Int ): Call<PedidoResponse>

    @GET("/api/orders")
    fun listPedidos(@Header ("USUARIO_ID") USUARIO_ID: Int): Call<PedidoResponse>

}