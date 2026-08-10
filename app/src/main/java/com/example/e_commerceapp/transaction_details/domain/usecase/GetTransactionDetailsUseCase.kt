package com.example.e_commerceapp.transaction_details.domain.usecase

import com.example.e_commerceapp.transaction_details.domain.model.TransactionDetailsDomainModel
import com.example.e_commerceapp.transaction_details.domain.repository.TransactionDetailsRepository
import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import javax.inject.Inject

class GetTransactionDetailsUseCase @Inject constructor(val repository: TransactionDetailsRepository) {
    suspend operator fun invoke(id: Int): ApiResultStatus<TransactionDetailsDomainModel?> {
        return repository.getTransactionById(id)
    }
}