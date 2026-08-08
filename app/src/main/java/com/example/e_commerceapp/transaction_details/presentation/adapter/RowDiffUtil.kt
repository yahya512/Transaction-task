package com.example.e_commerceapp.transaction_details.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.e_commerceapp.transaction_details.presentation.model.RowsUiModel

class RowDiffUtil : DiffUtil.ItemCallback<RowsUiModel>() {
    override fun areItemsTheSame(
        oldItem: RowsUiModel,
        newItem: RowsUiModel
    ): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(
        oldItem: RowsUiModel,
        newItem: RowsUiModel
    ): Boolean {
        return oldItem == newItem
    }
}