package app.tbo.bitcoin.presentation.screen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.tbo.bitcoin.api.controller.CurrencyApi
import app.tbo.bitcoin.domain.model.BitcoinValue
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.service.ExchangeRateService

/**
 * Composable function to display the exchange screen, which includes the current exchange rate and a table of Bitcoin exchange rates.
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExchangeScreen() {
    var vsCurrency by remember {
        mutableStateOf("eur")
    }

    val bitcoinValue = produceState<BitcoinValue?>(initialValue = null, key1 = vsCurrency) {
        CurrencyApi().getExchangeRates(
            vsCurrency = vsCurrency,
            onSuccess = {
                value = it
            },
            onError = {
                Log.d("ERROR", it.message.toString())
            }
        )
    }

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

    if (bitcoinValue.value == null || exchangeRate.value == null) {
        return
    }

    var selectedCurrency by remember { mutableStateOf(exchangeRate.value!!.rates["eur"]) }

    if (selectedCurrency == null) {
        return
    }

    val mapped = ExchangeRateService().getBTCtoCurrency(selectedCurrency!!, bitcoinValue.value!!)

    return Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrentExchangeRateScreen(
            exchangeRate = exchangeRate.value!!,
            selectedCurrency = selectedCurrency!!,
            onSelectedCurrencyChanged = { selectedCurrency = it },
            onVsCurrencyChanged = { vsCurrency = it })
        Divider()
        BitcoinRateTableScreen(
            unit = selectedCurrency?.unit,
            mapped = mapped
        )
    }
}