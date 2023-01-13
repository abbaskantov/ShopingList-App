package com.example.shopinglist_app.presentation.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist_app.R
import com.example.shopinglist_app.domain.entity.ShopItem

  class MainAdapter:RecyclerView.Adapter<ShopItemViewHolder>() {

      var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
      var onShopItemClickListener: ((ShopItem) -> Unit)? = null

      companion object{
          const val VIEW_TYPE_ENABLED = 0
          const val VIEW_TYPE_DISABLED = 1
          const val MAX_POOL_SIZE = 20
      }


      var shopItemList = listOf<ShopItem>()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    } 

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
        val shopItem = shopItemList[position]

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

    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""
        viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.view.context, android.R.color.white))
    }

    override fun getItemViewType(position: Int): Int {
        val item = shopItemList[position]
        return if(item.enabled){
            VIEW_TYPE_ENABLED
        } else{
            VIEW_TYPE_DISABLED
        }
    }

    override fun getItemCount(): Int =
        shopItemList.size

      interface OnShopItemClickListener{
          fun onShopItemClick(shopItem: ShopItem)
      }

  }
   class ShopItemViewHolder(
    val view : View
    ):RecyclerView.ViewHolder(view){
    val tvName  = view.findViewById<TextView>(R.id.textView_name)
    val tvCount  = view.findViewById<TextView>(R.id.textView_count)



}
