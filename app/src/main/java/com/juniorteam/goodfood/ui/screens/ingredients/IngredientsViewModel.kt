package com.juniorteam.goodfood.ui.screens.ingredients

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY_INGREDIENT
import com.juniorteam.domain.interactor.IngredientInteractor
import com.juniorteam.domain.interactor.database.IngredientDBInteractor
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.model.IngredientsResponse
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
class IngredientsViewModel @Inject constructor(
    private val ingredientDBInteractor: IngredientDBInteractor,
    private val ingredientInteractor: IngredientInteractor
) : BaseViewModel() {

    private val tag = IngredientsViewModel::class.java.simpleName

    private val _query = MutableLiveData<String>().apply {
        value = DEFAULT_QUERY_INGREDIENT
    }

    var ingredientList by mutableStateOf<List<Ingredient>?>(null)
        private set

    private val list = MutableSharedFlow<IngredientsResponse>(replay = 1)

    fun getIngredientList() {
        viewModelScope.launchWithLoading {
            ingredientInteractor.getIngredientList(query = _query.value!!)
                .onSuccess {
                    list.emit(it)
                    ingredientList = it.results
                    Log.i(tag, "results = ${it.results}")
                    insertData(ingredientList!!)
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
            ingredientList = ingredientDBInteractor.readAll().first()
        }
    }

    fun deleteData(item: Ingredient) {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientDBInteractor.delete(item)
        }
    }

    private fun addAllData(list: List<Ingredient>) {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientDBInteractor.insertAll(list)
        }
    }

    private fun deleteAllData() {
        viewModelScope.launch {
            ingredientDBInteractor.deleteAll()
        }
    }

    private fun insertData(list: List<Ingredient>) {
        viewModelScope.launch(Dispatchers.IO) {
            ingredientDBInteractor.deleteAll()
            ingredientDBInteractor.insertAll(list)
        }
    }
}