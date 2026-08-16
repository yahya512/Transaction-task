package com.example.e_commerceapp.transaction_list.presentation.adapter.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.DateTransactionItemBinding
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionUiModule

class DateHeader(val binding: DateTransactionItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun dateHeaderBind(transactionItem: TransactionUiModule) {
        binding.dateLabel.text = transactionItem.title
    }
}