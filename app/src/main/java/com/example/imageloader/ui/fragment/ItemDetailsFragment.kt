package com.example.imageloader.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
 * A simple [DaggerFragment] subclass to show detailed information about the item.
 * Use the [ItemDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemDetailsFragment : DaggerFragment() {
    private lateinit var viewDataBinding: ItemDetailsFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CatalogViewModel>(
        { requireActivity() },
        { viewModelFactory })

    companion object {
        fun newInstance() = ItemDetailsFragment()
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
        viewModel.loadCatalog()
        item_details_image.loadImage(BASE_URL + viewModel.selectedItem.url)
        item_details_name.text = viewModel.selectedItem.name
    }
}