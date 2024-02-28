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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
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
    exchangeRate: ExchangeRate,
    modifier: Modifier = Modifier
) {
    val currency = produceState<Currency?>(initialValue = null) {
        CurrencyApi().getExchangeRates(
            onSuccess = {
                value = it
            },
            onError = {
                Log.d("ERROR", it.message.toString())
            }
        )
    }

    if (currency.value == null) {
        return
    }

    val euroRate = exchangeRate.rates["eur"]
    val mapped = CurrencyService().getBTCtoEuros(currency.value!!)

    return Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        TitleScreen()
        euroRate?.let { HeaderScreen(euroRate = it) }
        euroRate?.let { DualTextWithIcon(euroRate = it) }
        LazyColumn {
            items(mapped) { item: CurrencyObject ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = item.day)
                    Text(text = String.format("%.2f€", item.price))
                }
            }
        }
    }
}