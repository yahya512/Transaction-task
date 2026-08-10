package com.example.e_commerceapp.transaction_list.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionUiModule

class TransactionsDiffUtil : DiffUtil.ItemCallback<TransactionUiModule>() {
    override fun areItemsTheSame(
        oldItem: TransactionUiModule,
        newItem: TransactionUiModule
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: TransactionUiModule,
        newItem: TransactionUiModule
    ): Boolean {
        return oldItem == newItem
    }
}