package com.example.fitbalance.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.core_ui.Dimensions
import com.example.core_ui.LocalSpacing

private val DarkColorPalette = darkColors(
  primary = BrightGreen,
  primaryVariant = DarkGreen,
  secondary = Orange,
  background = MediumGray,
  onBackground = TextWhite,
  surface = LightGray,
  onSurface = TextWhite,
  onPrimary = Color.White,
  onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
  primary = BrightGreen,
  primaryVariant = DarkGreen,
  secondary = Orange,
  background = Color.White,
  onBackground = DarkGray,
  surface = Color.White,
  onSurface = DarkGray,
  onPrimary = Color.White,
  onSecondary = Color.White,
)

@Composable
fun FitbalanceTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Dynamic color is available on Android 12+
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }
  val view = LocalView.current


  CompositionLocalProvider(
    LocalSpacing provides Dimensions()
  ) {

    MaterialTheme(
      colors = colors,
      typography = Typography,
      content = content
    )
  }
}
