package com.example.sportzinteractivedemo.match.models


data class MatchResponseModelDTO(
    var Matchdetail:MatchDetailData?,
    var Teams:Map<String,TeamTestDataModelDTO>
)
data class TeamTestDataModelDTO(
      val Name_Full:String?,
      val Name_Short:String?,
      val Players:HashMap<String,PlayersData>?,
)
