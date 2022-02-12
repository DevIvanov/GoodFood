package com.juniorteam.football.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY
import com.juniorteam.data.source.remote.data.RecipesPagingSource
import com.juniorteam.data.source.remote.data.RecipesRepository
import com.juniorteam.domain.model.Recipe
import com.juniorteam.football.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: RecipesRepository
) : BaseViewModel() {

    private val tag = RecipesViewModel::class.java.simpleName

//    private val _query = MutableLiveData<String>().apply {
//        value = DEFAULT_QUERY
//    }
//
//    val recipeList = _query.switchMap { queryString ->
//        repository.getSearchResults(queryString).cachedIn(viewModelScope)
//    }
//
//
//    fun getRecipeList(query: String) {
//        _query.value = query
//    }

    private val _query = MutableStateFlow(DEFAULT_QUERY)

//    val recipeList = _query.let {  queryString ->
//        repository.getSearchResults(queryString.value).cachedIn(viewModelScope)
//    }

    val recipesList: Flow<PagingData<Recipe>> =
        repository.getSearchResults(query = _query.value).cachedIn(viewModelScope)


    fun getRecipeList(query: String) {
        _query.value = query
    }
}