package com.example.sportzinteractivedemo.match.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.sportzinteractivedemo.databinding.FragmentMatchBinding
import com.example.sportzinteractivedemo.match.models.MatchResponseModel
import com.example.sportzinteractivedemo.match.ui.adapter.MatchListAdapter
import com.example.sportzinteractivedemo.match.viewmodels.MainViewModel
import com.example.sportzinteractivedemo.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MatchFragment : Fragment(), MatchListAdapter.MatchInfoListener {
    private var matchResponseList = ArrayList<MatchResponseModel>()
    private var _binding: FragmentMatchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var matchListAdapter: MatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMatchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()

        setupObservers()
    }

    private fun setupViews() {
        matchListAdapter = MatchListAdapter(this)
        binding.rvMatch.apply {
            adapter = matchListAdapter
        }
    }


    private fun setupObservers() {
        viewModel.matchResponse.observe(viewLifecycleOwner) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                    binding.progressBar.showView()
                }
                is DataState.Success -> {
                    binding.progressBar.hideView()
                    matchResponseList = dataState.data
                    setMatchListData()
                }
                is DataState.Error -> {
                    binding.progressBar.hideView()
                    toast("${dataState.exception.message}")
                }
                else -> {

                }
            }
        }

    }

    private fun setMatchListData() {
        matchListAdapter.setMatchList(matchResponseList)
    }


    override fun onMatchSelected(data: MatchResponseModel) {
        val action = MatchFragmentDirections.actionMatchFragmentToPlayersFragment(data)
        findNavController().navigate(action)
    }
}