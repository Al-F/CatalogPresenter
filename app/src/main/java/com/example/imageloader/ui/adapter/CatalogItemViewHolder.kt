package com.example.imageloader.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.ui.model.CatalogItemUI
import com.example.imageloader.ui.utils.loadImage
import kotlinx.android.synthetic.main.catalog_item.view.*

class CatalogItemViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
    fun bind(model: CatalogItemUI) {
        //TODO: handle FileNotFoundException
        item.preview_image.loadImage(BASE_URL + model.url)
        item.item_name_preview.text = model.name
    }

    companion object {
        //TODO: change to injecting it
        const val BASE_URL = "http://mobcategories.s3-website-eu-west-1.amazonaws.com"
    }
}