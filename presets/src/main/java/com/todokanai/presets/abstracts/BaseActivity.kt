package com.todokanai.presets.abstracts

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

/** view 의 lifeCycle 대신, 기능 ( 작업 ) 단위로 구분하기 위한 class **/
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = prepareView()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectUIState()
            }
        }
        setContentView(rootView)
    }

    /** @return root view **/
    abstract fun prepareView(): View

    /** update ui **/
    abstract suspend fun collectUIState()

}