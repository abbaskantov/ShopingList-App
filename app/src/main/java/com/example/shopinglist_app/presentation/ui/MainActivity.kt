package com.example.shopinglist_app.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist_app.R
import com.example.shopinglist_app.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopItemAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            shopItemAdapter.shopItemList = it
        }
    }
    private fun setRecyclerView(){
        val shopItemRecyclerView = findViewById<RecyclerView>(R.id.shopItemRecyclerView)
        shopItemAdapter = MainAdapter()
        with(shopItemRecyclerView) {

            shopItemAdapter = MainAdapter()
            adapter = shopItemAdapter

            recycledViewPool.setMaxRecycledViews(
                MainAdapter.VIEW_TYPE_ENABLED, MainAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                MainAdapter.VIEW_TYPE_DISABLED, MainAdapter.MAX_POOL_SIZE
            )
        }
        shopItemAdapter.onShopItemLongClickListener = {
                viewModel.changeEnabledState(it)
        }
        shopItemAdapter.onShopItemClickListener = {
            Log.d("AAA", it.toString())
        }
    }
}