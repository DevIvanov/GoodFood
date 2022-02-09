package com.juniorteam.data.mapper

import com.juniorteam.data.model.entity.CarEntity
import com.juniorteam.domain.mapper.ModelMapper
import com.juniorteam.domain.model.Car
import javax.inject.Inject

class CarModelMapperImpl @Inject constructor() : ModelMapper<CarEntity, Car> {
    override fun fromEntity(from: CarEntity): Car {
        return Car(
            from.id, from.year, from.horsepower, from.make, from.model,
            from.price, from.imageUrl
        )
    }

    override fun toEntity(from: Car): CarEntity {
        return CarEntity(
            from.id, from.year, from.horsepower, from.make, from.model,
            from.price, from.imageUrl
        )
    }
}