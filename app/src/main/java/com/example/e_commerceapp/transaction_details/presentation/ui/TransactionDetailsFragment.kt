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
import com.example.e_commerceapp.transaction_details.presentation.model.TransactionDetailsUiModel
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
        setUpListeners()
    }


    private fun getTransactionDetailsById() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.stateDetailsFlow.collect {
                    when (it) {
                        UiDetailsState.Loading -> {
                            binding.groupOfSuccessfulState.isVisible = false
                        }

                        is UiDetailsState.Error -> {
                            handleErrorState(it.errorMessage)
                        }

                        is UiDetailsState.Success -> {
                            handleSuccessState(it.data)
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

    private fun setUpListeners() {
        binding.backButton.setOnClickListener {
            navigateToHome()
        }
        binding.backToHomeButton.setOnClickListener {
            navigateToHome()
        }
    }

    private fun handleSuccessState(data: TransactionDetailsUiModel) {
        binding.apply {
            progressBar.isVisible = false
            errorMessageTextView.isVisible = false
            groupOfSuccessfulState.isVisible = true
            successfulPaymentTextView.text = data.statusTitle
            paymentPrice.text = data.amountLabel
            showTransactionDetails(data.sections ?: emptyList())
            problemTextView.text = data.support?.text
            supportRequest.text = data.support?.actionLabel
            shareButton.isVisible = data.shareable
        }
    }

    private fun handleErrorState(errorMessage: String) {
        binding.apply {
            progressBar.isVisible = false
            groupOfSuccessfulState.isVisible = false
            errorMessageTextView.isVisible = true
            errorMessageTextView.text = errorMessage
        }
    }

    private fun navigateToHome() {
        val action =
            TransactionDetailsFragmentDirections.actionTransactionDetailsFragmentToTransactionListHistoryFragment()
        findNavController().navigate(action)
    }
}