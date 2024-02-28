package app.tbo.bitcoin.api.mapper

import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.api.transport.CurrencyTO

fun CurrencyTO.toCurrency(): Currency {
    return Currency(
        prices = prices
    )
}