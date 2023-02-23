package com.example.sportzinteractivedemo.match.repository

import android.util.Log
import com.example.sportzinteractivedemo.match.models.*
import com.example.sportzinteractivedemo.match.network.ApiService
import com.example.sportzinteractivedemo.utils.*
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiService: ApiService) : SafeApiRequest() {
    private var matchList=ArrayList<MatchResponseModel>()
    fun getMatchDetails(): Flow<DataState<ArrayList<MatchResponseModel>>> = flow {
        emit(DataState.Loading)
        try {

            val firstMatchDetailData = apiRequest {
                apiService.getFirstMatchDetails()
            }
            val secondMatchDetailData = apiRequest {
                apiService.getSecondMatchDetails()
            }
            //first Data
         //   Log.d("Data----1", Gson().toJson(firstMatchDetailData))
            var teamsDataList = ArrayList<TeamsData>()
            firstMatchDetailData.Teams.forEach { (keys, values) ->
                val playerList = ArrayList<PlayersData>()
                values.Players?.forEach { (key, value) ->
                    playerList.add(value)
                }
                teamsDataList.add(TeamsData(values.Name_Full, values.Name_Short, playerList))
                println("Data----3  $keys = $values")
            }
           matchList.add(MatchResponseModel(firstMatchDetailData.Matchdetail, teamsDataList))

           //second Data
             teamsDataList = ArrayList<TeamsData>()
            secondMatchDetailData.Teams.forEach { (keys, values) ->
                val playerList = ArrayList<PlayersData>()
                values.Players?.forEach { (key, value) ->
                    playerList.add(value)
                }
                teamsDataList.add(TeamsData(values.Name_Full, values.Name_Short, playerList))
                println("Data----3  $keys = $values")
            }

            matchList.add(MatchResponseModel(secondMatchDetailData.Matchdetail, teamsDataList))
            emit(DataState.Success(matchList))
            Log.d("Data----final", Gson().toJson(matchList))
        } catch (e: NoInternetException) {
            emit(DataState.Error(Exception("No Internet connection")))
        } catch (e: ApiException) {
            emit(DataState.Error(Exception("Api Exception")))
        } catch (e: ConnectionTimedOutException) {
            emit(DataState.Error(Exception("Connection timed out")))
        } catch (e: Exception) {
            emit(DataState.Error(Exception(e.localizedMessage)))
        }

    }.flowOn(Dispatchers.IO)


}