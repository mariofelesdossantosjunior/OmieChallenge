package com.mario.omiechallenge.presentation.screen.orderitem

import com.mario.omiechallenge.data.datasource.fakeOrderItems
import com.mario.omiechallenge.data.datasource.fakeOrders
import com.mario.omiechallenge.domain.AddOrderUseCase
import com.mario.omiechallenge.domain.GetAllOrdersUseCase
import com.mario.omiechallenge.domain.UpdateOrderUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class OrderItemViewModelTest {

    @MockK
    lateinit var getAllOrdersUseCase: GetAllOrdersUseCase

    @MockK
    lateinit var addOrderUseCase: AddOrderUseCase

    @MockK
    lateinit var updateOrderUseCase: UpdateOrderUseCase

    private lateinit var viewModel: OrderItemViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(instantiateViewModel())
    }

    private fun instantiateViewModel() = OrderItemViewModel(
        getAllOrdersUseCase,
        addOrderUseCase,
        updateOrderUseCase
    )

    @Test
    fun `onEvent SHOULD invoke changeClient`() {
        every {
            viewModel.changeClient(any())
        } returns Unit

        val fakeClient = "client"

        viewModel.onEvent(
            OrderItemUIEvent.OnClientChanged(
                client = fakeClient
            )
        )

        verify {
            viewModel.changeClient(fakeClient)
        }
    }

    @Test
    fun `onEvent SHOULD invoke changeProductName`() {
        every {
            viewModel.changeProductName(any())
        } returns Unit

        val fakeProduct = "new product"

        viewModel.onEvent(
            OrderItemUIEvent.OnProductNameChanged(
                name = fakeProduct
            )
        )

        verify {
            viewModel.changeProductName(any())
        }
    }

    @Test
    fun `onEvent SHOULD invoke changeProductQuantity`() {
        every {
            viewModel.changeProductQuantity(any())
        } returns Unit

        val fakeQuantity = "1"

        viewModel.onEvent(
            OrderItemUIEvent.OnProductQuantityChanged(
                quantity = fakeQuantity
            )
        )

        verify {
            viewModel.changeProductQuantity(any())
        }
    }

    @Test
    fun `onEvent SHOULD invoke changeProductPrice`() {
        every {
            viewModel.changeProductPrice(any())
        } returns Unit

        val fakePrice = "1"

        viewModel.onEvent(
            OrderItemUIEvent.OnProductPriceChanged(
                price = fakePrice
            )
        )

        verify {
            viewModel.changeProductPrice(any())
        }
    }

    @Test
    fun `onEvent SHOULD invoke addItem`() {
        every {
            viewModel.addItem()
        } returns Unit


        viewModel.onEvent(
            OrderItemUIEvent.OnAddItem
        )

        verify {
            viewModel.addItem()
        }
    }

    @Test
    fun `onEvent SHOULD invoke deleteItem`() {
        every {
            viewModel.deleteItem(any())
        } returns Unit


        viewModel.onEvent(
            OrderItemUIEvent.OnDeleteItem(
                item = fakeOrderItems.first()
            )
        )

        verify {
            viewModel.deleteItem(any())
        }
    }

    @Test
    fun `onEvent SHOULD invoke saveOrUpdateOrder`() {
        every {
            viewModel.saveOrUpdateOrder()
        } returns Unit


        viewModel.onEvent(
            OrderItemUIEvent.OnSaveOrUpdateOrder
        )

        verify {
            viewModel.saveOrUpdateOrder()
        }
    }

    @Test
    fun `onEvent SHOULD invoke loadOrderItem`() {
        every {
            viewModel.loadOrderItem(any())
        } returns Unit


        viewModel.onEvent(
            OrderItemUIEvent.LoadOrderItem(
                orderId = "1"
            )
        )

        verify {
            viewModel.loadOrderItem(any())
        }
    }

    @Test
    fun `changeClient SHOULD update uiState`() {
        val fakeClient = "client"

        viewModel.changeClient(fakeClient)

        Assert.assertEquals(
            fakeClient,
            viewModel.uiState.value.client
        )
    }

    @Test
    fun `changeProductName SHOULD update uiState`() {
        val fakeProduct = "new product"

        viewModel.changeProductName(fakeProduct)

        Assert.assertEquals(
            fakeProduct,
            viewModel.uiState.value.productName
        )
    }

    @Test
    fun `changeProductQuantity SHOULD update uiState`() {
        val fakeQuantity = "1"

        viewModel.changeProductQuantity(fakeQuantity)

        Assert.assertEquals(
            fakeQuantity,
            viewModel.uiState.value.productQuantity
        )
    }

    @Test
    fun `changeProductPrice SHOULD update uiState`() {
        val fakePrice = "1"

        viewModel.changeProductPrice(fakePrice)

        Assert.assertEquals(
            fakePrice,
            viewModel.uiState.value.productPrice
        )
    }

    @Test
    fun `addItem SHOULD update uiState`() {
        val fakeProduct = "new product"
        val fakeQuantity = "1"
        val fakePrice = "1"

        viewModel.changeProductName(fakeProduct)
        viewModel.changeProductQuantity(fakeQuantity)
        viewModel.changeProductPrice(fakePrice)

        viewModel.addItem()

        Assert.assertEquals(
            fakeProduct,
            viewModel.uiState.value.items.first().name
        )
        Assert.assertEquals(
            fakeQuantity.toInt(),
            viewModel.uiState.value.items.first().quantity
        )
        Assert.assertEquals(
            fakePrice.toDouble(),
            viewModel.uiState.value.items.first().unitPrice,
            0.0
        )

        verify {
            viewModel.cleanInputs()
        }
    }

    @Test
    fun `deleteItem SHOULD update uiState`() {
        val fakeProduct = "new product"
        val fakeQuantity = "1"
        val fakePrice = "1"

        viewModel.changeProductName(fakeProduct)
        viewModel.changeProductQuantity(fakeQuantity)
        viewModel.changeProductPrice(fakePrice)

        viewModel.addItem()

        viewModel.deleteItem(viewModel.uiState.value.items.first())

        Assert.assertTrue(
            viewModel.uiState.value.items.isEmpty()
        )
    }

    @Test
    fun `saveOrUpdateOrder SHOULD invoke updateOrder`() {
        every {
            getAllOrdersUseCase.invoke()
        } returns Result.success(
            fakeOrders
        )

        viewModel.loadOrderItem(
            orderId = fakeOrders.first().id
        )

        every {
            viewModel.updateOrder(any(), any(), any())
        } returns Unit

        viewModel.saveOrUpdateOrder()

        verify {
            viewModel.updateOrder(any(), any(), any())
        }
    }

    @Test
    fun `saveOrUpdateOrder SHOULD invoke addOrder`() {
        val fakeProduct = "new product"
        val fakeQuantity = "1"
        val fakePrice = "1"

        every {
            viewModel.addOrder(any(), any())
        } returns Unit

        viewModel.changeProductName(fakeProduct)
        viewModel.changeProductQuantity(fakeQuantity)
        viewModel.changeProductPrice(fakePrice)

        viewModel.saveOrUpdateOrder()

        verify {
            viewModel.addOrder(any(), any())
        }
    }

    @Test
    fun `updateOrder SHOULD invoke useCase`() {
        every {
            updateOrderUseCase.invoke(any())
        } returns Unit

        viewModel.updateOrder(
            id = fakeOrders.first().id,
            clientName = fakeOrders.first().clientName,
            items = fakeOrders.first().items
        )

        verify {
            updateOrderUseCase.invoke(any())
        }
    }

    @Test
    fun `addOrder SHOULD invoke useCase`() {
        every {
            addOrderUseCase.invoke(any())
        } returns Unit

        viewModel.addOrder(
            clientName = fakeOrders.first().clientName,
            items = fakeOrders.first().items
        )

        verify {
            addOrderUseCase.invoke(any())
        }
    }

    @Test
    fun `deleteItem SHOULD remove item`() {
        every {
            getAllOrdersUseCase.invoke()
        } returns Result.success(
            fakeOrders
        )

        viewModel.loadOrderItem(
            orderId = fakeOrders.first().id
        )

        fakeOrders.first().items.forEach {
            viewModel.deleteItem(it)
        }

        Assert.assertTrue(
            viewModel.uiState.value.items.isEmpty()
        )
    }

    @Test
    fun `isValidForm SHOULD return productName Empty`() {

        val result = viewModel.isValidForm(
            name = "",
            quantity = 1,
            price = 1.0
        )

        Assert.assertFalse(result)
        Assert.assertEquals(
            "Digite o nome do produto",
            viewModel.uiState.value.productError
        )
    }

    @Test
    fun `isValidForm SHOULD return quantity 0`() {

        val result = viewModel.isValidForm(
            name = "produto",
            quantity = 0,
            price = 1.0
        )

        Assert.assertFalse(result)
        Assert.assertEquals(
            "Digite a quantidade do produto",
            viewModel.uiState.value.quantityError
        )
    }

    @Test
    fun `isValidForm SHOULD return price 0`() {

        val result = viewModel.isValidForm(
            name = "produto",
            quantity = 1,
            price = 0.0
        )

        Assert.assertFalse(result)
        Assert.assertEquals(
            "Digite o valor do produto",
            viewModel.uiState.value.valueError
        )
    }
}