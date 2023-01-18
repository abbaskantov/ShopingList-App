package com.example.shopinglist_app.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.shopinglist_app.domain.entity.ShopItem

class ShopIemDiffCallback:DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}