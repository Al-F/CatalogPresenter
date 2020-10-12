package com.example.imageloader.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.R
import com.example.imageloader.ui.model.CatalogItemUI
import kotlin.properties.Delegates

class CatalogItemAdapter : RecyclerView.Adapter<CatalogItemViewHolder>() {

    private var imageSelectedForExpantion = MutableLiveData<CatalogItemUI>()
    fun observeSelectedForExpantion(): LiveData<CatalogItemUI> = imageSelectedForExpantion

    var renderables: List<CatalogItemUI> by Delegates.observable(emptyList()) { _, _, _ ->
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
            val alertDialog = AlertDialog.Builder(it.context).also { builder ->
                builder.setTitle("Open image")
                builder.setMessage("Do you want to see more details?")

                builder.setPositiveButton("Yes") { dialog, which ->
                    this.imageSelectedForExpantion.value = renderables[position]
                    dialog.dismiss()
                }
                builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            }.create()
            alertDialog.show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.catalog_item
    }
}
