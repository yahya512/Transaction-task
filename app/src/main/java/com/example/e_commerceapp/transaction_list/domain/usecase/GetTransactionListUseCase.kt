package com.example.e_commerceapp.transaction_list.domain.usecase

import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.model.TransactionDomainModel
import com.example.e_commerceapp.transaction_list.domain.repository.TransactionListRepository
import javax.inject.Inject

class GetTransactionListUseCase @Inject constructor(private val repository: TransactionListRepository) {
    suspend operator fun invoke(): ApiResultStatus<List<TransactionDomainModel>> {
        return repository.getTransactionList()
    }
}