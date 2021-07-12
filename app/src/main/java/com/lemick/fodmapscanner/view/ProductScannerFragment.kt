package com.lemick.fodmapscanner.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.business.BarcodeAnalyzer
import com.lemick.fodmapscanner.databinding.FragmentProductScannerBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean


class ProductScannerFragment : Fragment() {

    private var _binding: FragmentProductScannerBinding? = null
    private val binding get() = _binding!!
    private var processingBarcode = AtomicBoolean(false)

    private val productViewModel by viewModel<ProductScannerViewModel>();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductScannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        checkCameraPermissions(view, activity)
        startCameraAnalyze()

        productViewModel.productState.observe(viewLifecycleOwner, { eventProduct ->
            if (!eventProduct.hasBeenHandled()) {
                val product = eventProduct.contentIfNotHandled()
                if (product == null) {
                    Toast.makeText(activity, R.string.not_found_product_msg, Toast.LENGTH_SHORT).show()
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

    private fun startCameraAnalyze() {
        val cameraExecutor = Executors.newSingleThreadExecutor()
        val imageAnalysis = ImageAnalysis.Builder()
            .build()
            .also {
                it.setAnalyzer(cameraExecutor, BarcodeAnalyzer { barcode ->
                    if (processingBarcode.compareAndSet(false, true)) {
                        productViewModel.fetchProduct(barcode)
                    }
                })
            }

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(binding.fragmentScanBarcodePreviewView.surfaceProvider)
            }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalysis)
            } catch (e: Exception) {
                Log.e("PreviewUseCase", "Binding failed!", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}