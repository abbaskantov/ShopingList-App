package com.example.shopinglist_app.domain.interactor

import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.repository.Repository

class GetShopListUseCase(private val repository: Repository) {
     fun getShopList():List<ShopItem>{
            return repository.getShopList()
    }
}