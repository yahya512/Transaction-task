package com.example.e_commerceapp.transaction_list.presentation.models

sealed class UiState {
    data class Success(val transactions: List<TransactionUiModule>?) : UiState()
    object Loading : UiState()
    data class Error(val errorMessage: String) : UiState()
}