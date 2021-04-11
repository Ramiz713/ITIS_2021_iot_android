package com.itis.iot_2021_front.ui.customView

import android.content.Context
import com.itis.iot_2021_front.R
import com.itis.iot_2021_front.databinding.ViewConcentrationCardBinding
import com.itis.iot_2021_front.ext.colorTransition
import com.itis.iot_2021_front.ui.main.MainViewState
import java.text.DateFormat
import java.util.*

fun ViewConcentrationCardBinding.bind(state: MainViewState, context: Context) {
    viewConcentrationCardValueTv.text = when (state) {
        is MainViewState.Success -> context
            .getString(R.string.concentration_value, state.concentration)
        else -> context.getString(R.string.concentration_no_value)
    }
    viewConcentrationCardTvocValueTv.text = when (state) {
        is MainViewState.Success -> context
            .getString(R.string.concentration_value, state.tvoc)
        else -> context.getString(R.string.concentration_no_value)
    }

    val currentDateTimeString = DateFormat.getDateTimeInstance().format(Date())

    viewConcentrationCardTimeValueTv.text = when (state) {
        is MainViewState.Success -> currentDateTimeString
        else -> context.getString(R.string.concentration_no_value)
    }
    when (state) {
        is MainViewState.Success -> {
            when (state.concentration) {
                in 0..699 -> R.string.concentration_air_high_quality
                in 700..999 -> R.string.concentration_air_medium_quality
                in 1000..1499 -> R.string.concentration_air_high_medium_quality
                in 1500..1999 -> R.string.concentration_air_low_quality
                else -> R.string.concentration_air_lowes_quality
            }
        }
        else -> R.string.concentration_no_value
    }.also { viewConcentrationCardAirValueTv.setText(it) }
}