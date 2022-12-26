package com.example.shopinglist_app.domain.entity

data class ShopItem(
    val id:Int,
    val count:Int,
    val name:String,
    val enabled:Boolean
)