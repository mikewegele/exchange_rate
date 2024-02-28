package app.tbo.bitcoin.api.transport

import com.google.gson.annotations.SerializedName

data class ExchangeRateTO(
    @SerializedName("rates") val rates: Map<String, ExchangeRateUnitTO>
)

