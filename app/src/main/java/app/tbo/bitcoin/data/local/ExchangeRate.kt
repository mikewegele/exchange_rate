package app.tbo.bitcoin.data.local

data class ExchangeRate(
    val rates: Map<String, ExchangeRateUnit>
)
