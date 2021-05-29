package com.lemick.fodmapscanner.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.databinding.FragmentFirstBinding
import com.lemick.fodmapscanner.databinding.FragmentSummaryProductBinding
import java.util.*

class SummaryProductFragment : Fragment() {

    private var _binding: FragmentSummaryProductBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        // 1
        val bundle = arguments
        if (bundle == null) {
            Log.e("Confirmation", "ConfirmationFragment did not receive traveler information")
            return
        }

        val args = SummaryProductFragmentArgs.fromBundle(bundle)
        val product = args.product
       // Arrays.toString(product.ingredients?.map { ingredients ->  ingredients.})
        Log.i("APP", "OUI")

    }
}