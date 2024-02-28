package app.tbo.bitcoin.api.mapper

import app.tbo.bitcoin.data.local.ExchangeRate
import app.tbo.bitcoin.data.local.ExchangeRateUnit
import app.tbo.bitcoin.api.transport.ExchangeRateTO
import app.tbo.bitcoin.api.transport.ExchangeRateUnitTO

fun ExchangeRateTO.mapToExchangeRate(): ExchangeRate {
    val ratesMap = rates.mapValues { it.value.toExchangeRateUnit() }
    return ExchangeRate(ratesMap)
}

private fun ExchangeRateUnitTO.toExchangeRateUnit(): ExchangeRateUnit {
    return ExchangeRateUnit(name, unit, value, type)
}
