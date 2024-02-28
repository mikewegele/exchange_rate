package app.tbo.bitcoin.data.mapper

import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.data.remote.CurrencyTO

fun CurrencyTO.toCurrency(): Currency {
    return Currency(
        prices = prices
    )
}