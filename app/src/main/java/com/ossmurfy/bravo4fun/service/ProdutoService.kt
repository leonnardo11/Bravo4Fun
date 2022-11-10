package com.ossmurfy.bravo4fun.service


import com.ossmurfy.bravo4fun.model.Produto

import retrofit2.http.GET

interface ProdutoService {

    @GET("/android/rest/produto")
    fun listar(): retrofit2.Call<List<Produto>>
}