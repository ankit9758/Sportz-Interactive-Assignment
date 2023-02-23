package com.example.sportzinteractivedemo.match.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MatchResponseModel(
    var Matchdetail: MatchDetailData?,
    var teams: ArrayList<TeamsData>
):Parcelable
@Parcelize
data class MatchDetailData(
    val Match: MatchData?,
    var Venue: VenueData?,
):Parcelable
@Parcelize
data class MatchData(
     val Date: String?,
     val Time: String?,
):Parcelable

@Parcelize
data class VenueData(
     val Id: String?,
     val Name: String?,
):Parcelable
@Parcelize
data class TeamsData(
    val Name_Full: String?,
    val Name_Short: String?,
    val Players: ArrayList<PlayersData>?,
):Parcelable
@Parcelize
data class PlayersData(
    val Position: String?,
    val Name_Full: String?,
    val Iskeeper: Boolean?,
    val Iscaptain: Boolean?,
    val Batting: BattingData?,
    val Bowling: BowlingData?,
):Parcelable
@Parcelize
data class BattingData(
    val Style: String?,
    val Average: String?,
    val Strikerate: String?,
    val Runs: String?,
):Parcelable
@Parcelize
data class BowlingData(
    val Style: String?,
    val Average: String?,
    val Economyrate: String?,
    val Wickets: String?,
):Parcelable

