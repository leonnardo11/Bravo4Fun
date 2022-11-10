package com.ossmurfy.bravo4fun.service


import com.ossmurfy.bravo4fun.model.Produto
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface ProdutoService {

    @GET("/api/product")
    fun listAll(): Call<List<Produto>>

    //list all aa
    @GET("/api/product/{id)")
    fun list(@Path("/id") id: Int): Call<Produto>

}