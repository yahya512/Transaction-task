package com.example.e_commerceapp.transaction_details.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.e_commerceapp.transaction_details.presentation.model.SectionUiModel

class SectionDiffUtil : DiffUtil.ItemCallback<SectionUiModel>() {
    override fun areItemsTheSame(
        oldItem: SectionUiModel,
        newItem: SectionUiModel
    ): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(
        oldItem: SectionUiModel,
        newItem: SectionUiModel
    ): Boolean {
        return oldItem == newItem
    }
}