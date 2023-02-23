package com.example.sportzinteractivedemo.match.network
import com.example.sportzinteractivedemo.match.models.MatchResponseModel
import com.example.sportzinteractivedemo.match.models.MatchTestResponseModel
import com.example.sportzinteractivedemo.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("{matchPath}")
    suspend fun getMatchDetails(@Path("matchPath") matchPath:String): Response<MatchTestResponseModel>
}