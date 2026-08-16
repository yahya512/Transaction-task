package com.example.e_commerceapp.transaction_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.e_commerceapp.databinding.SectionItemBinding
import com.example.e_commerceapp.transaction_details.presentation.adapter.viewHolder.TransactionDetailsViewHolder
import com.example.e_commerceapp.transaction_details.presentation.model.SectionUiModel

class TransactionDetailsAdapter :
    ListAdapter<SectionUiModel, TransactionDetailsViewHolder>(
        SectionDiffUtil()
    ) {
    
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TransactionDetailsViewHolder {
        val view = SectionItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TransactionDetailsViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TransactionDetailsViewHolder, position: Int
    ) {
        val transactionItem = getItem(position)
        holder.binding.apply {
            val adapter = SectionAdapter()
            titleOfOperationTextView.text = transactionItem.title
            rowRecyclerView.adapter = adapter
            adapter.submitList(transactionItem.rows)

        }
    }
}