package com.example.shopinglist_app.domain.interactor

import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.repository.Repository

class RefactorShopItemUseCase(private val repository: Repository) {
    fun refactorShopItem(shopItem: ShopItem){
        repository.refactorShopItem(shopItem = shopItem)

    }
}