package com.example.shopinglist_app.domain.repository
import com.example.shopinglist_app.domain.entity.ShopItem

interface Repository {
    fun addShopItem(shopItem: ShopItem) // добавление объекта
    fun getShopItem(shopItemId:Int): ShopItem // получение объекта по id
    fun deleteShopItem(shopItem: ShopItem) //удаление объекта
    fun refactorShopItem(shopItem: ShopItem) // редактирование объекта
    fun getShopList():List<ShopItem> // получение списка объектов




}