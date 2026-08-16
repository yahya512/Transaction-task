package com.example.e_commerceapp.transaction_list.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.e_commerceapp.transaction_list.presentation.model.TransactionUiModel

class TransactionsDiffUtil : DiffUtil.ItemCallback<TransactionUiModel>() {
    override fun areItemsTheSame(
        oldItem: TransactionUiModel,
        newItem: TransactionUiModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TransactionUiModel,
        newItem: TransactionUiModel
    ): Boolean {
        return oldItem == newItem
    }
}