package com.iceberg.okcovid.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class State(val state: String,
                 val positive: Int,
                 val negative: Int,
                 val dataQualityGrade: String,
                 val totalTestResults: Int,
                 val hospitalized: Int,
                 val recovered: Int,
                 val inIcuCurrently: Int,
                 val death: Int,
                 val dateModified: String,
                 val dateChecked: String,
                 val hash: String
): Parcelable, Serializable