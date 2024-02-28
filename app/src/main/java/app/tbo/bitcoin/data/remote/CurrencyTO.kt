package app.tbo.bitcoin.data.remote

import com.google.gson.annotations.SerializedName

data class CurrencyTO(
    @SerializedName("prices")
    var prices: List<List<Double>> = arrayListOf(),
    @SerializedName("market_caps")
    var marketCaps: List<List<Double>> = arrayListOf(),
    @SerializedName("total_volumes")
    var totalVolumes: List<List<Double>> = arrayListOf()
)

