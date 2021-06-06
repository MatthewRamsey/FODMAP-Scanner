package com.lemick.fodmapscanner.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.business.FodmapIngredientMapper
import com.lemick.fodmapscanner.databinding.FragmentSummaryProductBinding
import com.lemick.fodmapscanner.model.api.model.Product
import com.lemick.fodmapscanner.model.fodmap.IngredientFodmapResult
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import org.koin.android.ext.android.inject


class SummaryProductFragment : Fragment() {

    private lateinit var product: Product
    private lateinit var ingredientFodmapResults: List<IngredientFodmapResult>

    private var _binding: FragmentSummaryProductBinding? = null
    private val binding get() = _binding!!

    private val fodmapIngredientMapper: FodmapIngredientMapper by inject();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSummaryProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductFromBundle()
        populateUI()
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

    private fun populateUI() {
        val productHeader: View = layoutInflater.inflate(R.layout.indredient_list_header, binding.productListIngredients, false)
        ingredientFodmapResults = fodmapIngredientMapper.searchFodmapEntries(product.ingredients)
        val adapter = IngredientListAdapter(requireActivity(), ingredientFodmapResults)
        binding.productListIngredients.adapter = adapter
        binding.productListIngredients.addHeaderView(productHeader)

        if (product.productName != null) {
            val textProductName = productHeader.findViewById<TextView>(R.id.text_product_name)
            textProductName.text = product.productName
        }
        if (product.imageFrontSmallUrl != null) {
            val imageProduct = productHeader.findViewById<ImageView>(R.id.image_product)
            Picasso.get()
                .load(product.imageFrontUrl)
                .fit().centerInside()
                .transform(CropCircleTransformation())
                .placeholder(R.drawable.progress_animation)
                .into(imageProduct)
        }
    }


}