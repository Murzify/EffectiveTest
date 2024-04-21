package com.murzify.effectivetest.core.shared_prefs

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesDataStoreImpl @Inject constructor(
    @ApplicationContext private val context: Context
): SharedPreferencesDataStore {
    private val prefs = context.getSharedPreferences(
        context.getString(R.string.prefs_file_key), Context.MODE_PRIVATE
    )

    override suspend fun getPreviousFrom(): String? {
        return prefs.getString(
            context.getString(R.string.from_key),
            ""
        )
    }

    override suspend fun setFrom(from: String) {
        with(prefs.edit()) {
            putString(context.getString(R.string.from_key), from)
            apply()
        }
    }
}