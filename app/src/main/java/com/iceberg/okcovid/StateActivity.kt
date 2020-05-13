package com.iceberg.okcovid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.iceberg.okcovid.models.State
import kotlinx.android.synthetic.main.activity_state.*
import kotlinx.coroutines.*
import java.text.NumberFormat

class StateActivity : AppCompatActivity() {

    private lateinit var state: State
    var capitalImage: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state)

        //Set edge-to-edge attributes
        capitalImageView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        informationCard.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

        state = intent.getParcelableExtra("state")!!
        val actionBar: ActionBar = supportActionBar!!
        actionBar.title = state.state
        actionBar.setDisplayHomeAsUpEnabled(true)

        fillViews()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.states_menu, menu)
        return true
    }

    private fun fillViews() {
        statenameTextView.text = runBlocking { getFullStateName() }
        when (state.dataQualityGrade) {
            "A+" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.a_color))
            "A" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.a_color))
            "A-" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.a_color))
            "B+" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.b_color))
            "B" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.b_color))
            "B-" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.b_color))
            "C+" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.c_color))
            "C" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.c_color))
            "C-" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.c_color))
            "D+" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.d_color))
            "D" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.d_color))
            "D-" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.d_color))
            "F" -> gradeTextView.setTextColor(ContextCompat.getColor(this, R.color.f_color))
        }
        gradeTextView.text = state.dataQualityGrade
        Glide.with(this).load(capitalImage).into(capitalImageView)
        positiveNumberView.text = NumberFormat.getIntegerInstance().format(state.positive)
        negativeNumberView.text = NumberFormat.getIntegerInstance().format(state.negative)
        hospitalNumberView.text = NumberFormat.getIntegerInstance().format(state.hospitalized)
        icuNumberView.text = NumberFormat.getIntegerInstance().format(state.inIcuCurrently)
        deathNumberView.text = NumberFormat.getIntegerInstance().format(state.death)
        lastUpdatedTextView.text = "Last Modified: ${state.dateModified}"
    }

    private suspend fun getFullStateName(): String {
        var fullStateName: String? = null
        val value = GlobalScope.async {
            withContext(Dispatchers.Default){
                when (state.state) {
                    "AL" -> {
                        fullStateName = "Alabama"
                        capitalImage = "https://www.newamericanjournal.net/wp-content/uploads/2017/02/alabama-capitol-building_1.jpg"
                    }
                    "AK" -> {
                        fullStateName = "Alaska"
                        capitalImage = "https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,h_600,q_75,w_1200/v1/clients/juneau/TJJO_Downtown_Juneau_4_2015_868ae45f-1b73-4c52-9c4f-dbea12170952.jpg"
                    }
                    "AZ" -> {
                        fullStateName = "Arizona"
                        capitalImage = "https://res.cloudinary.com/simpleview/image/upload/v1529338942/clients/phoenix/148e5282_6056_4aca_9135_d0d5f25e9a9d_df00ab2b-7f9c-462e-a8a7-903f8757870e.jpg"
                    }
                    "AR" -> {
                        fullStateName = "Arkansas"
                        capitalImage = "https://miro.medium.com/max/700/0*XlsG4rW6QavaL0gz.jpg"
                    }
                    "CA" -> {
                        fullStateName = "California"
                        capitalImage = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/Sacramento_Skyline_%28cropped%29.jpg/900px-Sacramento_Skyline_%28cropped%29.jpg"
                    }
                    "CO" -> {
                        fullStateName = "Colorado"
                        capitalImage = "https://lp-cms-production.imgix.net/2019-06/1da32e09c1497a907cda4479049e6b2b-denver.jpg?fit=crop&q=40&sharp=10&vib=20&auto=format&ixlib=react-8.6.4"
                    }
                    "CT" -> {
                        fullStateName = "Connecticut"
                        capitalImage = "https://www.sisinternational.com/wp-content/uploads/2009/02/Hartford-Connecticut-Market-Research.jpg"
                    }
                    "DE" -> {
                        fullStateName = "Delaware"
                        capitalImage = "https://media-cdn.tripadvisor.com/media/photo-c/2560x500/04/85/cd/74/first-state-heritage.jpg"
                    }
                    "FL" -> {
                        fullStateName = "Florida"
                        capitalImage = "https://media.radissonhotels.net/image/destination-pages/localattraction/16256-118729-f63224528_3xl.jpg?impolicy=HomeHero"
                    }
                    "GA" -> {
                        fullStateName = "Georgia"
                        capitalImage = "https://cdn.mos.cms.futurecdn.net/uByzpurE7CdgELbtvsaAhN.jpg"
                    }
                    "HI" -> {
                        fullStateName = "Hawaii"
                        capitalImage = "https://about.hawaiilife.com/wp-content/uploads/2018/01/Honolulu-Hawaii-624711274_2125x1416-e1514508467409-1.jpeg"
                    }
                    "ID" -> {
                        fullStateName = "Idaho"
                        capitalImage = "https://stadiachurchplanting.org/wp-content/uploads/2019/12/boise-3864184_1920.jpg"
                    }
                    "IL" -> {
                        fullStateName = "Illinois"
                        capitalImage = "https://worldstrides.com/wp-content/uploads/2017/06/Springfield_Illinois.jpg"
                    }
                    "IN" -> {
                        fullStateName = "Indiana"
                        capitalImage = "https://i1.wp.com/wheelchairtravel.org/wp-content/uploads/2020/01/indianapolis-v2020-header.jpg?resize=1920%2C1024&ssl=1"
                    }
                    "IA" -> {
                        fullStateName = "Iowa"
                    }
                    "KS" -> {
                        fullStateName = "Kansas"
                    }
                    "KY" -> {
                        fullStateName = "Kentucky"
                    }
                    "LA" -> {
                        fullStateName = "Louisiana"
                    }
                    "ME" -> {
                        fullStateName = "Maine"
                    }
                    "MD" -> {
                        fullStateName = "Maryland"
                    }
                    "MA" -> {
                        fullStateName = "Massachusetts"
                    }
                    "MI" -> {
                        fullStateName = "Michigan"
                    }
                    "MN" -> {
                        fullStateName = "Minnesota"
                    }
                    "MS" -> {
                        fullStateName = "Mississippi"
                    }
                    "MO" -> {
                        fullStateName = "Missouri"
                    }
                    "MT" -> {
                        fullStateName = "Montana"
                    }
                    "NE" -> {
                        fullStateName = "Nebraska"
                    }
                    "NV" -> {
                        fullStateName = "Nevada"
                    }
                    "NH" -> {
                        fullStateName = "New Hampshire"
                    }
                    "NJ" -> {
                        fullStateName = "New Jersey"
                    }
                    "NM" -> {
                        fullStateName = "New Mexico"
                    }
                    "NY" -> {
                        fullStateName = "New York"
                    }
                    "NC" -> {
                        fullStateName = "North Carolina"
                    }
                    "ND" -> {
                        fullStateName = "North Dakota"
                    }
                    "OH" -> {
                        fullStateName = "Ohio"
                        capitalImage = "https://thumbor.forbes.com/thumbor/711x385/https://blogs-images.forbes.com/adammillsap/files/2018/08/cbus-skyline-pic-1200x651.jpg?width=960"
                    }
                    "OK" -> {
                        fullStateName = "Oklahoma"
                        capitalImage = "https://www.woundprepcourse.com/sites/woundprepcourse.com/files/uploads/2019-WCPC-Hero-OklahomaCity-1600x500%401x.jpg"
                    }
                    "OR" -> {
                        fullStateName = "Oregon"
                    }
                    "PA" -> {
                        fullStateName = "Pennsylvania"
                    }
                    "RI" -> {
                        fullStateName = "Rhode Island"
                    }
                    "SC" -> {
                        fullStateName = "South Carolina"
                    }
                    "SD" -> {
                        fullStateName = "South Dakota"
                    }
                    "TN" -> {
                        fullStateName = "Tennessee"
                    }
                    "TX" -> {
                        fullStateName = "Texas"
                        capitalImage = "https://media.radissonhotels.net/image/destination-pages/localattraction/16256-118729-f63244587_3xl.jpg?impolicy=HomeHero"
                    }
                    "UT" -> {
                        fullStateName = "Utah"
                    }
                    "VT" -> {
                        fullStateName = "Vermont"
                    }
                    "VA" -> {
                        fullStateName = "Virginia"
                    }
                    "WA" -> {
                        fullStateName = "Washington"
                    }
                    "WV" -> {
                        fullStateName = "West Virginia"
                    }
                    "WI" -> {
                        fullStateName = "Wisconsin"
                    }
                    "WY" -> {
                        fullStateName = "Wyoming"
                    }
                    "AS" -> {
                        fullStateName = "American Samoa"
                    }
                    "DC" -> {
                        fullStateName = "District of Columbia"
                    }
                    "FM" -> {
                        fullStateName = "Federated States of Micronesia"
                    }
                    "GU" -> {
                        fullStateName = "Guam"
                    }
                    "MH" -> {
                        fullStateName = "Marshall Islands"
                    }
                    "MP" -> {
                        fullStateName = "Northern Mariana Islands"
                    }
                    "PW" -> {
                        fullStateName = "Palau"
                    }
                    "PR" -> {
                        fullStateName = "Puerto Rico"
                    }
                    "VI" -> {
                        fullStateName = "Virgin Islands"
                    }
                }
            }
        }
        value.await()
        return fullStateName!!
    }
}
