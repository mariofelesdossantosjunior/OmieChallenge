package com.mario.omiechallenge.domain

import com.mario.omiechallenge.data.datasource.fakeOrders
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateOrderUseCaseTest {

    @MockK
    lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(
            this,
            relaxUnitFun = true
        )
    }

    @Test
    fun `SHOULD call correct functions WHEN invoke is called`() = runBlocking {
        every {
            repository.update(any())
        } returns Unit

        val fakeOrder = fakeOrders.first().copy(
            clientName = "New Name"
        )
        val params = UpdateOrderUseCase.Params(fakeOrder)

        UpdateOrderUseCase(
            repository
        ).invoke(params)

        verify {
            repository.update(any())
        }
    }
}