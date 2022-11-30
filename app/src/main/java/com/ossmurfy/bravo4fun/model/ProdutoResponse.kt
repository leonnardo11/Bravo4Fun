package com.ossmurfy.bravo4fun.model

data class ProdutoResponse(
    var status: Int,
    var mensagem: String,
    var produtos: List<Produto>
)
