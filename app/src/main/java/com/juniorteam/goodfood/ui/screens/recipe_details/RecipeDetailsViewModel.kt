package com.juniorteam.goodfood.ui.screens.recipe_details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.domain.interactor.RecipeInteractor
import com.juniorteam.domain.model.Recipe
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
class RecipeDetailsViewModel @Inject constructor(
    private val interactor: RecipeInteractor
) : BaseViewModel() {

    private val tag = RecipeDetailsViewModel::class.java.simpleName

    var recipeState by mutableStateOf<RecipeDetails?>(null)
        private set

    fun getRecipeById(id: String) {
        viewModelScope.launchWithLoading {
            interactor.getRecipeById(id)
                .onSuccess {
                    recipeState = it
                    Log.i(tag, "recipe = $it")
                }
                .onError {
                    error.emit(it)
                    Log.e(tag, "error = $it")
                }
        }
    }

    fun getHeaders() {
        viewModelScope.launch(Dispatchers.IO) {
            OkHttp().run()
        }
    }
}