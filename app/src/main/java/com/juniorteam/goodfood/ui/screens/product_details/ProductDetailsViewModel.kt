package com.juniorteam.goodfood.ui.screens.product_details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.domain.interactor.ProductInteractor
import com.juniorteam.domain.model.ProductDetails
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

    val product = MutableLiveData<ProductDetails>().apply {
        value = ProductDetails(
            id = 22374,
//            glutenFree = true,
//            image = "https://spoonacular.com/productImages/1.jpg",
//            readyInMinutes = 24,
//            sourceUrl = null,
//            title = "Eggs",
//            vegan = false,
//            vegetarian = true,
//            product = null,
//            winePairing = null
        )
    }

    fun getProductById(id: String) {
        viewModelScope.launchWithLoading {
            interactor.getProductById(id)
                .onSuccess {
                    product.value = it
                    Log.i(tag, "product = $it")
                }
                .onError {
                    error.emit(it)
                    Log.e(tag, "error = $it")
                }
        }
    }
}