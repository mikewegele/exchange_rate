package app.tbo.bitcoin.data.local

import com.google.gson.annotations.SerializedName

data class ExchangeRateUnit(
    var name: String,
    var unit: String,
    var value: Double,
    var type: String
)
