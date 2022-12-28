package com.example.shopinglist_app.domain.entity

data class ShopItem(
    val name:String,
    val count:Int,
    val enabled:Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
