package com.example.imageloader.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.imageloader.databinding.CatalogFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CatalogFragment : DaggerFragment() {
    private lateinit var viewDataBinding: CatalogFragmentBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CatalogViewModel> { viewModelFactory }


    companion object {
        fun newInstance() = CatalogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = CatalogFragmentBinding.inflate(inflater, container,  false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.loadCatalog()
    }

}