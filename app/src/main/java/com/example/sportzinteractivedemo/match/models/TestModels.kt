package com.example.sportzinteractivedemo.match.models


data class MatchTestResponseModel(
    var Matchdetail:MatchDetailData?,
    var Teams:Map<String,TeamTestDataModel>
)
data class TeamTestDataModel(
      val Name_Full:String?,
      val Name_Short:String?,
      val Players:HashMap<String,PlayersData>?,
)
