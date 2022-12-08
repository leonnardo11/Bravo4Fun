package com.ossmurfy.bravo4fun.model

data class PedidoItem(
    var ITEM_PRECO: String,
    var ITEM_QTD: Int,
    var PRODUTO: List<ProdutosPedidoResponse>
)
