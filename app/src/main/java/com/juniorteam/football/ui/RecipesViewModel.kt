package com.juniorteam.football.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY
import com.juniorteam.data.source.remote.data.RecipesRepository
import com.juniorteam.domain.model.Recipe
import com.juniorteam.football.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: RecipesRepository
) : BaseViewModel() {

    private val tag = RecipesViewModel::class.java.simpleName

    private val _query = MutableLiveData<String>().apply {
        value = DEFAULT_QUERY
    }

//    val recipeList = _query.switchMap { queryString ->
//        repository.getSearchResults(queryString).cachedIn(viewModelScope)
//    }
    val recipesList = _query.switchMap { queryString ->
        repository.getRecipes(queryString)
}


    fun getRecipeList(query: String) {
        _query.value = query
    }
}