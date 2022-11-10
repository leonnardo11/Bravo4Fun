package com.ossmurfy.bravo4fun.service


import com.ossmurfy.bravo4fun.model.Produto

import retrofit2.http.GET

interface ProdutoService {

    @GET("/api/cart")
    fun listar(): Call<List<Produto>>

}