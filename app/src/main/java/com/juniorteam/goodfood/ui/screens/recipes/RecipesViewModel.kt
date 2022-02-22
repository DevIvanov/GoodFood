package com.juniorteam.goodfood.ui.screens.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juniorteam.data.constants.ApiConstants
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY_INGREDIENT
import com.juniorteam.data.repository.SpoonRepositoryImpl
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.domain.model.Recipe
import com.juniorteam.goodfood.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val repository: SpoonRepositoryImpl
) : BaseViewModel() {

    private val _query = MutableLiveData<String>().apply {
        value = ApiConstants.DEFAULT_QUERY_RECIPE
    }

    fun getRecipesList(): Flow<PagingData<Recipe>> =
        repository.getRecipesResults(query = _query.value!!).cachedIn(viewModelScope)


    fun setQuery(query: String) {
        _query.value = query
    }
}