package app.tbo.bitcoin.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.tbo.bitcoin.data.local.CurrencyObject

@Composable
fun ExchangeRateTable(modifier: Modifier = Modifier, mapped: List<CurrencyObject>) {
    LazyColumn {
        items(mapped) { item: CurrencyObject ->
            DualText(firstValue = item.day, secondValue = String.format("%.2f%s", item.price, item.unit))
        }
    }
}