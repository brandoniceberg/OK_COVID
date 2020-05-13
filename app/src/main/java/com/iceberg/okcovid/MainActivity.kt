package com.iceberg.okcovid

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.iceberg.okcovid.fragments.HomeFragment
import com.iceberg.okcovid.models.State
import com.iceberg.okcovid.models.US
import com.iceberg.okcovid.viewmodels.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private val title = "United States"
    private lateinit var usData: US
    private lateinit var FragmentManager: FragmentManager
    private val model = MainActivityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationMenuView: View = findViewById(R.id.bottomNavigationView)
        val toolbar = supportActionBar!!
        toolbar.title = title

        bottomNavigationMenuView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        FragmentManager = supportFragmentManager

        FragmentManager.beginTransaction()
            .add(R.id.container, HomeFragment())
            .commit()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> {
                    Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.map_item -> {
                    Toast.makeText(this, "Map", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }

        bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.home_item -> {
                    //Check for update from network
                }
                R.id.map_item -> {
                    //Check for update from network
                }
            }
        }
    }

    private fun manageFragmentTransaction(selectedFrag: String) {
        when (selectedFrag) {
            "HomeFragment" -> {
                if (FragmentManager.findFragmentByTag("HomeFragment") != null){
                    FragmentManager.beginTransaction().show(HomeFragment()).addToBackStack(null).commit()
                } else {
                    FragmentManager.beginTransaction().add(R.id.container, HomeFragment()).commit()
                }
            }

            "MapFragment" -> {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        FragmentManager.beginTransaction().remove(HomeFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.share_item -> {
                model.init()
                usData = model.getData().value!!
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "$title COVID-19 Statistics: \n" +
                            "Positive Cases: ${NumberFormat.getIntegerInstance().format(usData.positive)} \n" +
                            "Negative Cases: ${NumberFormat.getIntegerInstance().format(usData.negative)} \n" +
                            "Currently in ICU: ${NumberFormat.getIntegerInstance().format(usData.inIcuCurrently)} \n" +
                            "Currently Hospitalized: ${NumberFormat.getIntegerInstance().format(usData.hospitalizedCurrently)} \n" +
                            "Recoverd: ${NumberFormat.getIntegerInstance().format(usData.recovered)} \n" +
                            "Total Test Results: ${NumberFormat.getIntegerInstance().format(usData.totalTestResults)} \n" +
                            "Last Modified: ${usData.lastModified} \n" +
                            "*Information provided by The COVID Tracking Project")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }

            R.id.tips_item -> {
                Toast.makeText(this, "Health & Safety Tips", Toast.LENGTH_SHORT).show()
            }

            R.id.settings_item -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
