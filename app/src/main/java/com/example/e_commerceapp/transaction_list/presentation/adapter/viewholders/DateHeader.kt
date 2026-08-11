package com.example.e_commerceapp.transaction_list.presentation.adapter.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.DateTransactionItemBinding
import com.example.e_commerceapp.transaction_list.presentation.model.TransactionUiModel

class DateHeader(val binding: DateTransactionItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun dateHeaderBind(transactionItem: TransactionUiModel) {
        binding.dateLabel.text = transactionItem.title
    }
}