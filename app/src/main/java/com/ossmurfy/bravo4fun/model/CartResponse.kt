package com.ossmurfy.bravo4fun.model

data class CartResponse(
    var status: Int,
    var message: String,
    var data: List<Cart>
)
