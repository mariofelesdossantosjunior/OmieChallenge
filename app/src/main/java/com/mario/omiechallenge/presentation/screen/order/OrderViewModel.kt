package com.mario.omiechallenge.presentation.screen.order

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import com.mario.omiechallenge.domain.GetAllOrdersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel(
    private val getAllOrdersUseCase: GetAllOrdersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(OrderUIState())
    val uiState: StateFlow<OrderUIState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    @VisibleForTesting
    fun loadOrders() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }

        getAllOrdersUseCase.invoke()
            .onSuccess { orders ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        orders = orders
                    )
                }
            }.onFailure {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isFailure = true
                    )
                }
            }
    }
}