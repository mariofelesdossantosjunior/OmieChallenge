package com.mario.omiechallenge.domain

import com.mario.omiechallenge.domain.model.Order

class UpdateOrderUseCase(
    private val repository: Repository
) {

    class Params(val order: Order)

    operator fun invoke(
        params: Params
    ){
        return repository.update(
            order = params.order
        )
    }
}