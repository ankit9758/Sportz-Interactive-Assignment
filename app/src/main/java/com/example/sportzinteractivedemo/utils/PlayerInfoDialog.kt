package com.example.sportzinteractivedemo.utils

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialog
import androidx.fragment.app.Fragment
import com.example.sportzinteractivedemo.databinding.DialogPlayerInfoBinding
import com.example.sportzinteractivedemo.match.models.PlayersData


class PlayerInfoDialog(context: Context,val  playersData: PlayersData) : AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DialogPlayerInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        with(binding){
            //setPlayerData
            txtPlayerName.text="${playersData.Name_Full} ${getPlayerType(playersData.Iscaptain?:false,playersData.Iskeeper?:false)}"
            //set batting Data
            txtBattingStyle.text="Style:- ${playersData.Batting?.Style?:"N.A"}"
            txtBattingRuns.text="Runs:- ${playersData.Batting?.Runs?:"N.A"}"
            txtBattingAverage.text="Average:- ${playersData.Batting?.Average?:"N.A"}"
            txtStrikeRate.text="Strike Rate:- ${playersData.Batting?.Strikerate?:"N.A"}"

            //set Bowling Data
            txtBowlingStyle.text="End:- ${playersData.Bowling?.Style?:"N.A"}"
            txtBowlingEconomy.text="Economy:- ${playersData.Bowling?.Economyrate?:"N.A"}"
            txtBowlingAverage.text="Average:- ${playersData.Bowling?.Average?:"N.A"}"
            txtWickets.text="Wickets:- ${playersData.Bowling?.Wickets?:"N.A"}"
        }
        binding.ivClose.setSafeOnClickListener {
            dismiss()
        }
    }
}

fun Fragment.showPlayerInfo(playersData: PlayersData): Lazy<PlayerInfoDialog> = lazy {
    PlayerInfoDialog(requireContext(),playersData)
}
