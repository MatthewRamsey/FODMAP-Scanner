package com.lemick.fodmapscanner.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.model.fodmap.IngredientFodmapResult

class IngredientListAdapter(
    private val context: Context,
    private val dataSource: List<IngredientFodmapResult>
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

        val rowView = inflater.inflate(R.layout.ingredient_list_view, parent, false)
        val textViewIngredientName = rowView.findViewById(R.id.ingredient_name) as TextView
        val textViewFodmapResult = rowView.findViewById(R.id.fodmap_result) as TextView

        val resultItem = dataSource[position];
        textViewIngredientName.text = resultItem.ingredient.text
        if (resultItem.fodmapEntry != null) {
            textViewFodmapResult.text = resultItem.fodmapEntry.fodmap
        } else {
            textViewFodmapResult.text = "Unknown"
        }
        return rowView
    }
}