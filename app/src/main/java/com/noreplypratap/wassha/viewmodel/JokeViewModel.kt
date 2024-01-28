package com.noreplypratap.wassha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noreplypratap.wassha.model.ModelJoke
import com.noreplypratap.wassha.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {

    private val _joke = MutableLiveData<String>()
    val joke: LiveData<String> get() = _joke

    private val _isLocal = MutableLiveData<Boolean>()
    val isLocal: LiveData<Boolean> get() = _isLocal

    init {
        _isLocal.postValue(false)
        getRemoteData()
    }

    private fun getRemoteData() = viewModelScope.launch(Dispatchers.IO) {
        try {
            handleResponse(repositoryImpl.getRemoteData())
        } catch (e: Exception) {
            getLocalData()
        }
    }

    private fun handleResponse(response: Response<ModelJoke>) {
        if (response.isSuccessful) {
            response.body()?.let { jokesData ->
                _joke.postValue(jokesData.joke.toString())
                _isLocal.postValue(false)
                savaDataToLocal(jokesData)
            }
        } else {
            getLocalData()
        }
    }

    private fun savaDataToLocal(modelJoke: ModelJoke) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.saveData(modelJoke)
        }
    }

    private fun getLocalData() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLocal.postValue(true)
            _joke.postValue(repositoryImpl.getLocalData()?.joke.toString())
        }
    }

    fun updateJoke() {
        getRemoteData()
    }
}