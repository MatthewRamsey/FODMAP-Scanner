package com.lemick.fodmapscanner.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.databinding.FragmentProductScannerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProductScannerFragment : Fragment() {

    private var _binding: FragmentProductScannerBinding? = null
    private val binding get() = _binding!!
    private lateinit var codeScanner: CodeScanner

    private val productViewModel by viewModel<ProductScannerViewModel>();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductScannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scannerView = binding.scannerView
        val activity = requireActivity()

        checkCameraPermissions(view, activity)
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.decodeCallback = DecodeCallback { detectionResult ->
            productViewModel.fetchProduct(detectionResult.text)
        }

        productViewModel.productState.observe(viewLifecycleOwner, { eventProduct ->
            if (!eventProduct.hasBeenHandled()) {
                val product = eventProduct.contentIfNotHandled()
                if (product == null) {
                    Toast.makeText(activity, R.string.not_found_product_msg, Toast.LENGTH_SHORT).show()
                    codeScanner.startPreview()
                } else {
                    productViewModel.persistAnalyzedProduct(product)
                    findNavController().navigate(
                        ProductScannerFragmentDirections.actionCodeScannerFragmentToSummaryProductFragment(product)
                    )
                }
            }
        })

    }

    private fun checkCameraPermissions(view: View, activity: FragmentActivity) {
        if (checkSelfPermission(view.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, R.string.give_permissions, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_CodeScannerFragment_to_FirstFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}