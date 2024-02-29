package app.tbo.bitcoin.domain.service

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ExchangeRateServiceTest : ExchangeRateTestData() {

    private lateinit var exchangeRateService: ExchangeRateService

    @Before
    fun setUp() {
        exchangeRateService = ExchangeRateService()
    }

    @Test
    fun testGetBTCtoCurrency() {
        val result = exchangeRateService.getBTCtoCurrency(usDollar, bitcoinValue)
        assertEquals(bitcoinValues, result)
    }

    @Test
    fun testGetExchangeRateByName() {
        val result = exchangeRateService.getExchangeRateByName(exchangeRate, euroName)
        assertEquals(euro, result)
    }
}