package com.lemick.fodmapscanner.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lemick.fodmapscanner.business.IngredientParser
import com.lemick.fodmapscanner.databinding.FragmentSummaryProductBinding
import com.lemick.fodmapscanner.model.api.model.Product
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class SummaryProductFragment : Fragment() {

    private lateinit var product: Product
    private var _binding: FragmentSummaryProductBinding? = null
    private val binding get() = _binding!!

    private val ingredientParser: IngredientParser by inject();

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
        binding.button.setOnClickListener {
            val result = ingredientParser.searchFodmapEntries(product.ingredients)
        }
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