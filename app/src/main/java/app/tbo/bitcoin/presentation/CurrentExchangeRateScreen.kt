package app.tbo.bitcoin.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import app.tbo.bitcoin.api.client.CurrencyApi
import app.tbo.bitcoin.data.local.ExchangeRate

@Composable
fun CurrentExchangeRateScreen() {

    val exchangeRate = produceState<ExchangeRate?>(initialValue = null) {
        CurrencyApi().getExchangeRate(
            onSuccess = {
                value = it
            },
            onError = {
                Log.d("ERROR", it.message.toString())
            }
        )
    }
    if (exchangeRate.value == null) {
        return
    }

    var selectedCurrency by remember { mutableStateOf(exchangeRate.value!!.rates["eur"]) }

    val currentRate = exchangeRate.value!!.rates.values.toList().find { it.name == selectedCurrency?.name }

    HeaderScreen(
        exchangeRate = exchangeRate.value!!,
        selectedCurrency = selectedCurrency,
        onSelectedCurrencyChanged = { selectedCurrency = it })
    currentRate?.let { DualTextWithIcon(rate = it) }
}