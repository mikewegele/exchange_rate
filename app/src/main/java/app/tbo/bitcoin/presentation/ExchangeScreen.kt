package app.tbo.bitcoin.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.data.local.CurrencyObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ExchangeScreen(
    exchangeRate: Currency,
    modifier: Modifier = Modifier
) {
    val currentDate: LocalDate = LocalDate.now().minusDays(exchangeRate.prices.size.toLong())
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val mapped: List<CurrencyObject> = exchangeRate.prices.flatMapIndexed { day, pricesOfDay ->
        val currentDateMinusDays = currentDate.plusDays(day.toLong())
        val formattedDate = currentDateMinusDays.format(formatter)
        pricesOfDay.drop(1).map { price -> CurrencyObject(formattedDate, price) }
    }.reversed()

    println(mapped)

    return Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = "Wechselkurs",
            fontSize = 30.sp
        )
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