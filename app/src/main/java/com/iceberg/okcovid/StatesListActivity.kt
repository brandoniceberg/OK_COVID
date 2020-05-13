package com.iceberg.okcovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.iceberg.okcovid.adapters.StatesListAdapter
import com.iceberg.okcovid.models.State
import kotlinx.android.synthetic.main.activity_states_list.*
import kotlinx.coroutines.*
import javax.sql.StatementEvent

class StatesListActivity : AppCompatActivity() {

    private val TAG = "StatesListActivity"
    private lateinit var listOfStates: ArrayList<State>
    private val options = arrayOf("Alphabetical", "Grade", "Positive Cases")
    private lateinit var sortedList: List<State>
    private var optionChecked = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_states_list)

        listOfStates = intent.getParcelableArrayListExtra("statesList")!!
        runBlocking {
            sortByNumber()
        }

        statesRecyclerView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        val actionBar: ActionBar = supportActionBar!!
        actionBar.title = "States"
        actionBar.setDisplayHomeAsUpEnabled(true)

        floatingActionButton.setOnClickListener {
            createDialog()
        }

        initRecyclerView()
    }

    private fun createDialog() {
        //create dialog options box
        MaterialAlertDialogBuilder(this)
            .setTitle("Filter:")
            .setNeutralButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton("OK") {dialog, which ->
                //Positive action
                statesRecyclerView.adapter?.notifyDataSetChanged()
            }
            .setSingleChoiceItems(options, optionChecked){ _, item ->
                // Respond to item chosen

                when (options[item]) {
                    "Alphabetical" -> {
                        optionChecked = 0
                        runBlocking {
                            sortByName()
                        }
                    }
                    "Grade" -> {
                        optionChecked = 1
                        runBlocking {
                            sortByGrade()
                        }
                    }
                    "Positive" -> {
                        optionChecked = 2
                        runBlocking {
                            sortByNumber()
                        }
                    }
                }
            }
            .show()
        }

    private fun initRecyclerView() {
        statesRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        statesRecyclerView.adapter = StatesListAdapter(sortedList)
    }

    private suspend fun sortByName() {
        val value = GlobalScope.async {
            withContext(Dispatchers.Default){
                sortedList = listOfStates.sortedBy { it.state }
            }
        }
        value.await()
    }

    private suspend fun sortByGrade() {
        val value = GlobalScope.async {
            withContext(Dispatchers.Default) {
                sortedList = listOfStates.sortedBy { it.dataQualityGrade }
            }
        }
        value.await()
    }

    private suspend fun sortByNumber() {
        val value = GlobalScope.async {
            withContext(Dispatchers.Default) {
                sortedList = listOfStates.sortedByDescending { it.positive }
            }
        }
        value.await()
    }
}
