package com.example.myrecipes.ui.view.Home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipes.R
import com.example.myrecipes.databinding.FragmentHomeBinding
import com.example.myrecipes.domain.model.Recipe
import com.example.myrecipes.ui.view.viewmodel.RecipesViewModel
import com.example.myrecipes.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _mBinding: FragmentHomeBinding? = null
    private val mBinding get() = _mBinding!!

    private var list: List<Recipe> = emptyList()
    private var listFilter: List<Recipe> = emptyList()

    private val recipesViewModel: RecipesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeRecipesState()
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        _mBinding = null
    }

    private fun init() {
        mBinding.rvRecipes.layoutManager = LinearLayoutManager(requireContext())

        mBinding.swipe.setOnRefreshListener {
            showView(visibilityLoading = true, visibilityRecycler = false)
            recipesViewModel.getAllRecipes()
            mBinding.swipe.isRefreshing = false
            mBinding.scrollView.scrollTo(0,0)
        }

        mBinding.svRecipes.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { string ->
                    if (string.isNotEmpty() && string.isNotBlank()) {
                        listFilter = list.filter {
                            it.nombre.uppercase()
                                .contains(string.uppercase()) || it.ingredientes.toString()
                                .uppercase().contains(string.uppercase())
                        }
                    } else {
                        listFilter = emptyList()
                        listFilter = list
                    }
                    initRecyclerView(listFilter)
                }
                return false
            }

        })
    }

    private fun observeRecipesState() {
        recipesViewModel.recipesState.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    showView(visibilityLoading = true, visibilityRecycler = false)
                    mBinding.sfLoading.isShimmerStarted
                }
                is Resource.Success -> {
                    if (!result.data.isNullOrEmpty()) {
                        list = result.data
                        initListFilter(list)
                    } else {
                        initRecyclerView(emptyList())
                    }
                }
                is Resource.Error -> {
                    list = emptyList()
                    showView(visibilityLoading = false, visibilityRecycler = true)
                    initRecyclerView(emptyList())
                    Toast.makeText(requireContext(), "Error: ${result.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showView(visibilityLoading: Boolean, visibilityRecycler: Boolean) {
        mBinding.viewLoading.isVisible = visibilityLoading
        mBinding.rvRecipes.isVisible = visibilityRecycler
    }

    private fun initRecyclerView(recipes: List<Recipe>) {
        showView(visibilityLoading = false, visibilityRecycler = true)
        mBinding.rvRecipes.adapter = HomeAdapter(requireContext(), recipes) { recipe ->
            val bundle = Bundle()
            bundle.putParcelable("dataRecipe", recipe)
            findNavController().navigate(R.id.detailFragment, bundle)
        }
    }

    private fun initListFilter(list: List<Recipe>) {
        val query = mBinding.svRecipes.query.toString()
        if (query.isNotEmpty() && query.isNotBlank()) {
            listFilter = list.filter {
                it.nombre.uppercase().contains(query.uppercase()) || it.ingredientes.toString()
                    .uppercase().contains(query.uppercase())
            }
            initRecyclerView(listFilter)
        } else {
            initRecyclerView(list)
        }
    }

}