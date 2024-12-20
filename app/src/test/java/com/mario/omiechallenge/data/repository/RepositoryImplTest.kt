package com.mario.omiechallenge.data.repository

import com.mario.omiechallenge.data.datasource.LocalDataSource
import com.mario.omiechallenge.data.datasource.fakeOrders
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test


class RepositoryImplTest {

    @MockK
    lateinit var dataSource: LocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    private fun instantiateRepository() = RepositoryImpl(
        dataSource
    )

    @Test
    fun `SHOULD invoke data source WHEN getAllOrders invoke`() {

        every {
            dataSource.getAllOrders()
        } returns Result.success(
            fakeOrders
        )

        instantiateRepository().getAllOrders()

        verify {
            dataSource.getAllOrders()
        }
    }

    @Test
    fun `SHOULD invoke data source WHEN saveOrder invoke`() {

        every {
            dataSource.addOrder(any())
        } returns Unit

        instantiateRepository().add(fakeOrders.first())

        verify {
            dataSource.addOrder(any())
        }
    }

    @Test
    fun `SHOULD invoke data source WHEN updateOrder invoke`() {

        every {
            dataSource.updateOrder(any())
        } returns Unit

        instantiateRepository().update(fakeOrders.first())

        verify {
            dataSource.updateOrder(any())
        }
    }
}