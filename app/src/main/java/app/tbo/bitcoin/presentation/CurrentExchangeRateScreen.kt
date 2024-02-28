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
import app.tbo.bitcoin.data.local.ExchangeRateUnit
import app.tbo.bitcoin.helper.getKeyByPropertyValue

@Composable
fun CurrentExchangeRateScreen(
    exchangeRate: ExchangeRate,
    selectedCurrency: ExchangeRateUnit,
    onSelectedCurrencyChanged: (ExchangeRateUnit) -> Unit,
    onVsCurrencyChanged: (String) -> Unit) {

    val currentRate = exchangeRate.rates.values.toList().find { it.name == selectedCurrency.name }

    fun selectedCurrencyHandler(current: ExchangeRateUnit) {
        getKeyByPropertyValue(exchangeRate.rates, "name", current.name)?.let {
            onVsCurrencyChanged(it)
        }
    }

    HeaderScreen(
        exchangeRate = exchangeRate,
        selectedCurrency = selectedCurrency,
        onSelectedCurrencyChanged = { element ->
            if (element != null) {
                onSelectedCurrencyChanged(element)
            }
            if (element != null) {
                selectedCurrencyHandler(element)
            }
        })

    currentRate?.let { DualTextWithIcon(rate = it) }
}