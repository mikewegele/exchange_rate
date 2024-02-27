package app.tbo.bitcoin.data.remote

data class CurrencyTO(
    val name: String,
    val unit: String,
    val value: Double,
    val type: String,
)
