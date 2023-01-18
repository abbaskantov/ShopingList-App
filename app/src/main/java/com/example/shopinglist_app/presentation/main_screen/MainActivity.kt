package com.example.shopinglist_app.presentation.main_screen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shopinglist_app.R
import com.example.shopinglist_app.presentation.ShopItemActivity
import com.example.shopinglist_app.presentation.ui.MainAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopItemAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()

        val addFloatBtn = findViewById<FloatingActionButton>(R.id.AddFloatingActionButton)

        addFloatBtn.setOnClickListener {
            val intent = ShopItemActivity.newIntentAddItem(this)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            shopItemAdapter.submitList(it)
        }
    }
    private fun setRecyclerView(){
        val shopItemRecyclerView = findViewById<RecyclerView>(R.id.shopItemRecyclerView)
        shopItemAdapter = MainAdapter()
        with(shopItemRecyclerView) {

            recycledViewPool.setMaxRecycledViews(
                MainAdapter.VIEW_TYPE_ENABLED, MainAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                MainAdapter.VIEW_TYPE_DISABLED, MainAdapter.MAX_POOL_SIZE
            )
            shopItemAdapter = MainAdapter()
            adapter = shopItemAdapter
        }
        setupItemLongClick()
        setupItemClick()
        setupDeleteItem(shopItemRecyclerView)
    }

    private fun setupItemLongClick() {
        shopItemAdapter.onShopItemLongClickListener = {
            viewModel.changeEnabledState(it)
        }
    }

    private fun setupItemClick() {
        shopItemAdapter.onShopItemClickListener = {
            val intent = ShopItemActivity.newIntentRefactorItem(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupDeleteItem(shopItemRecyclerView: RecyclerView?) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopItemAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(shopItemRecyclerView)
    }
}