package app.tbo.bitcoin.presentation.composable.current

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tbo.bitcoin.domain.model.ExchangeRate
import app.tbo.bitcoin.domain.model.ExchangeRateElement
import app.tbo.bitcoin.domain.service.ExchangeRateService

/**
 * Composable function to display a header with a dropdown menu for selecting a currency.
 *
 * @param exchangeRate the exchange rate data
 * @param selectedCurrency the currently selected currency
 * @param onSelectedCurrencyChanged the callback for when the selected currency changes
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderDropdownMenu(
    exchangeRate: ExchangeRate,
    selectedCurrency: ExchangeRateElement?,
    onSelectedCurrencyChanged: (ExchangeRateElement?) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    val exchangeRateNames = exchangeRate.rates.values.toList().map { it.name }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Text(
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            text = "Bitcoin",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { expanded = true })
                .weight(1f)
        ) {
            IconText(
                text = selectedCurrency?.name ?: "Euro", modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .menuAnchor(),
                icon = Icons.Rounded.ArrowDropDown
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.requiredHeight(300.dp)
            ) {
                exchangeRateNames.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            expanded = false
                            onSelectedCurrencyChanged(
                                ExchangeRateService().getExchangeRateByName(
                                    exchangeRate,
                                    item
                                )
                            )
                        }
                    )
                }
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "Euro",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}