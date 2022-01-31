package com.example.samplejson_jan10.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.samplejson_jan10.model.repository.SampleJsonRepository
import com.example.samplejson_jan10.utils.Resource
import com.example.samplejson_jan10.utils.SelectedData
import com.example.samplejson_jan10.utils.TAG
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class SampleJsonViewModel(
    private val sampleJsonRepository: SampleJsonRepository
) : ViewModel() {

    private var _data: MutableLiveData<Resource<List<Any>>> = MutableLiveData()
    val data: LiveData<Resource<List<Any>>> = _data

    init {
        getTodos()
    }

    var selectedDataType = SelectedData.TODOS
        set(value) {
            Log.d(TAG,"Inside SampleJsonViewModel: selectedDataType: selectedDataType is $selectedDataType")
            when (value) {
                SelectedData.TODOS -> getTodos()
                SelectedData.POSTS -> getPosts()
                SelectedData.USERS -> getUsers()
                SelectedData.PHOTOS -> getPhotos()
            }
            field = value
        }

    private fun getTodos() {
        _data.value = Resource.Loading
        viewModelScope.launch {
            val response = sampleJsonRepository.getTodos()
            _data.postValue(response)
        }
    }

    private fun getPosts() {
        _data.value = Resource.Loading
        viewModelScope.launch {
            val response = sampleJsonRepository.getPosts()
            _data.postValue(response)
        }
    }

    private fun getUsers() {
        _data.value = Resource.Loading
        viewModelScope.launch {
            val response = sampleJsonRepository.getUsers()
            _data.postValue(response)
        }
    }

    private fun getPhotos() {
        Log.d(TAG,"Inside SampleJsonViewModel: getPhotos")
        _data.value = Resource.Loading
        viewModelScope.launch {
            val response = sampleJsonRepository.getPhotos()
            Log.d(TAG,"Inside SampleJsonViewModel: getPhotos: coroutine: response is $response")
            _data.postValue(response)
        }
    }

    class Factory(
        private val sampleJsonRepository: SampleJsonRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SampleJsonViewModel::class.java)) {
                return SampleJsonViewModel(sampleJsonRepository) as T
            } else {
                throw RuntimeException("Could not create instance of TodoViewModel")
            }
        }

    }
}