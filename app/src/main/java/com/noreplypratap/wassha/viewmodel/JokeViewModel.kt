package com.noreplypratap.wassha.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noreplypratap.wassha.model.UiJokeModel
import com.noreplypratap.wassha.model.toUiModel
import com.noreplypratap.wassha.repository.RepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val repositoryImpl: RepositoryImpl
) : ViewModel() {
    private val _uiJokeModel = MutableLiveData<UiJokeModel>()
    val uiJokeModel: LiveData<UiJokeModel> get() = _uiJokeModel

    init {
        getRemoteData()
    }

    private fun getRemoteData() = viewModelScope.launch(Dispatchers.IO) {
        val data = repositoryImpl.getRemoteData()
        if (data != null) {
            data.let {
                val joke = it.joke.toString()
                val uiJoke = it.toUiModel(false,countWords(joke),countLength(joke))
                _uiJokeModel.postValue(uiJoke)
            }
        }else {
            getLocalData()
        }
    }

    private fun getLocalData() = viewModelScope.launch(Dispatchers.IO) {
        val data = repositoryImpl.getLocalData()
        data?.let {
            val joke = it.joke.toString()
            val uiJoke = it.toUiModel(true,countWords(joke),countLength(joke))
            _uiJokeModel.postValue(uiJoke)
        }
    }

    private fun countWords(input: String): Int {
        val words = input.trim().split("\\s+".toRegex())
        return words.size
    }

    private fun countLength(string: String): Int {
        return string.length
    }

    fun updateJoke() {
        getRemoteData()
    }
}