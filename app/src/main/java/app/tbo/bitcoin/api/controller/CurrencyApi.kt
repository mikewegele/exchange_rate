package app.tbo.bitcoin.api.controller

import app.tbo.bitcoin.api.transport.BitcoinValueTO
import app.tbo.bitcoin.api.transport.ExchangeRateTO
import app.tbo.bitcoin.domain.model.BitcoinValue
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.api.mapper.mapToExchangeRate
import app.tbo.bitcoin.api.mapper.toCurrency
import app.tbo.bitcoin.util.json.fromJson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class CurrencyApi {

    private val BASE_URL = "https://api.coingecko.com/api/v3"
    private val id = "bitcoin"
    private val days = 14
    private val interval = "daily"
    private val precision = 2

    fun getExchangeRates(
        vsCurrency: String = "eur",
        onSuccess: (BitcoinValue) -> Unit,
        onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = "${BASE_URL}/coins/${id}/market_chart?vs_currency=${vsCurrency}&days=${days}&interval=${interval}&precision=${precision}"
            getFromApi<BitcoinValueTO>(
                url = url,
                onSuccess = {
                    onSuccess(it.toCurrency())
                },
                onError = onError
            )
        }
    }

    suspend fun getExchangeRate(
        onSuccess: (ExchangeRate) -> Unit,
        onError: (Exception) -> Unit
    ) {
        while (true) {
            CoroutineScope(Dispatchers.IO).launch {
                val url = "${BASE_URL}/exchange_rates"
                getFromApi<ExchangeRateTO>(
                    url = url,
                    onSuccess = {
                        println(it.rates["eur"])
                        onSuccess(it.mapToExchangeRate())
                    },
                    onError = onError
                )
            }
            delay(60000);
        }
    }

    /* ----------------------------- private helper methods ----------------------------- */

    private inline fun <reified T> getFromApi(
        url: String,
        crossinline onSuccess: (T) -> Unit,
        crossinline onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val connection = createConnection(url)
                val response = readResponse(connection)
                launch(Dispatchers.Main) {
                    fromJson<T>(response, T::class.java)?.let { onSuccess(it) }
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    onError(Exception(e))
                }
            }
        }
    }

    private fun createConnection(url: String): HttpURLConnection {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")
        return connection
    }

    private fun readResponse(connection: HttpURLConnection): String {
        val reader = InputStreamReader(connection.inputStream)
        reader.use { input ->
            val response = StringBuilder()
            val bufferedReader = BufferedReader(input)
            bufferedReader.forEachLine {
                response.append(it.trim())
            }
            return response.toString()
        }
    }
}