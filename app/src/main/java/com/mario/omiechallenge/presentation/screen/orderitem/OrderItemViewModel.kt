package com.mario.omiechallenge.presentation.screen.orderitem

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.mario.omiechallenge.domain.AddOrderUseCase
import com.mario.omiechallenge.domain.GetAllOrdersUseCase
import com.mario.omiechallenge.domain.UpdateOrderUseCase
import com.mario.omiechallenge.domain.model.Order
import com.mario.omiechallenge.domain.model.OrderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderItemViewModel(
    private val getAllOrdersUseCase: GetAllOrdersUseCase,
    private val addOrderUseCase: AddOrderUseCase,
    private val updateOrderUseCase: UpdateOrderUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderItemUIState())
    val uiState: StateFlow<OrderItemUIState> = _uiState.asStateFlow()

    fun onEvent(event: OrderItemUIEvent) {
        when (event) {
            is OrderItemUIEvent.OnClientChanged -> {
                changeClient(
                    client = event.client
                )
            }

            is OrderItemUIEvent.OnProductNameChanged -> {
                changeProductName(
                    product = event.productName
                )
            }

            is OrderItemUIEvent.OnProductQuantityChanged -> {
                changeProductQuantity(
                    quantity = event.productQuantity
                )
            }

            is OrderItemUIEvent.OnProductValueChanged -> {
                changeProductValue(
                    value = event.productValue
                )
            }

            OrderItemUIEvent.OnAddItem -> {
                addItem()
            }

            is OrderItemUIEvent.OnDeleteItem -> {
                deleteItem(event.item)
            }

            OrderItemUIEvent.OnSaveOrUpdateOrder -> {
                saveOrUpdateOrder()
            }

            is OrderItemUIEvent.LoadOrderItem -> {
                loadOrderItem(event.orderId)
            }
        }
    }

    @VisibleForTesting
    fun changeClient(client: String) {
        _uiState.update {
            it.copy(
                client = client
            )
        }
    }

    @VisibleForTesting
    fun changeProductName(product: String) {
        _uiState.update {
            it.copy(
                productName = product
            )
        }
    }

    @VisibleForTesting
    fun changeProductQuantity(quantity: String) {
        _uiState.update {
            it.copy(
                productQuantity = quantity
            )
        }
    }

    @VisibleForTesting
    fun changeProductValue(value: String) {
        _uiState.update {
            it.copy(
                productValue = value
            )
        }
    }

    @VisibleForTesting
    fun addItem() {
        val productName = _uiState.value.productName
        val productQuantity = _uiState.value.productQuantity.toIntOrNull() ?: 0
        val productValue = _uiState.value.productValue.toDoubleOrNull() ?: 0.0

        if (isValidForm(
                productName = productName,
                productQuantity = productQuantity,
                productValue = productValue
            )
        ) {
            _uiState.update {
                it.copy(
                    items = it.items + OrderItem(
                        name = productName,
                        quantity = productQuantity,
                        unitPrice = productValue,
                        total = productQuantity * productValue
                    )
                )
            }

            cleanInputs()
        }
    }

    private fun isValidForm(
        productName: String,
        productQuantity: Int,
        productValue: Double
    ): Boolean {
        var isValid = true

        if (productName.isBlank()) {
            _uiState.update {
                it.copy(
                    productError = "Digite o nome do produto"
                )
            }
            isValid = false
        }

        if (productQuantity <= 0) {
            _uiState.update {
                it.copy(
                    quantityError = "Digite a quantidade do produto"
                )
            }
            isValid = false
        }

        if (productValue < 0.0) {
            _uiState.update {
                it.copy(
                    valueError = "Digite o valor do produto"
                )
            }
            isValid = false
        }

        return isValid
    }

    @VisibleForTesting
    fun cleanInputs() {
        changeProductName("")
        changeProductQuantity("")
        changeProductValue("")
    }

    @VisibleForTesting
    fun saveOrUpdateOrder() {
        val id = _uiState.value.id
        val clientName = _uiState.value.client
        val items = _uiState.value.items

        if (!id.isNullOrBlank()) {
            updateOrder(id, clientName, items)
        } else {
            addOrder(clientName, items)
        }
    }

    @VisibleForTesting
    fun updateOrder(
        id: String,
        clientName: String,
        items: List<OrderItem>
    ) {
        val params = UpdateOrderUseCase.Params(
            order = Order(
                id = id,
                clientName = clientName,
                items = items
            )
        )
        updateOrderUseCase.invoke(params)
    }

    @VisibleForTesting
    fun addOrder(
        clientName: String, items: List<OrderItem>
    ) {
        val params = AddOrderUseCase.Params(
            order = Order(
                clientName = clientName,
                items = items
            )
        )
        addOrderUseCase.invoke(params)
    }

    @VisibleForTesting
    fun deleteItem(item: OrderItem) {
        _uiState.update {
            it.copy(
                items = it.items - item
            )
        }
    }

    @VisibleForTesting
    fun loadOrderItem(orderId: String) {
        getAllOrdersUseCase.invoke().onSuccess { orders ->

            val selectedOrder = orders.firstOrNull {
                it.id == orderId
            } ?: return@onSuccess

            _uiState.update {
                it.copy(
                    id = selectedOrder.id,
                    client = selectedOrder.clientName,
                    items = selectedOrder.items
                )
            }
        }
    }

}