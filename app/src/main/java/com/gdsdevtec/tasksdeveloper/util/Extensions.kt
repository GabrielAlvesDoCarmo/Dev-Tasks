package com.gdsdevtec.tasksdeveloper.util

import android.os.Handler
import android.os.Looper
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gdsdevtec.tasksdeveloper.ui.splash.SplashFragment

fun SplashFragment.splashDelay(action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({ action.invoke() }, 3000)
}

fun Fragment.nextFragment(
    @IdRes action: Int,
) = findNavController().navigate(action)

fun Fragment.initToolbar(toolbar: Toolbar) {
    (activity as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    toolbar.setNavigationOnClickListener { activity?.onBackPressedDispatcher?.onBackPressed() }
}