package com.lemick.fodmapscanner

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
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.lemick.example.ProductResult
import com.lemick.fodmapscanner.api.OpenFoodFactsClient
import com.lemick.fodmapscanner.databinding.FragmentCodeScannerBinding
import com.fasterxml.jackson.databind.ObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CodeScannerFragment : Fragment() {


    private var _binding: FragmentCodeScannerBinding? = null
    private lateinit var codeScanner: CodeScanner
    private var permissionGranted = false;
    private var httpClient = OkHttpClient();

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeScannerBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scannerView = binding.scannerView
        val activity = requireActivity()

        checkCameraPermissions(view, activity)

        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                Log.i("APP", "Detected bar code was ${it.text}")
                var productRequest = OpenFoodFactsClient.client.findProduct(it.text);
                productRequest.enqueue(object : Callback<ProductResult> {
                    override fun onResponse(
                        call: Call<ProductResult>, response: Response<ProductResult>
                    ) {
                        val productResult = response.body()
                        val om = ObjectMapper();
                        Log.d("APP", om.writeValueAsString(productResult));
                    }

                    override fun onFailure(call: Call<ProductResult>, t: Throwable) {
                        Log.e("APP", "Cannot fetch product", t);
                    }
                })

                Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
            }
        }

/*        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }*/


        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

    }

    private fun checkCameraPermissions(view: View, activity: FragmentActivity) {
        if (checkSelfPermission(
                view.context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                activity,
                "Merci de donner les permissions d'accés à la caméra",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
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