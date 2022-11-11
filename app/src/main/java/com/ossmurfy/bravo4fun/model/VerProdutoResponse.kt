package com.ossmurfy.bravo4fun.model

data class VerProdutoResponse(
    val status: Int,
    val mensagem: String,
    val produto: Produto
)
