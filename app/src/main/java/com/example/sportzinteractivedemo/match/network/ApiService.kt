package com.example.sportzinteractivedemo.match.network
import com.example.sportzinteractivedemo.match.models.MatchResponseModel
import com.example.sportzinteractivedemo.match.models.MatchTestResponseModel
import com.example.sportzinteractivedemo.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET(AppConstants.FIRST_MATCH_DETAILS)
    suspend fun getFirstMatchDetails(): Response<MatchTestResponseModel>

    @GET(AppConstants.SECOND_MATCH_DETAILS)
    suspend fun getSecondMatchDetails(): Response<MatchTestResponseModel>
}