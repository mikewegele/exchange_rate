package app.tbo.bitcoin.domain.model

data class ExchangeRateElement(
    var name: String,
    var unit: String,
    var value: Double,
    var type: String
)
