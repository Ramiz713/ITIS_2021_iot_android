package com.itis.iot_2021_front.ui.customView

import android.content.Context
import android.view.View
import androidx.core.view.isVisible
import com.itis.iot_2021_front.R
import com.itis.iot_2021_front.databinding.ViewStatusCardBinding
import com.itis.iot_2021_front.ui.main.MainViewState

fun ViewStatusCardBinding.bind(state: MainViewState, context: Context) {
    when (state) {
        MainViewState.Idle -> R.string.status_idle
        MainViewState.Loading -> R.string.status_loading
        is MainViewState.Success -> R.string.status_success
        MainViewState.Error -> R.string.status_error
    }.also { viewStatusCardMessageTv.text = context.getString(it) }

    viewStatusCardPb.isVisible = state is MainViewState.Loading
    viewStatusCardBtn.isVisible = when (state) {
        MainViewState.Idle, MainViewState.Error -> true
        else -> false
    }
}

fun ViewStatusCardBinding.setRefreshButtonClickListener(clickListener: View.OnClickListener) {
    viewStatusCardBtn.setOnClickListener(clickListener)
}