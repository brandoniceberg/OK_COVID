package com.iceberg.okcovid.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iceberg.okcovid.models.State
import com.iceberg.okcovid.models.US
import com.iceberg.okcovid.repositories.MainActivityRepo

class MainActivityViewModel: ViewModel() {
    private var USdata: MutableLiveData<US> = MutableLiveData()
    private var listOfStates: MutableLiveData<ArrayList<State>> = MutableLiveData()
    private var loading: MutableLiveData<Boolean> = MutableLiveData()
    private val repo = MainActivityRepo()

    fun init() {
        USdata.value = repo.getData().value
        listOfStates.value = repo.getListData().value
        loading.value = repo.isUpdating().value
    }

    fun getData(): LiveData<US> {
        return USdata
    }

    fun getStateData(): LiveData<ArrayList<State>>{
        return listOfStates
    }

    fun isLoading(): LiveData<Boolean>{
     return loading
    }

}