package com.example.tracker_presentation.trackeroverview.components

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.R
import com.example.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientBarInfo(
  value: Int,
  goal: Int,
  name: String,
  color: Color,
  modifier: Modifier = Modifier,
  strokeWidth: Dp = 8.dp
) {
  val backgroundColor = MaterialTheme.colors.background
  val goalExceedColor = MaterialTheme.colors.error
  val angleRatio = remember {
    Animatable(0f)
  }
  LaunchedEffect(key1 = value) {
    Log.d("loool", value.toString())
    Log.d("loool", goal.toString())

    angleRatio.animateTo(
      targetValue = (if (goal > 0) {
          value / (goal.toFloat()/100)
      } else 0f) ,
      animationSpec = tween(300)
    )
    Log.d("loool", angleRatio.value.toString())

  }
  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center
  ) {

    Canvas(
      modifier = Modifier
        .fillMaxSize()
        .aspectRatio(1f)
    ) {
      // The background Arc
      drawArc(
        color = if (value <= goal) backgroundColor else goalExceedColor,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        size = size,
        style = Stroke(
          width = strokeWidth.toPx(),
          cap = StrokeCap.Round
        )
      )
      if (value <= goal) {
        //Animated Arc
        drawArc(
          color = color,
          startAngle = 90f,
          sweepAngle = 360f * angleRatio.value,
          useCenter = false,
          size = size,
          style = Stroke(
            width = strokeWidth.toPx(),
            cap = StrokeCap.Round
          )
        )
      }

    }
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      UnitDisplay(
        amount = value,
        unit = stringResource(id = R.string.grams),
        amountColor = if (value <= goal) {
          MaterialTheme.colors.onPrimary
        } else goalExceedColor,
        unitColor = if (value <= goal) {
          MaterialTheme.colors.onPrimary
        } else goalExceedColor
      )
      Text(
        text = name,
        color = if (value <= goal) {
          MaterialTheme.colors.onPrimary
        } else goalExceedColor,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Light
      )
    }


  }


}


