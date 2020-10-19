package com.example.imageloader.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.imageloader.CatalogApplication.Companion.BASE_URL
import com.example.imageloader.databinding.ItemDetailsFragmentBinding
import com.example.imageloader.ui.utils.loadImage
import com.example.imageloader.ui.viewmodels.CatalogViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.item_details_fragment.*
import javax.inject.Inject

private const val ARG_IMAGE_URL = "imageUrl"
private const val ARG_ITEM_NAME = "itemName"

/**
 * A simple [Fragment] subclass.
 * Use the [ItemDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemDetailsFragment : DaggerFragment() {
    private var imageURL: String? = null
    private var itemName: String? = null

    private lateinit var viewDataBinding: ItemDetailsFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CatalogViewModel> { viewModelFactory }

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
        viewDataBinding = ItemDetailsFragmentBinding.inflate(inflater, container, false).apply {
            viewmodeldetails = viewModel
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observeItems().value
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