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
import okhttp3.Headers
import okhttp3.internal.http2.Header
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException
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

    var headerLeftQuota by mutableStateOf("")
        private set


    fun getRecipesList() {
        viewModelScope.launchWithLoading {
            val call = recipeInteractor.getRecipeList(query = _query.value!!)
            call.enqueue(object : Callback<RecipesResponse> {
                override fun onResponse(
                    call: Call<RecipesResponse>,
                    response: Response<RecipesResponse>
                ) {
                    val headers: Headers = response.headers()
                    Log.e(tag, "header ${headers.get("x-api-quota-left")}")
                    headerLeftQuota = headers.get("x-api-quota-left")?: ""
                    Log.i(tag, "response.headers(): ${response.headers()}")
                    try {
                        recipesList = response.body()!!.results
                    }catch (e: NullPointerException){
                        Log.e(tag, "response.body() ${e.message}")
                    }
                }

                override fun onFailure(call: Call<RecipesResponse>, t: Throwable) {
                    Log.e(tag, "error = ${t.message.toString()}")
                }
            })
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
}
