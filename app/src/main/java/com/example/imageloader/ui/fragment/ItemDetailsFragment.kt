package com.example.imageloader.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.imageloader.CatalogApplication.Companion.BASE_URL
import com.example.imageloader.R
import com.example.imageloader.ui.utils.loadImage
import kotlinx.android.synthetic.main.fragment_item_details.*

private const val ARG_IMAGE_URL = "imageUrl"
private const val ARG_ITEM_NAME = "itemName"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemDetailsFragment : Fragment() {
    private var imageURL: String? = null
    private var itemName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageURL = it.getString(ARG_IMAGE_URL)
            itemName = it.getString(ARG_ITEM_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item_details_image.loadImage(BASE_URL + imageURL)
        item_details_name.text = itemName
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param imageURL Parameter 1.
         * @param itemDescription Parameter 2.
         * @return A new instance of fragment ItemDetailsFragment.
         */
        @JvmStatic
        fun newInstance(imageURL: String, itemDescription: String) =
            ItemDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE_URL, imageURL)
                    putString(ARG_ITEM_NAME, itemDescription)
                }
            }
    }
}