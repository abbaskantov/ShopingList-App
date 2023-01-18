package com.example.shopinglist_app.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.shopinglist_app.R
import com.example.shopinglist_app.presentation.main_screen.MainViewModel
import com.google.android.material.textfield.TextInputLayout

class ShopItemActivity : AppCompatActivity() {

    private lateinit var fieldName : TextInputLayout
    private lateinit var fieldCount : TextInputLayout
    private lateinit var nameEditText : EditText
    private lateinit var countEditText : EditText
    private lateinit var saveBtn : Button

    private lateinit var viewModel: ShopItemViewModel
    private var screenMode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_item)
        initViews()

        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]

        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        Log.d("ShopItemActivity", mode.toString())
    }

    private fun parseIntent(){
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)){
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
    if (mode != MODE_REFACTOR && mode != MODE_ADD){
        throw RuntimeException("Unknown screen mode: $mode")
    }
        screenMode = mode
    }
    private fun initViews(){
         fieldName = findViewById(R.id.fieldName)
         fieldCount = findViewById(R.id.fieldCount)
         nameEditText = findViewById(R.id.nameEditText)
         countEditText = findViewById(R.id.countEditText)
         saveBtn = findViewById(R.id.addItemBtn)
    }

    companion object{
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_REFACTOR = "mode_refactor"
        private const val MODE_ADD = "mode_add"

        fun newIntentAddItem(context: Context) : Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }
        fun newIntentRefactorItem(context: Context, shopItemId:Int):Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_REFACTOR)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}