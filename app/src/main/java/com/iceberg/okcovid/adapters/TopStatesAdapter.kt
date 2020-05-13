package com.iceberg.okcovid.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.iceberg.okcovid.R
import com.iceberg.okcovid.StateActivity
import com.iceberg.okcovid.models.State
import java.io.Serializable
import java.text.NumberFormat

class TopStatesAdapter(private val listOfStates: List<State>): RecyclerView.Adapter<TopStatesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val state: TextView = itemView.findViewById(R.id.stateName)
        val grade: TextView = itemView.findViewById(R.id.gradeTextView)
        val positiveNumberView: TextView = itemView.findViewById(R.id.positiveTextView)
        val negativeNumberView: TextView = itemView.findViewById(R.id.negativeTextView)
        val hospitalNumberView: TextView = itemView.findViewById(R.id.hospitalTextView)
        val icuNumberView:TextView = itemView.findViewById(R.id.icuTextView)
        val card: CardView = itemView.findViewById(R.id.stateCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.top_states_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfStates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val state = listOfStates[position]
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
        holder.state.text = state.state
        holder.grade.text = state.dataQualityGrade
        holder.positiveNumberView.text = "Positive: ${NumberFormat.getIntegerInstance().format(state.positive)}"
        holder.negativeNumberView.text = "Negative: ${NumberFormat.getIntegerInstance().format(state.negative)}"
        holder.hospitalNumberView.text = "Hospitalized: ${NumberFormat.getIntegerInstance().format(state.hospitalized)}"
        holder.icuNumberView.text = "ICU: ${NumberFormat.getIntegerInstance().format(state.inIcuCurrently)}"
        holder.card.setOnClickListener {
            val intent = Intent(holder.card.context, StateActivity::class.java)
            intent.putExtra("state", State(state.state,
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
            startActivity(holder.card.context, intent, null)
        }
    }
}