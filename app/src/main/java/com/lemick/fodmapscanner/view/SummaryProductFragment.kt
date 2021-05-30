package com.lemick.fodmapscanner.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lemick.fodmapscanner.databinding.FragmentSummaryProductBinding
import com.lemick.fodmapscanner.model.api.model.Product

class SummaryProductFragment : Fragment() {

    private lateinit var product: Product
    private var _binding: FragmentSummaryProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSummaryProductBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProductFromBundle()
    }

    private fun initProductFromBundle() {
        val bundle = arguments
        if (bundle == null) {
            Log.e("Confirmation", "ConfirmationFragment did not receive traveler information")
            return
        }
        val args = SummaryProductFragmentArgs.fromBundle(bundle)
        product = args.product
    }
}