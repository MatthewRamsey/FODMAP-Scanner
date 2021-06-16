package com.lemick.fodmapscanner.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.databinding.DialogFodmapDetailsBinding
import com.lemick.fodmapscanner.databinding.FragmentProductAnalysisBinding
import com.lemick.fodmapscanner.model.api.model.Product
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import org.koin.android.ext.android.inject


class ProductAnalysisFragment : Fragment() {

    private lateinit var product: Product

    private var _binding: FragmentProductAnalysisBinding? = null
    private val binding get() = _binding!!
    private lateinit var dialogBinding: DialogFodmapDetailsBinding;
    private lateinit var dialogBuilder: AlertDialog;

    private val productAnalysisViewModel: ProductAnalysisViewModel by inject();

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProductAnalysisBinding.inflate(inflater, container, false)
        dialogBinding = DialogFodmapDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductFromBundle()
        initDialog()
        populateUI()
    }

    private fun initDialog() {
        dialogBuilder = AlertDialog.Builder(this.activity).create()
        dialogBuilder.setView(dialogBinding.root)
        dialogBinding.dialogBtnClose.setOnClickListener { dialogBuilder.dismiss() }
    }

    private fun initProductFromBundle() {
        val bundle = arguments
        if (bundle == null) {
            Log.e("Confirmation", "ConfirmationFragment did not receive traveler information")
            return
        }
        val args = ProductAnalysisFragmentArgs.fromBundle(bundle)
        product = args.product
    }

    private fun populateUI() {
        val productHeader: View = layoutInflater.inflate(R.layout.analyzed_ingredient_list_header, binding.productListIngredients, false)

        productAnalysisViewModel.analyzedIngredientsState.observe(viewLifecycleOwner, { analyzedIngredients ->
            val adapter = IngredientListAdapter(requireActivity(), analyzedIngredients)
            binding.productListIngredients.adapter = adapter
            binding.productListIngredients.addHeaderView(productHeader, null, false)
            binding.productListIngredients.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val ingredient = analyzedIngredients[position - 1]
                ingredient.fodmapEntry?.let {
                    dialogBinding.dialogTextIngredient.text = ingredient.ingredient.text
                    loadFodmapLevel(it.details.fructose, dialogBinding.dialogImgFructose, dialogBinding.dialogTextLvlFructose)
                    loadFodmapLevel(it.details.oligos, dialogBinding.dialogImgOligos, dialogBinding.dialogTextLvlOligos)
                    loadFodmapLevel(it.details.lactose, dialogBinding.dialogImgLactose, dialogBinding.dialogTextLvlLactose)
                    loadFodmapLevel(it.details.polyols, dialogBinding.dialogImgPolyols, dialogBinding.dialogTextLvlPolyols)
                    dialogBuilder.show()
                }
            }
        })
        productAnalysisViewModel.startProductAnalysis(product)
        val textProductName = productHeader.findViewById<TextView>(R.id.text_product_name)
        textProductName.text = product.productName
        product.imageFrontSmallUrl?.let {
            val imageProduct = productHeader.findViewById<ImageView>(R.id.image_product)
            Picasso.get()
                .load(it)
                .fit().centerInside()
                .transform(RoundedCornersTransformation(5, 5))
                .placeholder(R.drawable.progress_animation)
                .into(imageProduct)
        }
    }

    fun loadFodmapLevel(level: Int, imageView: ImageView, txtLevel: TextView) {
        when (level) {
            0 -> {
                Picasso.get().load(R.mipmap.ic_valid).into(imageView)
                txtLevel.text = resources.getString(R.string.low)
            }
            1 -> {
                Picasso.get().load(R.mipmap.ic_warning).into(imageView)
                txtLevel.text = resources.getString(R.string.medium)
            }
            2 -> {
                Picasso.get().load(R.mipmap.ic_alert).into(imageView)
                txtLevel.text = resources.getString(R.string.high)
            }
        }
    }


}