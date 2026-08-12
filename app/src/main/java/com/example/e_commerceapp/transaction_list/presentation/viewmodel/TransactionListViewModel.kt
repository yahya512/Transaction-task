package com.example.e_commerceapp.transaction_list.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.transaction_list.domain.mapper.PaginationUiMapper
import com.example.e_commerceapp.transaction_list.domain.mapper.TransactionListUiMapper
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.usecase.GetTransactionListUseCase
import com.example.e_commerceapp.transaction_list.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(private val getAllTransactions: GetTransactionListUseCase) :
    ViewModel() {
    private val _stateFlowTransactions = MutableStateFlow<UiState>(UiState.Loading)
    var statePagination = UiState.LoadingPagination()
    val stateTransactionFlow: StateFlow<UiState> = _stateFlowTransactions.asStateFlow()


    private var currentPage = 1
    private val limit = 10
    private var isLoading = false
    private var hasMore = true

    private var isInitialLoad = true

    fun loadTransactions() {
        if (isLoading) return // if there is a request => return
        if (!hasMore) return // if there is no hasMore items => return
        getTransactions()
    }

    fun getTransactions() {
        isLoading = true // there is a request right now
        viewModelScope.launch {
            // the initial load (Loading of page)
            if (isInitialLoad) {
                _stateFlowTransactions.emit(UiState.Loading)
            } else {
                // loading of pagination
                statePagination = statePagination.copy(
                    paginationLoading = true
                )
                _stateFlowTransactions.emit(UiState.LoadingPagination())
            }
            when (val result = getAllTransactions(currentPage, limit)) {
                is ApiResultStatus.Error -> {
                    isLoading = false  // allow for send request again
                    isInitialLoad = false
                    _stateFlowTransactions.emit(UiState.Error(result.errorMessage))
                    statePagination = statePagination.copy(
                        paginationLoading = false
                    )
                }

                is ApiResultStatus.Success -> {
                    val listOfTransactionUi =
                        TransactionListUiMapper.matToUi(result.data?.transactions)
                    isLoading = false // allow for send request again
                    isInitialLoad = false
                    // get the current page from backend => single source  of truth
                    val paginationUiModel = PaginationUiMapper.mapToUi(result.data?.pagination)
                    currentPage = paginationUiModel.currentPage ?: currentPage
                    //increment current page for the next request
                    currentPage++
                    // get the has more from backend => single source  of truth
                    hasMore = paginationUiModel.hasMore ?: false
                    // make a list Of All transactions = oldList + newList
                    val listOfTransaction = statePagination.transactions + listOfTransactionUi
                    statePagination = statePagination.copy(
                        transactions = listOfTransaction, // update the transaction of State by the list Of All transactions
                        paginationLoading = false, // turn off pagination Loading
                        hasMore = hasMore  // assign the value of has more from backEnd
                    )

                    _stateFlowTransactions.emit(UiState.Success(listOfTransaction))
                }
            }
        }
    }

    // reset the Pagination Values in First Call And When we Refresh
    fun resetPagination() {
        isInitialLoad = true
        isLoading = false
        statePagination = statePagination.copy(
            transactions = emptyList(), currentPage = 1, hasMore = true, paginationLoading = false
        )
        currentPage = statePagination.currentPage
        hasMore = statePagination.hasMore
    }
}