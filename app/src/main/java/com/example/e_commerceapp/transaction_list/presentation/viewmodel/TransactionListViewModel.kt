package com.example.e_commerceapp.transaction_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.transaction_list.domain.mapper.TransactionListUiMapper
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.usecase.GetTransactionListUseCase
import com.example.e_commerceapp.transaction_list.presentation.model.PaginationUiState
import com.example.e_commerceapp.transaction_list.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(private val getAllTransactions: GetTransactionListUseCase) :
    ViewModel() {
    private val _stateFlowTransactions = MutableStateFlow<UiState>(UiState.Loading)
    private val _statePagination = MutableStateFlow(PaginationUiState())
    val stateTransactionFlow: StateFlow<UiState> = _stateFlowTransactions.asStateFlow()
    val statePagination: StateFlow<PaginationUiState> = _statePagination.asStateFlow()

    private var currentPage: Int = 0
    private val limit = 10
    private var isLoading = false
    private var hasMore = true

    private var isInitialLoad = true
    fun loadTransactions() {
        if (isLoading) return
        if (!hasMore) return
        getTransactions()
    }

    fun getTransactions() {

        isLoading = true
        viewModelScope.launch {
            if (isInitialLoad) {
                _stateFlowTransactions.emit(UiState.Loading)
            } else {
                _statePagination.update { currentState ->
                    currentState.copy(
                        isLoadingMore = true
                    )
                }
            }
            when (val result = getAllTransactions(currentPage++, limit)) {
                is ApiResultStatus.Error -> {
                    isLoading = false
                    _stateFlowTransactions.emit(UiState.Error(result.errorMessage))
                    _statePagination.update { currentState ->
                        currentState.copy(
                            isLoadingMore = false
                        )
                    }
                }

                is ApiResultStatus.Success -> {
                    val listOfTransactionUi =
                        TransactionListUiMapper.matToUi(result.data?.transactions)

                    currentPage = result.data?.pagination?.currentPage ?: currentPage
                    hasMore = result.data?.pagination?.hasMore ?: false
                    _statePagination.update { currentState ->
                        currentState.copy(
                            transactions = currentState.transactions + listOfTransactionUi,
                            isLoadingMore = false,
                            hasMore = hasMore
                        )
                    }
                    _stateFlowTransactions.emit(UiState.Success(listOfTransactionUi))
                    isLoading = false
                    isInitialLoad = false
                }
            }
        }
    }

    fun resetPagination() {
        isInitialLoad = true
        _statePagination.update { currentState ->
            currentState.copy(
                transactions = emptyList(),
                currentPage = 0,
                hasMore = true,
            )
        }
    }
}