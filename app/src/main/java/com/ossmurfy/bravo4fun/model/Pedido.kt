package com.ossmurfy.bravo4fun.model

data class Pedido(
    var PEDIDO_ID: Int,
    var STATUS_PEDIDO: Int,
    var PEDIDO_DATA: String,
    var ITEM: List<PedidoItem>
)
