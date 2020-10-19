package com.example.imageloader.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.R
import com.example.imageloader.ui.model.CatalogItemUi
import kotlin.properties.Delegates

class CatalogItemAdapter : RecyclerView.Adapter<CatalogItemViewHolder>() {

    private var imageSelectedForExpantion = MutableLiveData<CatalogItemUi>()
    fun observeSelectedForExpantion(): LiveData<CatalogItemUi> = imageSelectedForExpantion

    var renderables: List<CatalogItemUi> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return CatalogItemViewHolder(view)
    }

    override fun getItemCount(): Int = renderables.size

    override fun onBindViewHolder(holder: CatalogItemViewHolder, position: Int) {
        holder.bind(renderables[position])
        holder.item.setOnClickListener {
            this.imageSelectedForExpantion.value = renderables[position]
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.catalog_item
    }
}
