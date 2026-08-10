package com.example.e_commerceapp.transaction_details.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.transaction_details.domain.mappers.TransactionDetailsUiMapper
import com.example.e_commerceapp.transaction_details.domain.usecase.GetTransactionDetailsUseCase
import com.example.e_commerceapp.transaction_details.presentation.model.UiDetailsState
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionDetailsViewModel @Inject constructor(private val getTransactionDetailsUseCase: GetTransactionDetailsUseCase) :
    ViewModel() {

    private val _stateDetailsFlow = MutableStateFlow<UiDetailsState>(UiDetailsState.Loading)
    val stateDetailsFlow: StateFlow<UiDetailsState> = _stateDetailsFlow.asStateFlow()


    fun getTransactionById(id: Int) {
        viewModelScope.launch {
            _stateDetailsFlow.emit(UiDetailsState.Loading)

            when (val result = getTransactionDetailsUseCase(id)) {
                is ApiResultStatus.Success -> {
                    val transactionDetailsUi = TransactionDetailsUiMapper.mapToUi(result.data)
                    _stateDetailsFlow.emit(UiDetailsState.Success(transactionDetailsUi))
                }

                is ApiResultStatus.Error -> {
                    _stateDetailsFlow.emit(UiDetailsState.Error(result.errorMessage))
                }
            }
        }

    }
}