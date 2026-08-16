package com.example.e_commerceapp.transaction_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.transaction_list.domain.mapper.TransactionListUiMapper
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.usecase.GetTransactionListUseCase
import com.example.e_commerceapp.transaction_list.presentation.models.UiState
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
    val stateflow: StateFlow<UiState> = _stateFlowTransactions.asStateFlow()

    fun getTransactions() {
        viewModelScope.launch {
            _stateFlowTransactions.emit(UiState.Loading)
            when (val result = getAllTransactions()) {
                is ApiResultStatus.Error -> {
                    _stateFlowTransactions.emit(UiState.Error(result.errorMessage))
                }

                is ApiResultStatus.Success -> {
                    val listOfTransactionUi = TransactionListUiMapper.matToDomain(result.data)
                    _stateFlowTransactions.emit(UiState.Success(listOfTransactionUi))

                }
            }
        }
    }
}