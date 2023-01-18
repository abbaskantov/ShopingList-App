package com.example.shopinglist_app.presentation.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist_app.R

class ShopItemViewHolder(
    val view : View
): RecyclerView.ViewHolder(view){
    val tvName  = view.findViewById<TextView>(R.id.textView_name)
    val tvCount  = view.findViewById<TextView>(R.id.textView_count)

}
