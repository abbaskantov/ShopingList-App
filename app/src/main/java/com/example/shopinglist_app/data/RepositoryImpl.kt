package com.example.shopinglist_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopinglist_app.domain.entity.ShopItem
import com.example.shopinglist_app.domain.repository.Repository

object RepositoryImpl:Repository{

    private val shopItemList = mutableListOf<ShopItem>()
    private var autoIncrementId = 0

    private val shopListLD = MutableLiveData<List<ShopItem>>()

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopItemList.add(shopItem)
        updateList()

    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopItemList.find {
            it.id ==  shopItemId
        } ?: throw RuntimeException("Элемент с id $shopItemId не найден")
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopItemList.remove(shopItem)
        updateList()
    }

    override fun refactorShopItem(shopItem: ShopItem) {
        val oldItem = getShopItem(shopItem.id)
        shopItemList.remove(oldItem)
        addShopItem(shopItem)

    }
    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList(){
        shopListLD.value = shopItemList.toList()
    }

}