package com.ossmurfy.bravo4fun.model

data class Cart(
    var PRODUTO_ID: Int,
    var USUARIO_ID: Int,
    var ITEM_QTD: Int,
    var PRODUTO_NOME: String,
    var PRODUTO_PRECO: Double,
    var PRODUTO_PRECO_TOTAL: Double,
)


