package com.example.shopinglist_app.domain.interactor

import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.repository.Repository

class GetShopItemUseCase(private val repository: Repository) {
    fun getShopItem(shopItemId: Int): ShopItem {
        return repository.getShopItem(shopItemId)
    }


}