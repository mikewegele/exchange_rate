package app.tbo.bitcoin.api.transport

import com.google.gson.annotations.SerializedName

data class ExchangeRateUnitTO(
    @SerializedName("name")
    var name: String,

    @SerializedName("unit")
    var unit: String,

    @SerializedName("value")
    var value: Double,

    @SerializedName("type")
    var type: String
)
