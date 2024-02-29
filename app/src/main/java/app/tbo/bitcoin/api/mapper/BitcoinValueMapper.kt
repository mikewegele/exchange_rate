package app.tbo.bitcoin.api.mapper

import app.tbo.bitcoin.domain.model.BitcoinValue
import app.tbo.bitcoin.api.transport.BitcoinValueTO

fun BitcoinValueTO.toCurrency(): BitcoinValue {
    return BitcoinValue(
        prices = prices
    )
}