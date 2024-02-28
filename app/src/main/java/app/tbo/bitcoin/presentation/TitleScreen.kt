package app.tbo.bitcoin.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TitleScreen(modifier: Modifier = Modifier) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Wechselkurs",
        fontSize = 30.sp,
    )
}