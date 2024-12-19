package com.mario.omiechallenge.presentation.screen.orderitem

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.mario.omiechallenge.domain.AddOrderUseCase
import com.mario.omiechallenge.domain.model.Order
import com.mario.omiechallenge.domain.model.OrderItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderItemViewModel(
    private val addOrderUseCase: AddOrderUseCase
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

            OrderItemUIEvent.OnSaveOrder -> {
                saveOrder()
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

        if (productName.isNotBlank() && productQuantity > 0 && productValue > 0) {
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

    @VisibleForTesting
    fun cleanInputs() {
        changeProductName("")
        changeProductQuantity("")
        changeProductValue("")
    }

    @VisibleForTesting
    fun saveOrder() {
        val clientName = _uiState.value.client
        val items = _uiState.value.items

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
}