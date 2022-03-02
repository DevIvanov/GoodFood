package com.juniorteam.goodfood.ui.screens.ingredient_details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.domain.interactor.IngredientInteractor
import com.juniorteam.domain.interactor.RecipeInteractor
import com.juniorteam.domain.model.IngredientDetails
import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.result.onError
import com.juniorteam.domain.model.result.onSuccess
import com.juniorteam.goodfood.base.BaseViewModel
import com.juniorteam.goodfood.di.OkHttp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientDetailsViewModel @Inject constructor(
    private val interactor: IngredientInteractor
) : BaseViewModel() {

    private val tag = IngredientDetailsViewModel::class.java.simpleName

    val ingredient = MutableLiveData<IngredientDetails>().apply {
        value = IngredientDetails(
            id = 2237
        )
    }

    fun getIngredientById(id: String) {
        viewModelScope.launchWithLoading {
            interactor.getIngredientById(id)
                .onSuccess {
                    ingredient.value = it
                    Log.i(tag, "ingredient = $it")
                }
                .onError {
                    error.emit(it)
                    Log.e(tag, "error = $it")
                }
        }
    }
}