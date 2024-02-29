package app.tbo.bitcoin.api.mapper

import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import app.tbo.bitcoin.api.transport.ExchangeRateTO
import app.tbo.bitcoin.api.transport.ExchangeRateElementTO

fun ExchangeRateTO.mapToExchangeRate(): ExchangeRate {
    val ratesMap = rates.mapValues { it.value.toExchangeRateUnit() }
    return ExchangeRate(ratesMap)
}

private fun ExchangeRateElementTO.toExchangeRateUnit(): ExchangeRateElement {
    return ExchangeRateElement(name, unit, value, type)
}
