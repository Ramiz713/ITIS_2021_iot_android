package com.itis.iot_2021_front.ui.customView

import android.content.Context
import com.itis.iot_2021_front.R
import com.itis.iot_2021_front.databinding.ViewConcentrationCardBinding
import com.itis.iot_2021_front.ui.main.MainViewState
import java.text.DateFormat
import java.util.*

fun ViewConcentrationCardBinding.bind(state: MainViewState, context: Context) {
    viewConcentrationCardValueTv.text = when (state) {
        is MainViewState.Success -> context
            .getString(R.string.concentration_value, state.concentration)
        else -> context.getString(R.string.concentration_no_value)
    }
    val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())

    viewConcentrationCardTimeValueTv.text = when (state) {
        is MainViewState.Success -> currentDateTimeString
        else -> context.getString(R.string.concentration_no_value)
    }
}