package com.juniorteam.goodfood.ui.screens.recipes


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.juniorteam.data.constants.ApiConstants
import com.juniorteam.domain.interactor.RecipeInteractor
import com.juniorteam.domain.interactor.database.RecipeDBInteractor
import com.juniorteam.domain.model.Recipe
import com.juniorteam.domain.model.RecipesResponse
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
class RecipesViewModel @Inject constructor(
    private val recipeDBInteractor: RecipeDBInteractor,
    private val recipeInteractor: RecipeInteractor
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
            recipeInteractor.getRecipeList(query = _query.value!!)
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
            recipesList = recipeDBInteractor.readAll().first()
        }
    }

    fun deleteData(item: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDBInteractor.delete(item)
        }
    }

    private fun addAllData(list: List<Recipe>) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDBInteractor.insertAll(list)
        }
    }

    private fun deleteAllData() {
        viewModelScope.launch {
            recipeDBInteractor.deleteAll()
        }
    }

    private fun insertData(list: List<Recipe>) {
        viewModelScope.launch(Dispatchers.IO) {
            recipeDBInteractor.deleteAll()
            recipeDBInteractor.insertAll(list)
        }
    }

//    private var page: Int = 1
//    val recipesLists = MutableLiveData<List<Recipe>>()
//    val showProgress = MutableLiveData<Boolean>()
//    val requestError = MutableLiveData<String>()
//
//    fun getPhotos() {
//        showProgress.postValue(true)
//        CoroutineScope(Dispatchers.IO).launch {
//            try {
//                val call =
//                    repository.getRecipesResults(query)
//                if (call.isSuccessful) {
//                    val photos = call.body()
//                    page += 1
//                    recipesLists.postValue(ArrayList())
//                    recipesLists.postValue(photos!!)
//                } else {
//                    requestError.postValue(RequestError.getByValue(0).toString())
//                }
//                showProgress.postValue(false)
//            }catch (e: Throwable) {
//                requestError.postValue(RequestError.getByValue().toString())
//                showProgress.postValue(false)
//            }
//        }
//    }
}
