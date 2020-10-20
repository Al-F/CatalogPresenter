package com.example.imageloader.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imageloader.databinding.CatalogFragmentBinding
import com.example.imageloader.ui.adapter.CatalogItemAdapter
import com.example.imageloader.ui.model.CatalogItemUi
import com.example.imageloader.ui.viewmodels.CatalogViewModel
import dagger.android.support.DaggerFragment
import java.net.SocketTimeoutException
import javax.inject.Inject

class CatalogFragment : DaggerFragment() {
    private lateinit var viewDataBinding: CatalogFragmentBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<CatalogViewModel>(
        { requireActivity() },
        { viewModelFactory })

    private lateinit var adapter: CatalogItemAdapter
    private lateinit var itemSelectedListener: OnCatalogItemSelected

    companion object {
        fun newInstance() = CatalogFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCatalogItemSelected) {
            itemSelectedListener = context
        } else {
            throw ClassCastException("$context must implement OnImageSelected.")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = CatalogFragmentBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        val activity = activity as Context
        adapter = CatalogItemAdapter()

        val recyclerView = viewDataBinding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(activity, 2)
        recyclerView.adapter = adapter

        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner

        viewModel.observeItems().observe(viewLifecycleOwner, {
            it?.let {
                adapter.renderables = it
            }
        })
        viewModel.observeFailure().observe(viewLifecycleOwner, Observer { failure ->
            when (failure.error) {
                is SocketTimeoutException -> Toast.makeText(
                    requireContext(),
                    "Content is not available in your region",
                    Toast.LENGTH_LONG
                ).show()
                else -> Toast.makeText(
                    requireContext(),
                    "Server error, try again later",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
        adapter.observeSelectedForExpantion().observe(viewLifecycleOwner, Observer {
            itemSelectedListener.onCatalogItemSelected(it)
            viewModel.onItemSelected(it)
        })
    }

    interface OnCatalogItemSelected {
        fun onCatalogItemSelected(model: CatalogItemUi)
    }
}