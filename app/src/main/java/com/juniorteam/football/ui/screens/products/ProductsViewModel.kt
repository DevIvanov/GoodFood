package com.juniorteam.football.ui.screens.products

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juniorteam.data.constants.ApiConstants.DEFAULT_QUERY_PRODUCT
import com.juniorteam.data.repository.SpoonRepositoryImpl
import com.juniorteam.domain.model.Product
import com.juniorteam.football.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val repository: SpoonRepositoryImpl
) : BaseViewModel() {

    private val _query = MutableStateFlow(DEFAULT_QUERY_PRODUCT)

    val productList: Flow<PagingData<Product>> =
        repository.getProductsResults(query = _query.value).cachedIn(viewModelScope)
}