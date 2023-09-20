package com.example.examtest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class JokesViewModel: ViewModel() {

    var result = MutableLiveData<JokeData?>()

    fun getJokes(){
        viewModelScope.launch(IO){
            var response = JokesRepo.getJokes()
            if (response?.isSuccessful == true){
                result.postValue(response.body())
                Log.i("NETWORK DATA","${response.body()}")
            } else {
                Log.e("NETWORK ERROR","CHE MINCHIA FAI")
            }
        }
    }

}