package com.juniorteam.data.mapper

import com.juniorteam.data.model.entity.CarEntity
import com.juniorteam.domain.model.Car

fun CarEntity.mapToCar() = Car(
    id = id,
    year = year,
    horsepower = horsepower,
    make = make,
    model = model,
    price = price,
    imageUrl = imageUrl
)

fun List<CarEntity>.mapToCarsList(): List<Car> {
    return this.map {
        it.mapToCar()
    }
}