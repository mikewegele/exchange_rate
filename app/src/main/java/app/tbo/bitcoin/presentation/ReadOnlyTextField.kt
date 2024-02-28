package app.tbo.bitcoin.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle

@Composable
fun ReadonlyTextField(
    value: String,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)?,
    colors: TextFieldColors
) {

    Box {
        TextField(
            value = value,
            textStyle = textStyle,
            onValueChange = {},
            readOnly = true,
            trailingIcon = trailingIcon,
            colors = colors,
            modifier = modifier
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f),
        )
    }
}
