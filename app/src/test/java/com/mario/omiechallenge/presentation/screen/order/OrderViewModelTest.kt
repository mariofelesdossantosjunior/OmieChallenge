package com.mario.omiechallenge.presentation.screen.order

import com.mario.omiechallenge.data.datasource.fakeOrders
import com.mario.omiechallenge.domain.GetAllOrdersUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class OrderViewModelTest {

    @MockK
    lateinit var getAllOrdersUseCase: GetAllOrdersUseCase

    private lateinit var viewModel: OrderViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(instantiateViewModel())
    }

    private fun instantiateViewModel() = OrderViewModel(
        getAllOrdersUseCase
    )

    @Test
    fun `onEvent SHOULD invoke loadOrders`() {
        every {
            viewModel.loadOrders()
        } returns Unit

        viewModel.onEvent(OrderUIEvent.LoadOrders)

        verify {
            viewModel.loadOrders()
        }
    }

    @Test
    fun `loadOrders SHOULD invoke use case`() {
        every {
            getAllOrdersUseCase.invoke()
        } returns Result.success(
            fakeOrders
        )

        viewModel.loadOrders()

        verify {
            getAllOrdersUseCase.invoke()
        }
    }

}