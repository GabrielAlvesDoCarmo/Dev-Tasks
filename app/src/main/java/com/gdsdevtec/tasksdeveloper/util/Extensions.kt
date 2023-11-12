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
import com.gdsdevtec.tasksdeveloper.R
import com.gdsdevtec.tasksdeveloper.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.nio.ByteBuffer
import java.security.MessageDigest
import java.util.Base64
import java.util.UUID

fun Fragment.nextFragment(
    @IdRes action: Int,
) = findNavController().navigate(action)

fun Fragment.initToolbar(toolbar: Toolbar) {
    (activity as AppCompatActivity).apply {
        setSupportActionBar(toolbar)
        title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    toolbar.setNavigationOnClickListener { popScreen() }
}

fun Fragment.popScreen() = activity?.onBackPressedDispatcher?.onBackPressed()

fun Fragment.messageToast(msg: Int?, duration: Int = Toast.LENGTH_SHORT) =
    msg?.let { Toast.makeText(requireContext(), it, duration).show() }

fun Fragment.messageToast(msg: String?, duration: Int = Toast.LENGTH_SHORT) =
    msg?.let { Toast.makeText(requireContext(), it, duration).show() }


fun View.show() {
    this.isVisible = true
}

fun View.hide() {
    this.isVisible = false
}

fun Fragment.hideKeyboard() {
    val view = activity?.currentFocus
    if (view != null) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: Int,
    onClick: () -> Unit = {},
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val binding = BottomSheetBinding.inflate(layoutInflater, null, false).apply {
        txtTitle.text = getText(titleDialog ?: R.string.text_title_warning)
        txtMessage.setText(message)
        btnOK.text = getText(titleButton ?: R.string.text_button_warning)
        btnOK.setOnClickListener {
            onClick()
            bottomSheetDialog.dismiss()
        }
    }
    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()
}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: String,
    onClick: () -> Unit = {},
) {
    val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
    val binding = BottomSheetBinding.inflate(layoutInflater, null, false).apply {
        txtTitle.text = getText(titleDialog ?: R.string.text_title_warning)
        txtMessage.text = message
        btnOK.text = getText(titleButton ?: R.string.text_button_warning)
        btnOK.setOnClickListener {
            onClick()
            bottomSheetDialog.dismiss()
        }
    }
    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()
}


fun String.generateIdUser(): String {
    val hashedBytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
    return UUID(
        ByteBuffer.wrap(hashedBytes.sliceArray(0 until 8)).long,
        ByteBuffer.wrap(hashedBytes.sliceArray(8 until 16)).long
    ).toString()
}

fun String.transformUUID(): UUID {
    val hashedBytes = MessageDigest.getInstance("SHA-256").digest(this.toByteArray())
    return UUID(
        ByteBuffer.wrap(hashedBytes.sliceArray(0 until 8)).long,
        ByteBuffer.wrap(hashedBytes.sliceArray(8 until 16)).long
    )
}

fun UUID.cryptPassword(): String {
    val buffer = ByteBuffer.wrap(ByteArray(16))
    buffer.putLong(this.mostSignificantBits)
    buffer.putLong(this.leastSignificantBits)

    val encodedBytes = Base64.getEncoder().encode(buffer.array())
    return String(encodedBytes)
}

fun String.decryptPassword(): UUID {
    val decodedBytes = Base64.getDecoder().decode(this)
    val buffer = ByteBuffer.wrap(decodedBytes)

    val mostSignificantBits = buffer.long
    val leastSignificantBits = buffer.long

    return UUID(mostSignificantBits, leastSignificantBits)
}