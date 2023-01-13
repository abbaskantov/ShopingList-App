package com.example.shopinglist_app.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shopinglist_app.data.RepositoryImpl
import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.interactor.*

class MainViewModel:ViewModel() {

    private val repository = RepositoryImpl
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val refactorShopItemUseCase = RefactorShopItemUseCase(repository)


     val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem = shopItem)
    }

    fun changeEnabledState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        refactorShopItemUseCase.refactorShopItem(shopItem = shopItem)
    }

}