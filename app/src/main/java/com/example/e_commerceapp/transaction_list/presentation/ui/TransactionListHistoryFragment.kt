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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.databinding.FragmentTransactionListHistoryBinding
import com.example.e_commerceapp.transaction_list.presentation.adapter.TransactionListAdapter
import com.example.e_commerceapp.transaction_list.presentation.model.TransactionOnClick
import com.example.e_commerceapp.transaction_list.presentation.model.TransactionUiModel
import com.example.e_commerceapp.transaction_list.presentation.model.UiState
import com.example.e_commerceapp.transaction_list.presentation.viewmodel.TransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionListHistoryFragment : Fragment(), TransactionOnClick {
    private lateinit var binding: FragmentTransactionListHistoryBinding
    private val viewModel: TransactionListViewModel by viewModels()

    private val adapter by lazy {
        TransactionListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionListHistoryBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //reset pagination
        viewModel.resetPagination()
        // show products
        viewModel.loadTransactions()
        // using swipe Refresh
        binding.swipeT0Refresh.setOnRefreshListener {
            viewModel.resetPagination()
            viewModel.loadTransactions()
        }
        //set the adapter to recyclerView
        binding.transactionListRecView.adapter = adapter
        //set up the next load page number
        setRecyclerListener()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.stateTransactionFlow.collect {
                        when (it) {
                            is UiState.Loading -> {
                                binding.apply {
                                    progressBar.isVisible = true
                                    swipeT0Refresh.isRefreshing = false
                                    transactionListRecView.isVisible = false
                                    errorMessageText.isVisible = false
                                }
                            }

                            is UiState.Success -> {

                                binding.apply {
                                    swipeT0Refresh.isRefreshing = false
                                    progressBar.isVisible = false
                                    transactionListRecView.isVisible = true
                                    errorMessageText.isVisible = false
                                }
                                showTransactionsList(it.transactions ?: emptyList())
                            }

                            is UiState.Error -> {
                                binding.apply {
                                    progressBar.isVisible = false
                                    swipeT0Refresh.isRefreshing = false
                                    transactionListRecView.isVisible = false
                                    errorMessageText.isVisible = true
                                    errorMessageText.text = it.errorMessage
                                }
                            }

                            is UiState.LoadingPagination -> {
                                binding.apply {
                                    swipeT0Refresh.isRefreshing = false
                                    progressBar.isVisible = false
                                    transactionListRecView.isVisible = true
                                    errorMessageText.isVisible = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    private fun showTransactionsList(transactions: List<TransactionUiModel>) {
        adapter.submitList(transactions)
    }

    override fun onClick(item: TransactionUiModel) {
        item.id?.let {
            val action =
                TransactionListHistoryFragmentDirections.actionTransactionListHistoryFragmentToTransactionDetailsFragment(
                    item.id
                )
            findNavController().navigate(action)
        }
    }

    private fun setRecyclerListener() {
        binding.transactionListRecView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy <= 0) return
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItems = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                    if (lastVisibleItem >= (totalItems - 3)) {
                        viewModel.loadTransactions()
                    }
                }
            }
        )
    }
}
