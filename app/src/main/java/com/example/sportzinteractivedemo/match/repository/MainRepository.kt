package com.example.sportzinteractivedemo.match.repository

import com.example.sportzinteractivedemo.match.models.*
import com.example.sportzinteractivedemo.match.network.ApiService
import com.example.sportzinteractivedemo.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MainRepository @Inject constructor(private val apiService: ApiService) : SafeApiRequest() {
    private var matchList = ArrayList<MatchResponseModel>()
    fun getMatchDetails(): Flow<DataState<ArrayList<MatchResponseModel>>> = flow {
        emit(DataState.Loading)
        try {

            val firstMatchDetailData = apiRequest {
                apiService.getMatchDetails(AppConstants.FIRST_MATCH_DETAILS)
            }
            val secondMatchDetailData = apiRequest {
                apiService.getMatchDetails(AppConstants.SECOND_MATCH_DETAILS)
            }
            //first Data
            val firstMatchData=filterMatchData(firstMatchDetailData)
            matchList.add(firstMatchData)
            //second Data
            val secondMatchData=filterMatchData(secondMatchDetailData)
            matchList.add(secondMatchData)

            emit(DataState.Success(matchList))
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

    private fun filterMatchData(matchDetailData: MatchResponseModelDTO): MatchResponseModel {
        val teamsDataList = ArrayList<TeamsData>()
        matchDetailData.Teams.forEach { (keys, values) ->
            val playerList = ArrayList<PlayersData>()
            values.Players?.forEach { (key, value) ->
                playerList.add(value)
            }
            teamsDataList.add(TeamsData(values.Name_Full, values.Name_Short, playerList))
        }
        return MatchResponseModel(matchDetailData.Matchdetail, teamsDataList)
    }


}