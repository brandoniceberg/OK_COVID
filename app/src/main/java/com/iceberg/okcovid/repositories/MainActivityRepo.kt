package com.iceberg.okcovid.repositories

import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.iceberg.okcovid.models.State
import com.iceberg.okcovid.models.US
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import okhttp3.*
import java.lang.reflect.Type

class MainActivityRepo {
    private val TAG = "MainActivityRepo"

    private var loading: Boolean = false
    private var dataset: US? = null
    private lateinit var listOfStates: ArrayList<State>
    private val client: OkHttpClient = OkHttpClient()

    fun getData(): MutableLiveData<US> {
        loading = true
        runBlocking {
            usData()
        }
        val data: MutableLiveData<US> = MutableLiveData()
        data.value = dataset

        loading = false
        return data
    }

    fun getListData(): MutableLiveData<ArrayList<State>> {
        loading = true
        runBlocking{
            stateData()
        }
        val data: MutableLiveData<ArrayList<State>> = MutableLiveData()
        data.value = listOfStates

        loading = false
        return data
    }

    fun isUpdating(): MutableLiveData<Boolean> {
        val data: MutableLiveData<Boolean> = MutableLiveData()
        data.value = loading

        return data
    }

    private suspend fun stateData() {
        val value = GlobalScope.async {
            withContext(Dispatchers.IO){
                val url = "https://covidtracking.com/api/v1/states/current.json"

                val request = Request.Builder()
                    .url(url)
                    .build()

                val collectionType: Type = object : TypeToken<ArrayList<State>>(){}.type

                val body = client.newCall(request).execute().body?.string()

                listOfStates = GsonBuilder().create().fromJson(body, collectionType)
            }
        }
        value.await()
    }

    private suspend fun usData() {
        val value = GlobalScope.async{
            withContext(Dispatchers.IO) {
                val url = "https://covidtracking.com/api/v1/us/current.json"

                val client = OkHttpClient()

                val request = Request.Builder()
                    .url(url)
                    .build()

                val collectionType: Type = object : TypeToken<ArrayList<US>>(){}.type

                val body = client.newCall(request).execute().body?.string()

                val collection = GsonBuilder().create().fromJson<ArrayList<US>>(body, collectionType)

                dataset = collection[0]
            }
        }
        value.await()
    }
}