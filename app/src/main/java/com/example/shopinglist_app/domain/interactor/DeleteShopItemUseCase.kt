package com.example.shopinglist_app.domain.interactor

import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.repository.Repository

class DeleteShopItemUseCase(private val repository: Repository) {
   fun deleteShopItem(shopItem: ShopItem){
        repository.deleteShopItem(shopItem = shopItem)

    }
}