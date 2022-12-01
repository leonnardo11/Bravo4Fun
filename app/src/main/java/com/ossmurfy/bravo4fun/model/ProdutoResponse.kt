package com.ossmurfy.bravo4fun.model

data class ProdutoResponse(
    var status: Int,
    var message: String,
    var data: List<Produto>
)
