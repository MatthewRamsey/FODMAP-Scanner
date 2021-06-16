package com.lemick.fodmapscanner.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lemick.fodmapscanner.databinding.FragmentDonateBinding


class DonateFragment : Fragment() {

    lateinit var _binding: FragmentDonateBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDonateBinding.inflate(inflater, container, false)
        return _binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.donateButtonUrl.setOnClickListener {
            val browse = Intent(Intent.ACTION_VIEW, Uri.parse("https://buymeacoffee.com/lemick"))
            startActivity(browse)
        }
    }
}