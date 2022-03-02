package com.juniorteam.goodfood.ui.screens.ingredient_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var ingredientState by mutableStateOf<IngredientDetails?>(null)
        private set

    fun getIngredientById(id: String) {
        viewModelScope.launchWithLoading {
            interactor.getIngredientById(id)
                .onSuccess {
                    ingredientState = it
                    Log.i(tag, "ingredient = $it")
                }
                .onError {
                    error.emit(it)
                    Log.e(tag, "error = $it")
                }
        }
    }
}