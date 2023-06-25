package com.example.searchapp.presentation.ui

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.searchapp.R
import com.example.searchapp.data.util.Constants
import com.example.searchapp.data.util.Network.isNetworkAvailable
import com.example.searchapp.data.util.SharedPreference
import com.example.searchapp.databinding.FragmentHomeBinding
import com.example.searchapp.presentation.adapter.HomeAdapter
import com.example.searchapp.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var homeAdapter: HomeAdapter

    @Inject
    lateinit var homeViewModel: HomeViewModel

    @Inject
    lateinit var sharedPreferences: SharedPreference

    private lateinit var lastSearchQuery: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        lastSearchQuery = sharedPreferences.getLastSearchQuery()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        observeImageData()
        loadData(lastSearchQuery)

        binding.swipeRefresh.setOnRefreshListener {
            loadData(lastSearchQuery)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.homeRecyclerView.apply {
            adapter = homeAdapter
        }
    }

    private fun setupSearchView() {
        binding.homeSearchView.apply {
            setQuery(lastSearchQuery, false)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        saveLastSearchQuery(it)
                        loadData(it)
                        hideKeyboard()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
        }

    }

    private fun loadData(query: String) {
        binding.homeRecyclerView.visibility = View.GONE
        binding.progress.loadingProgress.visibility = View.VISIBLE

        homeViewModel.getImages(Constants.API_KEY, query)
    }

    private fun observeImageData() {
        homeViewModel.images.observe(viewLifecycleOwner, Observer { response ->
            response.body()?.let { hits ->
                if(response.body()!!.total == 0){
                    binding.noResult.visibility = View.VISIBLE
                    binding.swipeRefresh.isRefreshing = false
                    binding.swipeRefresh.visibility =  View.GONE
                }else {
                    binding.swipeRefresh.visibility =  View.VISIBLE

                    binding.noResult.visibility = View.GONE
                    binding.errortextview.visibility = View.GONE
                    homeAdapter.differ.submitList(response.body()!!.hits)
                    homeAdapter.setOnItemClickListener { imageResponse ->
                        // Handle item click event here
                        val imageUrl = imageResponse.previewURL
                        val comments = imageResponse.comments
                        val likes = imageResponse.likes
                        val tags = imageResponse.tags
                        val downloads = imageResponse.downloads
                        val username = imageResponse.user
                        val userImage = imageResponse.userImageURL


                        ImageDetailsActivity.start(requireContext(), imageUrl, comments, likes, tags, downloads, userImage, username)
                    }

                }
                binding.homeRecyclerView.visibility = View.VISIBLE
                binding.progress.loadingProgress.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
            } ?: run {
                response.errorBody()?.let { showErrorToast( "Error: ${response.code()}" + " "+  it.string())  }
                binding.progress.loadingProgress.visibility = View.GONE
                binding.swipeRefresh.isRefreshing = false
                binding.errortextview.text = resources.getString(R.string.errorText)
                binding.errortextview.visibility = View.VISIBLE
            }
        })
    }

    private fun showErrorToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun saveLastSearchQuery(query: String) {
        lastSearchQuery = query
        sharedPreferences.saveLastSearchQuery(query)
    }

    private fun hideKeyboard() {
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.homeSearchView.windowToken, 0)
    }
}