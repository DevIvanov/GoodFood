package com.juniorteam.goodfood.ui.screens.recipes


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.data.constants.ApiConstants
import com.juniorteam.data.repository.RecipeRepository
import com.juniorteam.data.repository.SpoonRepositoryImpl
import com.juniorteam.domain.model.Recipe
import com.juniorteam.domain.model.RecipesResponse
import com.juniorteam.domain.model.result.onError
import com.juniorteam.domain.model.result.onSuccess
import com.juniorteam.goodfood.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: SpoonRepositoryImpl,
    private val recipeRepository: RecipeRepository
) : BaseViewModel() {

    private val tag = RecipesViewModel::class.java.simpleName

    private val _query = MutableLiveData<String>().apply {
        value = ApiConstants.DEFAULT_QUERY_RECIPE
    }

    var recipesList by mutableStateOf<List<Recipe>?>(null)
        private set

    private val list = MutableSharedFlow<RecipesResponse>(replay = 1)

    fun getRecipesList() {
        viewModelScope.launchWithLoading {
            repository.getRecipesResults(query = _query.value!!)
                .onSuccess {
                    list.emit(it)
                    recipesList = it.results
                    Log.i(tag, "results = ${it.results}")
                    insertData(recipesList!!)
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
            recipesList = recipeRepository.readAll().first()
        }
    }

    fun deleteData(item: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.delete(item)
        }
    }

    private fun addAllData(list: List<Recipe>) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.insertAll(list)
        }
    }

    private fun deleteAllData() {
        viewModelScope.launch {
            recipeRepository.deleteAll()
        }
    }

    private fun insertData(list: List<Recipe>) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeRepository.deleteAll()
            recipeRepository.insertAll(list)
        }
    }
}