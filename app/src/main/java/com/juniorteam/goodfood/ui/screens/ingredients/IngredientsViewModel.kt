package com.juniorteam.goodfood.ui.screens.ingredients

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY_RECIPE
import com.juniorteam.data.repository.SpoonRepositoryImpl
import com.juniorteam.domain.model.Ingredient
import com.juniorteam.goodfood.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    private val repository: SpoonRepositoryImpl
) : BaseViewModel() {

    private val _query = MutableLiveData<String>().apply {
        value = DEFAULT_QUERY_RECIPE
    }

    fun getIngredientList(): Flow<PagingData<Ingredient>> =
        repository.getIngredientsResults(query = _query.value!!).cachedIn(viewModelScope)


    fun setQuery(query: String) {
        _query.value = query
    }
}