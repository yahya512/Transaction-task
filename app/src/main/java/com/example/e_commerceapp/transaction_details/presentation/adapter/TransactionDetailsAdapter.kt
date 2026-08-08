package com.example.e_commerceapp.transaction_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.SectionItemBinding
import com.example.e_commerceapp.transaction_details.presentation.model.SectionUiModel

class TransactionDetailsAdapter :
    ListAdapter<SectionUiModel, TransactionDetailsAdapter.TransactionDetailsViewHolder>(
        SectionDiffUtil()
    ) {

    class TransactionDetailsViewHolder(val binding: SectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

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