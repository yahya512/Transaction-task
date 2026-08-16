package com.example.e_commerceapp.transaction_details.presentation.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.graphics.createBitmap
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
import java.io.File
import java.io.FileOutputStream

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
                            binding.apply {
                                groupOfSuccessfulState.isVisible = false
                                errorMessageTextView.isVisible = false
                            }
                        }

                        is UiDetailsState.Error -> {
                            binding.apply {
                                progressBar.isVisible = false
                                groupOfSuccessfulState.isVisible = false
                                errorMessageTextView.isVisible = true
                                errorMessageTextView.text = it.errorMessage
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

    private fun setUpListeners() {
        binding.backButton.setOnClickListener {
            navigateToHome()
        }
        binding.backToHomeButton.setOnClickListener {
            navigateToHome()
        }
        binding.shareButton.setOnClickListener {
            sharingTransactionImage()
        }
    }

    // Create a BitMap
    private fun View.bitMap(): Bitmap {
        val bitmap = createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)
        return bitmap
    }

    //Save the image into cache
    private fun saveBitmapToCache(bitmap: Bitmap): File {
        val file = File(requireContext().cacheDir, "transaction_share.png")
        FileOutputStream(file).use { outputStream ->
            bitmap.compress(
                Bitmap.CompressFormat.PNG,
                90,
                outputStream
            )
        }
        return file
    }

    // transfer file into content URI
    private fun fileToContentUri(file: File): Uri {
        val uri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileProvider",
            file
        )
        return uri
    }

    // share intent
    private fun shareImage(uri: Uri): Intent {
        return Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(
                Intent.EXTRA_STREAM,
                uri
            )
            addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }
    }

    private fun sharingTransactionImage() {
        val viewToShare = binding.transactionSharedContainer.bitMap()
        val file = saveBitmapToCache(viewToShare)
        val uri = fileToContentUri(file)
        val shareIntent = shareImage(uri)
        startActivity(
            Intent.createChooser(
                shareIntent,
                "Share transaction"
            )
        )
    }

    private fun navigateToHome() {
        val action =
            TransactionDetailsFragmentDirections.actionTransactionDetailsFragmentToTransactionListHistoryFragment()
        findNavController().navigate(action)
    }
}