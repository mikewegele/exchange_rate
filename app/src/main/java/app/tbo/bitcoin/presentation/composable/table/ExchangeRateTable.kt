package app.tbo.bitcoin.presentation.composable.table

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.tbo.bitcoin.domain.model.BitcoinValueElement
import app.tbo.bitcoin.presentation.composable.current.TextRow

@Composable
fun ExchangeRateTable(modifier: Modifier = Modifier, mapped: List<BitcoinValueElement>) {
    LazyColumn {
        items(mapped) { item: BitcoinValueElement ->
            TextRow(
                firstValue = item.day,
                secondValue = String.format("%.2f%s", item.price, item.unit)
            )
        }
    }
}