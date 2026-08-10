package com.example.e_commerceapp.transaction_list.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.e_commerceapp.databinding.FragmentTransactionListHistoryBinding
import com.example.e_commerceapp.transaction_list.presentation.adapter.TransactionListAdapter
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionOnClick
import com.example.e_commerceapp.transaction_list.presentation.models.TransactionUiModule
import com.example.e_commerceapp.transaction_list.presentation.models.UiState
import com.example.e_commerceapp.transaction_list.presentation.viewmodel.TransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionListHistoryFragment : Fragment(), TransactionOnClick {
    private lateinit var binding: FragmentTransactionListHistoryBinding
    private val viewModel: TransactionListViewModel by viewModels()
    private lateinit var adapter: TransactionListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionListHistoryBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // show products
        viewModel.getTransactions()
        // using swipe Refresh
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getTransactions()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateflow.collect {
                    when (it) {
                        is UiState.Loading -> {
                            binding.apply {
                                progressPar.isVisible = true
                                swipeToRefresh.isRefreshing = false
                                transactionListRecView.isVisible = false
                                errorMessageTextView.isVisible = false

                            }
                        }

                        is UiState.Success -> {
                            binding.apply {
                                swipeToRefresh.isRefreshing = false
                                progressPar.isVisible = false
                                transactionListRecView.isVisible = true
                                errorMessageTextView.isVisible = false

                            }
                            showTransactions(it.transactions ?: emptyList())
                        }

                        is UiState.Error -> {
                            binding.apply {
                                progressPar.isVisible = false
                                swipeToRefresh.isRefreshing = false
                                transactionListRecView.isVisible = false
                                errorMessageTextView.isVisible = true
                                errorMessageTextView.text = it.errorMessage
                            }
                        }

                    }
                }
            }
        }
    }

    private fun showTransactions(transactions: List<TransactionUiModule>) {
        adapter = TransactionListAdapter(this)
        binding.transactionListRecView.adapter = adapter
        adapter.submitList(transactions)

    }

    override fun onClick(item: TransactionUiModule) {
        item.id?.let {
            val action =
                TransactionListHistoryFragmentDirections.actionTransactionListHistoryFragmentToTransactionDetailsFragment(
                    item.id
                )
            findNavController().navigate(action)
        }
    }
}
