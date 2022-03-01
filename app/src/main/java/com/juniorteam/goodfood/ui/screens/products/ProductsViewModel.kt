package com.juniorteam.goodfood.ui.screens.products

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY_PRODUCT
import com.juniorteam.domain.interactor.ProductInteractor
import com.juniorteam.domain.interactor.database.ProductDBInteractor
import com.juniorteam.domain.model.Product
import com.juniorteam.domain.model.ProductsResponse
import com.juniorteam.domain.model.result.onError
import com.juniorteam.domain.model.result.onSuccess
import com.juniorteam.goodfood.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productDBInteractor: ProductDBInteractor,
    private val productInteractor: ProductInteractor
) : BaseViewModel() {

    private val tag = ProductsViewModel::class.java.simpleName

    private val _query = MutableLiveData<String>().apply {
        value = DEFAULT_QUERY_PRODUCT
    }

    var productList by mutableStateOf<List<Product>?>(null)
        private set

    private val list = MutableSharedFlow<ProductsResponse>(replay = 1)

    fun getProductList() {
        viewModelScope.launchWithLoading {
            productInteractor.getProductList(query = _query.value!!)
                .onSuccess {
                    list.emit(it)
                    productList = it.products
                    Log.i(tag, "products = ${it.products}")
                    insertData(productList!!)
                }
                .onError {
                    error.emit(it)
                    Log.e(tag, it.toString())
                }
        }
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    fun readAllData() {
        viewModelScope.launch {
            productList = productDBInteractor.readAll().first()
        }
    }

    fun deleteData(item: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productDBInteractor.delete(item)
        }
    }

    private fun addAllData(list: List<Product>) {
        viewModelScope.launch(Dispatchers.IO) {
            productDBInteractor.insertAll(list)
        }
    }

    private fun deleteAllData() {
        viewModelScope.launch {
            productDBInteractor.deleteAll()
        }
    }

    private fun insertData(list: List<Product>) {
        viewModelScope.launch(Dispatchers.IO) {
            productDBInteractor.deleteAll()
            productDBInteractor.insertAll(list)
        }
    }
}