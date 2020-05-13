package com.iceberg.okcovid.models

/*
[{"positive":931698,
"negative":4252937,
"pending":5315,
"hospitalizedCurrently":56312,
"hospitalizedCumulative":94743,
"inIcuCurrently":15020,
"inIcuCumulative":2516,
"onVentilatorCurrently":5266,
"onVentilatorCumulative":227,
"recovered":90445,
"hash":"88b95c3bf06a7da67491cd0b5e62e81489e348b6",
"lastModified":"2020-04-25T21:12:31.846Z",
"death":47980,"hospitalized":94743,
"total":5189950,
"totalTestResults":5184635,
"posNeg":5184635,
"notes":"NOTE: \"total\",
 \"posNeg\",
  \"hospitalized\" will be removed in the future."}]

 */

data class US(
    val positive: Int,
    val negative: Int,
    val pending: Int,
    val hospitalizedCurrently: Int,
    val inIcuCurrently: Int,
    val onVentilatorCurrently: Int,
    val recovered: Int,
    val hash: String,
    val lastModified: String,
    val death: Int,
    val totalTestResults: Int

)