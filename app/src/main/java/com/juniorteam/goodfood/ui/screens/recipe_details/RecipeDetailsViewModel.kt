package com.juniorteam.goodfood.ui.screens.recipe_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.data.repository.SpoonRepositoryImpl
import com.juniorteam.domain.model.RecipeDetails
import com.juniorteam.domain.model.result.onError
import com.juniorteam.domain.model.result.onSuccess
import com.juniorteam.goodfood.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
//    private val useCase: GetRecipeByIdUseCase
    private val repository: SpoonRepositoryImpl
) : BaseViewModel() {

    private val tag = RecipeDetailsViewModel::class.java.simpleName

//    val recipe = MutableSharedFlow<RecipeDetails>() //replay = 1
//
//    fun getRecipeById(id: String) {
//        viewModelScope.launchWithLoading {
//            repository.getRecipeById(id)
//                .onSuccess {
//                    recipe.emit(it)
//                    Log.i(tag, "recipe = $it")
//                }
//                .onError {
//                    error.emit(it)
//                    Log.e(tag, "error = $it")}
//        }
//    }

    val recipe = MutableLiveData<RecipeDetails>().apply {
        value = RecipeDetails(
            id = 22374,
            glutenFree = true,
            image = "https://spoonacular.com/productImages/1.jpg",
            readyInMinutes = 24,
            sourceUrl = null,
            title = "Eggs",
            vegan = false,
            vegetarian = true,
            product = null,
            winePairing = null
        )
    }


    fun getRecipeById(id: String) {
        viewModelScope.launchWithLoading {
            repository.getRecipeById(id)
                .onSuccess {
                    recipe.value = it
                    Log.i(tag, "recipe = $it")
                }
                .onError {
                    error.emit(it)
                    Log.e(tag, "error = $it")}
        }
    }
}