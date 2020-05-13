package com.iceberg.okcovid.adapters

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.iceberg.okcovid.R
import com.iceberg.okcovid.StateActivity
import com.iceberg.okcovid.models.State
import java.io.Serializable
import java.text.NumberFormat

class StatesListAdapter(private val listOfStates: List<State>): RecyclerView.Adapter<StatesListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val stateName: TextView = itemView.findViewById(R.id.stateName)
        val grade: TextView = itemView.findViewById(R.id.gradeTextView)
        val stateTotal: TextView = itemView.findViewById(R.id.totalNumberView)
        val rootView: ConstraintLayout = itemView.findViewById(R.id.rootView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.state_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfStates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = listOfStates[position]
        holder.stateName.text = state.state
        when (state.dataQualityGrade) {
            "A+" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.a_color))
            "A" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.a_color))
            "A-" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.a_color))
            "B+" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.b_color))
            "B" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.b_color))
            "B-" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.b_color))
            "C+" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.c_color))
            "C" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.c_color))
            "C-" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.c_color))
            "D+" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.d_color))
            "D" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.d_color))
            "D-" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.d_color))
            "F" -> holder.grade.setTextColor(ContextCompat.getColor(holder.grade.context, R.color.f_color))
        }
        holder.grade.text = state.dataQualityGrade
        holder.stateTotal.text = NumberFormat.getIntegerInstance().format(state.positive)
        holder.rootView.setOnClickListener {
            val intent = Intent(holder.rootView.context, StateActivity::class.java)
            intent.apply {
                this.putExtra("state", State(state.state,
                    state.positive,
                    state.negative,
                    state.dataQualityGrade,
                    state.totalTestResults,
                    state.hospitalized,
                    state.recovered,
                    state.inIcuCurrently,
                    state.death,
                    state.dateModified,
                    state.dateChecked,
                    state.hash) as Parcelable)
            }
            startActivity(holder.rootView.context, intent, null)
        }

    }


}