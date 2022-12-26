package com.example.shopinglist_app.data

import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.repository.Repository

object RepositoryImpl:Repository{

    private val shopItemList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopItemList.add(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopItemList.find {
            it.id ==  shopItemId
        } ?: throw RuntimeException("Элемент с id $shopItemId не найден")
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
    }

    override fun refactorShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        shopItemList.remove(oldItem)
        addShopItem(shopItem)

    }
    override fun getShopList(): List<ShopItem> {
        return shopItemList.toList()
    }

}