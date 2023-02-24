package com.example.sportzinteractivedemo.match.network
import com.example.sportzinteractivedemo.match.models.MatchResponseModelDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("{matchPath}")
    suspend fun getMatchDetails(@Path("matchPath") matchPath:String): Response<MatchResponseModelDTO>
}