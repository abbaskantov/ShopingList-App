package com.example.shopinglist_app.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shopinglist_app.R
import com.example.shopinglist_app.domain.entity.ShopItem

class MainAdapter:ListAdapter<ShopItem, ShopItemViewHolder>(ShopIemDiffCallback()) {

      var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
      var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when(viewType){
            VIEW_TYPE_ENABLED -> R.layout.shop_enabled_item
            VIEW_TYPE_DISABLED -> R.layout.shop_disabled_item
            else ->  throw RuntimeException("неизвестный тип: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        viewHolder.view.setOnClickListener{
            onShopItemClickListener?.invoke(shopItem)
        }
        viewHolder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }
        viewHolder.tvName.text = shopItem.name
        viewHolder.tvCount.text = shopItem.count.toString()
    }
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }
      companion object{
          const val VIEW_TYPE_ENABLED = 0
          const val VIEW_TYPE_DISABLED = 1
          const val MAX_POOL_SIZE = 20
      }
  }