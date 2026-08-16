package com.example.e_commerceapp.transaction_list.presentation.model

sealed class UiState {
    data class Success(val transactions: List<TransactionUiModel>?) : UiState()
   data object Loading : UiState()
    data class Error(val errorMessage: String) : UiState()
    data class LoadingPagination(
        val transactions: List<TransactionUiModel> = emptyList(),
        val currentPage: Int = 1,
        val paginationLoading: Boolean = false,
        val hasMore: Boolean = true
    ) : UiState()
}