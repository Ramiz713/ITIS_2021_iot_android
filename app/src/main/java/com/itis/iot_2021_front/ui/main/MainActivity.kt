package com.itis.iot_2021_front.ui.main

import android.animation.LayoutTransition
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.itis.iot_2021_front.R
import androidx.lifecycle.lifecycleScope
import com.itis.iot_2021_front.databinding.ActivityMainBinding
import com.itis.iot_2021_front.ext.colorTransition
import com.itis.iot_2021_front.ext.makeStatusBarTransparent
import com.itis.iot_2021_front.ui.customView.bind
import com.itis.iot_2021_front.ui.customView.setRefreshButtonClickListener
import kotlinx.coroutines.flow.collect
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect {
                onViewStateChanged(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.effect.collect {
                onViewEffect(it)
            }
        }
        viewBinding.actStatusCard.setRefreshButtonClickListener {
            viewModel.setEvent(MainEvent.InitEvent)
        }
        viewBinding.actMainLl.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
    }

    private fun onViewStateChanged(state: MainViewState) {
        viewBinding.actStatusCard.bind(state, this@MainActivity)
        viewBinding.actConcentrationCard.bind(state, this@MainActivity)
        when (state) {
            is MainViewState.Success -> {
                when (state.concentration) {
                    in 0..699 -> R.color.col_light_green
                    in 700..999 -> R.color.col_green
                    in 1000..1499 -> R.color.col_yellow
                    in 1500..1999 -> R.color.col_orange
                    else -> R.color.col_red
                }
            }
            else -> R.color.col_gray
        }.also { viewBinding.actMainLl.colorTransition(it) }
    }

    private fun onViewEffect(effect: MainEffect) {
        //TODO обработка эффектов
    }
}