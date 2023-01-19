package org.csystem.android.app.veterinarian.viewmodel.adapter

import android.content.Context
import android.widget.ArrayAdapter
import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianInfo

class VeterinarianListAdapter(context: Context, items: MutableList<VeterinarianInfo> = arrayListOf())
    : ArrayAdapter<VeterinarianInfo>(context, android.R.layout.simple_list_item_1, items) {
    override fun add(vi: VeterinarianInfo?)
    {
        super.add(vi)
        notifyDataSetChanged()
    }
}