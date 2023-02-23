package com.example.sportzinteractivedemo.match.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractivedemo.R
import com.example.sportzinteractivedemo.databinding.ListItemPlayerBinding
import com.example.sportzinteractivedemo.match.models.PlayersData
import com.example.sportzinteractivedemo.utils.getPlayerType
import com.example.sportzinteractivedemo.utils.setSafeOnClickListener

class TeamAdapter (private val playerInfoListener: PlayerInfoListener): RecyclerView.Adapter<TeamAdapter.TeamRowViewHolder>() {
    private var playersData = ArrayList<PlayersData>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamRowViewHolder {
        val binding = DataBindingUtil.inflate<ListItemPlayerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_player, parent, false
        )
        return TeamRowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return playersData.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setPlayerList(listItems: ArrayList<PlayersData>) {
        this.playersData = listItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TeamRowViewHolder, position: Int) {
        holder.bind(playersData[position])
        holder.itemView.setSafeOnClickListener {
            playerInfoListener.onPlayerSelected(playersData[position])
        }
    }

    class TeamRowViewHolder(private val itemBinding: ListItemPlayerBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: PlayersData) {
            itemBinding.tvPlayerName.text="${data.Name_Full} ${getPlayerType(data.Iscaptain?:false,data.Iskeeper?:false)}"
            itemBinding.tvPlayerType.text=data.Batting?.Style?:""
            itemBinding.executePendingBindings()
        }
    }

    interface PlayerInfoListener {
        fun onPlayerSelected(data: PlayersData)
    }
}