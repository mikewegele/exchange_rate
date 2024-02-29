package app.tbo.bitcoin.domain.model

data class ExchangeRate(
    val rates: Map<String, ExchangeRateElement>
)
