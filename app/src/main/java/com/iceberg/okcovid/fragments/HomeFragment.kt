package com.iceberg.okcovid.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.iceberg.okcovid.R
import com.iceberg.okcovid.StatesListActivity
import com.iceberg.okcovid.adapters.TopStatesAdapter
import com.iceberg.okcovid.models.State
import com.iceberg.okcovid.models.US
import com.iceberg.okcovid.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.*
import java.text.NumberFormat

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment() : Fragment(R.layout.fragment_home) {

    private lateinit var sortedList: List<State>
    private lateinit var listOfStates: ArrayList<State>
    private lateinit var usData: US
    private val model = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.init()
        usData = model.getData().value!!
        listOfStates = model.getStateData().value!!
        runBlocking {
            sortArrayList()
        }

    }

    private suspend fun sortArrayList() {
        val value = GlobalScope.async {
            withContext(Dispatchers.Default){
                sortedList = listOfStates.sortedByDescending { it.positive }
            }
        }
        value.await()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateViews()

        seeallButton.setOnClickListener {
            showList()
        }

        initRecyclerView()
    }

    private fun inflateViews() {
        positiveNumberView.text = NumberFormat.getIntegerInstance().format(usData.positive)
        recoveredNumberView.text = NumberFormat.getIntegerInstance().format(usData.recovered)
        deathNumberView.text = NumberFormat.getIntegerInstance().format(usData.death)
        negativeNumberView.text = NumberFormat.getIntegerInstance().format(usData.negative)
        hospitalizedNumberView.text = NumberFormat.getIntegerInstance().format(usData.hospitalizedCurrently)
        icuNumberView.text = NumberFormat.getIntegerInstance().format(usData.inIcuCurrently)
        dateTextView.text = "Last Modified: ${usData.lastModified}"
    }

    private fun initRecyclerView() {
        topstatesRecyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        topstatesRecyclerView.adapter = TopStatesAdapter(sortedList)
    }

    private fun showList() {
        val intent = Intent(activity, StatesListActivity::class.java)
        intent.putParcelableArrayListExtra("statesList", listOfStates)
        startActivity(intent)
    }

}
