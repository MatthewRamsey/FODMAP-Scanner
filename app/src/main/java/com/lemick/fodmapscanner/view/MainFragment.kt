package com.lemick.fodmapscanner.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.databinding.FragmentMainBinding
import com.lemick.fodmapscanner.model.entity.AnalyzedProduct
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.function.Consumer


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by inject();
    private val productViewModel by viewModel<ProductScannerViewModel>();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<FloatingActionButton>(R.id.app_fab_scanner)?.show()
        mainViewModel.findAnalyzedProducts().observe(viewLifecycleOwner, { analyzedProducts ->
            binding.mainList.adapter = AnalyzedProductListAdapter(requireContext(), analyzedProducts)
        })
        binding.mainList.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            showLoader()
            val analyzedProduct = binding.mainList.adapter.getItem(position) as AnalyzedProduct
            productViewModel.fetchProduct(analyzedProduct.productBarcode)
        }
        productViewModel.productState.observe(viewLifecycleOwner, { eventProduct ->
            if (!eventProduct.hasBeenHandled()) {
                val product = eventProduct.contentIfNotHandled()
                if (product == null) {
                    Toast.makeText(activity, R.string.not_found_product_msg, Toast.LENGTH_SHORT).show()
                    hideLoader()
                } else {
                    findNavController().navigate(MainFragmentDirections.actionFirstFragmentToSummaryProductFragment(product))
                }
            }
        })

        //initFakeData()
    }

    private fun initFakeData() {
        val listData = arrayListOf("3061990140722","3228857000852", "3017620422003", "3175680011480")
        for (barcode in listData) {
            productViewModel.fetchProduct(barcode)
        }
    }

    private fun showLoader() {
        binding.mainList.alpha = 0.5F
        binding.mainList.isEnabled = false
        binding.mainLoader.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        binding.mainList.alpha = 1F
        binding.mainList.isEnabled = true
        binding.mainLoader.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.findViewById<FloatingActionButton>(R.id.app_fab_scanner)?.hide()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
}