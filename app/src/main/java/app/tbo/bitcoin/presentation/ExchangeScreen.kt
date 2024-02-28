package app.tbo.bitcoin.presentation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tbo.bitcoin.api.client.CurrencyApi
import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.data.local.CurrencyObject
import app.tbo.bitcoin.data.local.ExchangeRate
import app.tbo.bitcoin.domain.CurrencyService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExchangeScreen(
    modifier: Modifier = Modifier
) {
    var vsCurrency by remember {
        mutableStateOf("eur")
    }

    val currency = produceState<Currency?>(initialValue = null, key1 = vsCurrency) {
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
    if (exchangeRate.value == null) {
        return
    }

    var selectedCurrency by remember { mutableStateOf(exchangeRate.value!!.rates["eur"]) }


    if (currency.value == null || selectedCurrency == null) {
        return
    }

    val mapped = CurrencyService().getBTCtoCurrency(selectedCurrency!!, currency.value!!)

    return Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleScreen(text = "Wechselkurs", fontSize = 30)
        CurrentExchangeRateScreen(
            exchangeRate.value!!,
            selectedCurrency!!,
            onSelectedCurrencyChanged = {selectedCurrency = it},
            onVsCurrencyChanged = {vsCurrency = it})
        Divider(
            Modifier.padding(top = 8.dp)
        )
        TitleScreen(text = "BTC / Euro der letzen 14 Tage", fontSize = 26)
        ExchangeRateTable(mapped = mapped)
    }
}