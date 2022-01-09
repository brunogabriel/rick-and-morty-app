package io.github.brunogabriel.rickmorty.shared.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.NonNull

fun Activity.openUrl(@NonNull url: String) = try {
    startActivity(
        Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
    )
} catch (_: Exception) {
    Toast.makeText(this, "Cannot open this URL", Toast.LENGTH_SHORT).show()
}