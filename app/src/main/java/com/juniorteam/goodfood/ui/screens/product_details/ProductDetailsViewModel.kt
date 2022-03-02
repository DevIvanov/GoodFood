package com.juniorteam.goodfood.ui.screens.product_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.domain.interactor.ProductInteractor
import com.juniorteam.domain.model.ProductDetails
import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.result.onError
import com.juniorteam.domain.model.result.onSuccess
import com.juniorteam.goodfood.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val interactor: ProductInteractor
) : BaseViewModel() {

    private val tag = ProductDetailsViewModel::class.java.simpleName

    var productState by mutableStateOf<ProductDetails?>(null)
        private set

    fun getProductById(id: String) {
        viewModelScope.launchWithLoading {
            interactor.getProductById(id)
                .onSuccess {
                    productState = it
                    Log.i(tag, "productById = $it")
                }
                .onError {
                    error.emit(it)
                    Log.e(tag, "error = $it")
                }
        }
    }
}