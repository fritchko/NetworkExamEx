package com.example.examtest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examtest.network.repositories.JokesRepo
import com.example.examtest.network.ResponseWrapper
import com.example.examtest.model.local.JokeDataLocal
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class JokesViewModel: ViewModel() {

    var result = MutableLiveData<JokeDataLocal?>()

    fun getJokes(){
        viewModelScope.launch(IO){
            var response = JokesRepo.getJokes()
            if (response is ResponseWrapper.Success){
                result.postValue(response.value)
                Log.i("NETWORK DATA","${response.value}")
            } else if( response is ResponseWrapper.Error){
                Log.e("NETWORK ERROR","|${response.code}| | ${response.message} |")
            }
        }
    }

}