package com.lemick.fodmapscanner.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.lemick.fodmapscanner.model.api.model.ProductResult
import com.lemick.fodmapscanner.databinding.FragmentCodeScannerBinding
import com.fasterxml.jackson.databind.ObjectMapper
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.business.ProductRemoteDataSource
import com.lemick.fodmapscanner.model.api.IOpenFoodFactsClient
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CodeScannerFragment : Fragment() {

    private var _binding: FragmentCodeScannerBinding? = null
    private lateinit var codeScanner: CodeScanner

    private val productViewModel by viewModel<ProductViewModel>();

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCodeScannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scannerView = binding.scannerView
        val activity = requireActivity()

        checkCameraPermissions(view, activity)
        productViewModel.productState.observe(viewLifecycleOwner, { product ->
            if (product == null) {
                Toast.makeText(activity, "Produit non detecté, veuillez ressayer", Toast.LENGTH_SHORT).show()
                codeScanner.startPreview()
            } else {
                findNavController().navigate(
                    CodeScannerFragmentDirections.actionCodeScannerFragmentToSummaryProductFragment(product)
                )
            }
        })
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.decodeCallback = DecodeCallback { detectionResult ->
            productViewModel.fetchProduct(detectionResult.text)
        }
/*            activity.runOnUiThread {
                Log.i("APP", "Detected bar code was ${it.text}")
                var productRequest = openFoodFactsClient.findProduct(it.text);
                productRequest.enqueue(object : Callback<ProductResult> {
                    override fun onResponse(
                        call: Call<ProductResult>, response: Response<ProductResult>
                    ) {
                        val productResult = response.body()
                        if (productResult?.product != null) {
                            findNavController().navigate(
                                CodeScannerFragmentDirections.actionCodeScannerFragmentToSummaryProductFragment(
                                    productResult.product!!
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<ProductResult>, t: Throwable) {
                        Log.e("APP", "Cannot fetch product", t);
                    }
                })*/

/*        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }*/

    }

    private fun checkCameraPermissions(view: View, activity: FragmentActivity) {
        if (checkSelfPermission(view.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(activity, "Merci de donner les permissions d'accés à la caméra", Toast.LENGTH_SHORT).show()
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