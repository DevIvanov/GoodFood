package com.juniorteam.football.ui.screens.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.juniorteam.data.mapper.mapToCarsList
import com.juniorteam.data.source.remote.api.CarsApi
import com.juniorteam.domain.model.Car
import com.juniorteam.football.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val api: CarsApi
) : BaseViewModel() {

    private val tag = CarViewModel::class.java.simpleName

    val carList = MutableLiveData<List<Car>>().apply {
        value = emptyList()
    }

    fun getCarList() {

        api.getCars().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                carList.postValue(it.mapToCarsList())
            }, {
                Log.e(tag, it.message.toString())
            })
    }
}