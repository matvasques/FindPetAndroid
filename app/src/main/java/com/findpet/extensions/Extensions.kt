package com.findpet.extensions

import android.content.Context
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

fun Context.showToast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun TextInputLayout.showTextInputLayoutError(errorMessage: String? = null) = this.run {
    error = errorMessage
}
