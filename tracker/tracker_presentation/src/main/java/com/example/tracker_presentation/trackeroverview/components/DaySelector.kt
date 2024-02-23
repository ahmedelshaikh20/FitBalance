package com.example.tracker_presentation.trackeroverview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DaySelector(
  onNextClick: () -> Unit,
  onPreviousClick: () -> Unit,
  date: LocalDate,
  modifier: Modifier = Modifier
) {

  Row(
    modifier = modifier.fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(onClick = onPreviousClick) {
      Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = stringResource(id = R.string.previous_day)
      )
    }

    Text(text = parseDate(date = date), style = MaterialTheme.typography.h2)
    IconButton(onClick = onNextClick) {


      Icon(
        imageVector = Icons.Default.ArrowForward,
        contentDescription = stringResource(id = R.string.next_day)
      )
    }


  }

}

@Composable
fun parseDate(date: LocalDate): String {
  var today = LocalDate.now()
  return when (date) {
    today -> {
      stringResource(id = R.string.today)
    }

    today.plusDays(1) -> {
      stringResource(id = R.string.tomorrow)
    }

    today.minusDays(1) -> {
      stringResource(id = R.string.yesterday)
    }

    else -> {
      DateTimeFormatter.ofPattern("dd LLLL").format(date)
    }

  }
}


