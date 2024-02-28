package app.tbo.bitcoin.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import app.tbo.bitcoin.api.client.CurrencyApi
import app.tbo.bitcoin.data.local.ExchangeRate

@Composable
fun CurrentExchangeRateScreen() {
    val exchangeRate = produceState<ExchangeRate?>(initialValue = null) {
        CurrencyApi().getExchangeEuro(
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
    val euroRate = exchangeRate.value!!.rates["eur"]

    HeaderScreen(exchangeRate = exchangeRate.value!!)
    euroRate?.let { DualTextWithIcon(euroRate = it) }
}