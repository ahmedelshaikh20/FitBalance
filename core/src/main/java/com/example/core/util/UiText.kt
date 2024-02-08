package com.example.core.util

import android.content.Context

sealed class UiText {

  data class MessageUIText(val text: String) : UiText()
  data class ResourceUiText(val resId: Int) : UiText()


  fun fromString(context: Context): String {
    return when (this) {
      is MessageUIText -> {
        text
      }

      is ResourceUiText -> {
        context.getString(resId)
      }
    }
  }

}
