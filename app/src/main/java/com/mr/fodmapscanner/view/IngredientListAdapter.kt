package com.mr.fodmapscanner.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mr.fodmapscanner.R
import com.mr.fodmapscanner.model.fodmap.FodmapLevel
import com.mr.fodmapscanner.model.fodmap.AnalyzedIngredient
import com.squareup.picasso.Picasso

class IngredientListAdapter(
    private val context: Context,
    private val dataSource: List<AnalyzedIngredient>
) : BaseAdapter() {

    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

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

        val rowView = inflater.inflate(R.layout.analyzed_ingredient_list_view, parent, false)
        val ingredientTextName = rowView.findViewById(R.id.product_text_name) as TextView
        val ingredientImageFodmap = rowView.findViewById(R.id.product_image) as ImageView

        val resultItem = dataSource[position];
        ingredientTextName.text = resultItem.ingredient.text
        if (resultItem.fodmapEntry != null) {
            if (resultItem.fodmapEntry.fodmap == FodmapLevel.LOW) {
                Picasso.get().load(R.mipmap.ic_valid).into(ingredientImageFodmap)
            } else {
                Picasso.get().load(R.mipmap.ic_alert).into(ingredientImageFodmap)
            }
        } else {
            Picasso.get().load(R.mipmap.ic_unknown).into(ingredientImageFodmap)
        }
        return rowView
    }
}