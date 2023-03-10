package com.example.shopinglist_app.domain.interactor

import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.repository.Repository

class AddShopItemUseCase(private val repository: Repository) {
    fun addShopItem(shopItem: ShopItem){
        repository.addShopItem(shopItem = shopItem)
    }
}