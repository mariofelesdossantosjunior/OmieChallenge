package com.mario.omiechallenge.domain

import com.mario.omiechallenge.domain.model.Order

class AddOrderUseCase(
    private val repository: Repository
) {

    class Params(val order: Order)

    operator fun invoke(
        params: Params
    ){
        return repository.add(
            order = params.order
        )
    }
}