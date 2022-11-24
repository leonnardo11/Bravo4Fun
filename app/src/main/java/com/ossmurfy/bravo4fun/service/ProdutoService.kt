package com.ossmurfy.bravo4fun.service


import com.ossmurfy.bravo4fun.model.Produto
import com.ossmurfy.bravo4fun.model.ProdutoResponse
import com.ossmurfy.bravo4fun.model.VerProdutoResponse
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface ProdutoService {

    @GET("/api/products")
    fun listAll(): Call<ProdutoResponse>

    //list all aa
    @GET("/api/products/{id}")
    fun list(@Path("id") id: Int): Call<VerProdutoResponse>

}