package com.example.sportzinteractivedemo.match.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportzinteractivedemo.match.models.MatchResponseModel
import com.example.sportzinteractivedemo.match.repository.MainRepository
import com.example.sportzinteractivedemo.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    private val _matchResponse = MutableLiveData<DataState<ArrayList<MatchResponseModel>>>()
    val matchResponse: LiveData<DataState<ArrayList<MatchResponseModel>>>
        get() = _matchResponse
    init {
        getMatchDetails()
    }

    fun getMatchDetails() {
        viewModelScope.launch {
            repository.getMatchDetails()
                .onEach {
                    _matchResponse.value = it
                }.launchIn(viewModelScope)
        }
    }
}