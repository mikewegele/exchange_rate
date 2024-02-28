package app.tbo.bitcoin.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tbo.bitcoin.R
import app.tbo.bitcoin.data.local.ExchangeRate
import app.tbo.bitcoin.data.local.ExchangeRateUnit
import app.tbo.bitcoin.domain.CurrencyService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderScreen(exchangeRate: ExchangeRate, selectedCurrency: ExchangeRateUnit?, onSelectedCurrencyChanged: (ExchangeRateUnit?) -> Unit) {

    var expanded by remember { mutableStateOf(false) }

    var exchangeRateNames = exchangeRate.rates.values.toList().map { it.name }


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                text = "Bitcoin",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {expanded = it},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
                    .weight(1f)
            ) {
                IconAndText(text = selectedCurrency?.name ?: "Euro", modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .menuAnchor(),
                    icon = Icons.Rounded.ArrowDropDown
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false},
                    modifier = Modifier.requiredHeight(300.dp)
                ) {
                    exchangeRateNames.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                expanded = false
                                onSelectedCurrencyChanged(CurrencyService().getExchangeRateByName(exchangeRate, item))
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