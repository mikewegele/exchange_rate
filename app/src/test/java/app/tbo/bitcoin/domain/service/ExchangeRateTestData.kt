package app.tbo.bitcoin.domain.service

import app.tbo.bitcoin.domain.model.BitcoinValue
import app.tbo.bitcoin.domain.model.BitcoinValueElement
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class ExchangeRateTestData {

    private val lowPrice = 52000.0
    private val highPrice = 53000.0

    // dates
    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    // first day of table = yesterday
    private val firstDay = LocalDate.now().minusDays(1).format(formatter)

    // secondDayOfTable = day before
    private val secondDay = LocalDate.now().minusDays(2).format(formatter)

    // US-Dollar
    private val usdName = "USD"
    private val usdUnit = "Dollar"

    // Euro
    val euroName = "Euro"
    private val euroUnit = "€"

    val usDollar = ExchangeRateElement(usdName, usdUnit, 50000.0, "fiat")
    val euro = ExchangeRateElement(euroName, euroUnit, 50000.0, "fiat")

    val bitcoinValue = BitcoinValue(
        prices = listOf(
            listOf(10000.0, 52000.0),
            listOf(11000.0, 53000.0),
        )
    )

    // bitcoin value elements
    private val firstBitcoinValueElement = BitcoinValueElement(
        day = firstDay,
        price = highPrice,
        unit = usdUnit
    )
    private val secondBitcoinValueElement = BitcoinValueElement(
        day = secondDay,
        price = lowPrice,
        unit = usdUnit
    )

    val bitcoinValues: List<BitcoinValueElement> = listOf(firstBitcoinValueElement, secondBitcoinValueElement)

    val exchangeRate: ExchangeRate = ExchangeRate(rates = mapOf(
        "eur" to euro,
        "usd" to usDollar
    ))


}