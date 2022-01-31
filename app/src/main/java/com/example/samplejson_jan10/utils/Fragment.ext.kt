package com.example.samplejson_jan10.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

const val TAG = "MainActivity"

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
}