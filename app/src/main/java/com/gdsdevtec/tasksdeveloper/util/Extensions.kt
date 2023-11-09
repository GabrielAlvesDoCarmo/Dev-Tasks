package com.gdsdevtec.tasksdeveloper.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

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
fun Fragment.messageToast(msg :Int?,duration : Int = Toast.LENGTH_SHORT) =
    msg?.let { Toast.makeText(requireContext(), it,duration).show() }


fun View.show()  {
    this.isVisible = true
}
fun View.hide()  {
    this.isVisible = false
}

fun Fragment.hideKeyboard() {
    val view = activity?.currentFocus
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}