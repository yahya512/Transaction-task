package com.example.e_commerceapp.transaction_list.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.DateTransactionItemBinding
import com.example.e_commerceapp.databinding.TransactionItemBinding
import com.example.e_commerceapp.transaction_list.presentation.adapter.viewholders.DateHeader
import com.example.e_commerceapp.transaction_list.presentation.adapter.viewholders.TransactionViewHolder
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionOnClick
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionUiModule
import com.example.e_commerceapp.transaction_list.presentation.models.ViewHolderType

const val HEADER = 0
const val TRANSACTION = 1


class TransactionListAdapter(val listener: TransactionOnClick) :
    ListAdapter<TransactionUiModule, RecyclerView.ViewHolder>(
        TransactionsDiffUtil()
    ) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).type == ViewHolderType.TRANSACTIONS) {
            TRANSACTION
        } else {
            HEADER
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            val view = DateTransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            DateHeader(view)
        } else {
            val view = TransactionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TransactionViewHolder(view)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val transactionItem = getItem(position)

        when (holder) {
            is DateHeader -> {
                holder.dateHeaderBind(transactionItem)
            }

            is TransactionViewHolder -> {
                holder.bindTransaction(transactionItem, listener)
                holder.binding.viewLine.isVisible = (position != itemCount - 1)
            }
        }
    }
}