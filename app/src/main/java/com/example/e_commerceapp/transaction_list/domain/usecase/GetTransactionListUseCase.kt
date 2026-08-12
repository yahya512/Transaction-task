package com.example.e_commerceapp.transaction_list.domain.usecase

import com.example.e_commerceapp.transaction_list.domain.model.ApiResultStatus
import com.example.e_commerceapp.transaction_list.domain.model.BaseDomainModel
import com.example.e_commerceapp.transaction_list.domain.repository.TransactionListRepository
import javax.inject.Inject

class GetTransactionListUseCase @Inject constructor(private val repository: TransactionListRepository) {
    suspend operator fun invoke(
        page: Int,
        limit: Int
    ): ApiResultStatus<BaseDomainModel?> {
        return repository.getTransactionList(page = page, limit = limit)
    }
}