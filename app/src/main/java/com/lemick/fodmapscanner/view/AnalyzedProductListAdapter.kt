package com.lemick.fodmapscanner.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.model.entity.AnalyzedProduct
import com.squareup.picasso.Picasso

class AnalyzedProductListAdapter(
    private val context: Context,
    private val dataSource: List<AnalyzedProduct>
) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val rowView = inflater.inflate(R.layout.main_product_list_view, parent, false)
        val productTextName = rowView.findViewById(R.id.product_text_name) as TextView
        val productImage = rowView.findViewById(R.id.product_image) as ImageView

        val resultItem = dataSource[position];
        productTextName.text = resultItem.productName
        Picasso.get().load(resultItem.thumbnailUrl).into(productImage)
        return rowView
    }
}