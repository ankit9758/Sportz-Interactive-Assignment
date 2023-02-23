package com.example.sportzinteractivedemo.match.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sportzinteractivedemo.R
import com.example.sportzinteractivedemo.databinding.ListItemMatchBinding
import com.example.sportzinteractivedemo.match.models.MatchResponseModel
import com.example.sportzinteractivedemo.utils.setSafeOnClickListener

class MatchListAdapter (private val matchInfoListener: MatchInfoListener): RecyclerView.Adapter<MatchListAdapter.MatchRowViewHolder>() {
    private var matchList = ArrayList<MatchResponseModel>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchRowViewHolder {
        val binding = DataBindingUtil.inflate<ListItemMatchBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_match, parent, false
        )
        return MatchRowViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setMatchList(listItems: ArrayList<MatchResponseModel>) {
        this.matchList = listItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MatchRowViewHolder, position: Int) {
        holder.bind(matchList[position])
        holder.itemView.setSafeOnClickListener {
            matchInfoListener.onMatchSelected(matchList[position])
        }
    }

    class MatchRowViewHolder(private val itemBinding: ListItemMatchBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: MatchResponseModel) {
            itemBinding.tvFirstTeam.text = data.teams[0].Name_Full
            itemBinding.tvSecondTeam.text = data.teams[1].Name_Full
            itemBinding.tvVenueTime.text = "${data.Matchdetail?.Match?.Date} ${data.Matchdetail?.Match?.Time} ${data.Matchdetail?.Venue?.Name}"
            itemBinding.executePendingBindings()

        }
    }

    interface MatchInfoListener {
        fun onMatchSelected(data: MatchResponseModel)
    }

}
