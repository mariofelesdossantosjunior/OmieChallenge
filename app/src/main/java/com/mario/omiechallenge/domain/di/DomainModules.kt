package com.mario.omiechallenge.domain.di

import com.mario.omiechallenge.domain.AddOrderUseCase
import com.mario.omiechallenge.domain.GetAllOrdersUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetAllOrdersUseCase(get())
    }

    factory {
        AddOrderUseCase(get())
    }

}