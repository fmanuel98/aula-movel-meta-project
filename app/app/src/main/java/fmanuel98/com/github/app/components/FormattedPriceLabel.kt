package fmanuel98.com.github.app.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import fmanuel98.com.github.R


@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.subtotal_price, subtotal),
        modifier = modifier
    )
}