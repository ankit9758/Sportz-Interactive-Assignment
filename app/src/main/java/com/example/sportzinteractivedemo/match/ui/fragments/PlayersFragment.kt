package com.example.sportzinteractivedemo.match.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sportzinteractivedemo.R
import com.example.sportzinteractivedemo.databinding.FragmentPlayersBinding
import com.example.sportzinteractivedemo.match.models.MatchResponseModel
import com.example.sportzinteractivedemo.match.models.PlayersData
import com.example.sportzinteractivedemo.match.models.TeamsData
import com.example.sportzinteractivedemo.match.ui.adapter.TeamAdapter
import com.example.sportzinteractivedemo.utils.hideView
import com.example.sportzinteractivedemo.utils.invisibleView
import com.example.sportzinteractivedemo.utils.showPlayerInfo
import com.example.sportzinteractivedemo.utils.showView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersFragment : Fragment() , TeamAdapter.PlayerInfoListener {
    private var teamDataList = ArrayList<TeamsData>()
    private lateinit var matchResponseModel: MatchResponseModel
    private var _binding: FragmentPlayersBinding? = null
    private lateinit var firstTeamAdapter: TeamAdapter
    private lateinit var secondTeamAdapter: TeamAdapter
    private val binding get() = _binding!!
    private val args: PlayersFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setData()
        clickListeners()
    }

    private fun clickListeners() {
        binding.rgTeamFilter.setOnCheckedChangeListener { _, i ->
            when(i){
                R.id.radioTeamA->{
                    binding.rvTeamB.hideView()
                    binding.tvSecondTeam.hideView()
                    binding.tvFirstTeam.hideView()
                    binding.rvTeamA.showView()

                }
                R.id.radioTeamB->{
                    binding.rvTeamA.hideView()
                    binding.tvFirstTeam.hideView()
                    binding.tvSecondTeam.hideView()
                    binding.rvTeamB.showView()

                }
                R.id.radioBothTeam->{

                    binding.rvTeamA.showView()
                    binding.rvTeamB.showView()

                    binding.tvFirstTeam.showView()
                    binding.tvSecondTeam.showView()

                }
            }
        }

        binding.radioTeamA.isChecked=true

    }


    private fun setupViews() {
        firstTeamAdapter = TeamAdapter(this)
        secondTeamAdapter = TeamAdapter(this)

        binding.rvTeamA.apply {
            adapter = firstTeamAdapter
        }
        binding.rvTeamB.apply {
            adapter = secondTeamAdapter
        }
        matchResponseModel = args.matchData


    }

    private fun setData() {
        if(::matchResponseModel.isInitialized){
            teamDataList=matchResponseModel.teams
            //set team A Data
            firstTeamAdapter.setPlayerList(teamDataList[0].Players?: ArrayList())
            binding.tvFirstTeam.text=teamDataList[0].Name_Full
            binding.radioTeamA.text=teamDataList[0].Name_Full
            //set Team B Data
            secondTeamAdapter.setPlayerList(teamDataList[1].Players?:ArrayList())
            binding.tvSecondTeam.text=teamDataList[1].Name_Full
            binding.radioTeamB.text=teamDataList[1].Name_Full


        }
    }

    override fun onPlayerSelected(data: PlayersData) {
        val playerInfoDialog by showPlayerInfo(data)
        playerInfoDialog.show()
    }
}