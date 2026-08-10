package com.example.e_commerceapp.transaction_details.presentation.ui

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
import androidx.navigation.fragment.navArgs
import com.example.e_commerceapp.databinding.FragmentTransactionDetailsBinding
import com.example.e_commerceapp.transaction_details.presentation.adapter.TransactionDetailsAdapter
import com.example.e_commerceapp.transaction_details.presentation.model.SectionUiModel
import com.example.e_commerceapp.transaction_details.presentation.model.UiDetailsState
import com.example.e_commerceapp.transaction_details.presentation.viewmodel.TransactionDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TransactionDetailsFragment : Fragment() {
    private lateinit var binding: FragmentTransactionDetailsBinding
    val viewModel: TransactionDetailsViewModel by viewModels()
    val args: TransactionDetailsFragmentArgs by navArgs()
    private val adapter by lazy {
        TransactionDetailsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransactionDetailsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getTransactionById(args.id)
        getTransactionDetailsById()

    }


    private fun getTransactionDetailsById() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateDetailsFlow.collect {
                    when (it) {
                        UiDetailsState.Loading -> {
                            binding.apply {
                                groupOfSuccessfulState.isVisible = false
                                backButton.setOnClickListener {
                                    navigateToHome()
                                }
                            }
                        }

                        is UiDetailsState.Error -> {
                            binding.apply {
                                progressBar.isVisible = false
                                groupOfSuccessfulState.isVisible = false
                                errorMessageTextView.isVisible = true
                                errorMessageTextView.text = it.errorMessage
                                backButton.setOnClickListener {
                                    navigateToHome()
                                }
                            }
                        }

                        is UiDetailsState.Success -> {
                            binding.apply {
                                progressBar.isVisible = false
                                errorMessageTextView.isVisible = false
                                groupOfSuccessfulState.isVisible = true
                                successfulPaymentTextView.text = it.data.status_title
                                paymentPrice.text = it.data.amount_label
                                showTransactionDetails(it.data.sections ?: emptyList())
                                problemTextView.text = it.data.support?.text
                                supportRequest.text = it.data.support?.action_label
                                backToHomeButton.setOnClickListener {
                                    navigateToHome()
                                }
                                backButton.setOnClickListener {
                                    navigateToHome()
                                }
                                shareButton.isVisible = it.data.shareable
                            }
                        }

                    }

                }
            }
        }
    }

    private fun showTransactionDetails(transaction: List<SectionUiModel>) {
        binding.apply {
            recyclerviewTransactionDetailsOperations.adapter = adapter
        }
        adapter.submitList(transaction)
    }

    private fun navigateToHome() {
        val action =
            TransactionDetailsFragmentDirections.actionTransactionDetailsFragmentToTransactionListHistoryFragment()
        findNavController().navigate(action)
    }
}