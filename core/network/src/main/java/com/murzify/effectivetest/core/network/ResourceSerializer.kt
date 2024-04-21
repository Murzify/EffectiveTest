package com.murzify.effectivetest.core.network

import android.content.Context
import androidx.annotation.RawRes
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ResourceSerializer @Inject constructor(
    @ApplicationContext val context: Context
) {

    suspend inline fun <reified T> deserializeResource(@RawRes id: Int): T {
        val text = withContext(Dispatchers.IO) {
            context.resources.openRawResource(id)
                .bufferedReader().use { it.readText() }
        }

        return Json.decodeFromString<T>(text)
    }
}