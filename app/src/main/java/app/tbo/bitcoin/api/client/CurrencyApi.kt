package app.tbo.bitcoin.api.client

import app.tbo.bitcoin.api.transport.CurrencyTO
import app.tbo.bitcoin.api.transport.ExchangeRateTO
import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.data.local.ExchangeRate
import app.tbo.bitcoin.api.mapper.mapToExchangeRate
import app.tbo.bitcoin.api.mapper.toCurrency
import com.google.gson.Gson
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
    private val vsCurrency = "eur"
    private val days = 14
    private val interval = "daily"
    private val precision = 2

    fun getExchangeRates(
        onSuccess: (Currency) -> Unit,
        onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val url = "${BASE_URL}/coins/${id}/market_chart?vs_currency=${vsCurrency}&days=${days}&interval=${interval}&precision=${precision}"
            getFromApi<CurrencyTO>(
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

    private inline fun <reified T> getFromApi(
        url: String,
        crossinline onSuccess: (T) -> Unit,
        crossinline onError: (Exception) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("Accept", "application/json")
            println(connection.responseCode)
            try {
                val reader = InputStreamReader(connection.inputStream)
                reader.use { input ->
                    val response = StringBuilder()
                    val bufferedReader = BufferedReader(input)
                    bufferedReader.forEachLine {
                        response.append(it.trim())
                    }
                    launch(Dispatchers.Main) {
                        onSuccess(parseJson(response.toString()))
                    }
                }
                reader.close()
                connection.disconnect()
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    onError(Exception(e))
                }
            } finally {
                connection.disconnect()
            }
        }
    }

    inline fun <reified T> parseJson(text: String): T {
        return Gson().fromJson(text, T::class.java)
    }
}