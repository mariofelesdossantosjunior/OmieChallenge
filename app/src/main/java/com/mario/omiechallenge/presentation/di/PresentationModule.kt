package com.mario.omiechallenge.presentation.di

import com.mario.omiechallenge.presentation.screen.order.OrderViewModel
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        OrderViewModel(
            getAllOrdersUseCase = get()
        )
    }

    viewModel {
        OrderItemViewModel(
            getAllOrdersUseCase = get(),
            addOrderUseCase = get(),
            updateOrderUseCase = get()
        )
    }

}