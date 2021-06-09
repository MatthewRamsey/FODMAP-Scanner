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
            val analyzedProduct = binding.mainList.adapter.getItem(position) as AnalyzedProduct
            productViewModel.fetchProduct(analyzedProduct.productBarcode)
        }
        productViewModel.productState.observe(viewLifecycleOwner, { eventProduct ->
            if (!eventProduct.hasBeenHandled()) {
                val product = eventProduct.contentIfNotHandled()
                findNavController().navigate(
                    MainFragmentDirections.actionFirstFragmentToSummaryProductFragment(product!!)
                )
            }
        })
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