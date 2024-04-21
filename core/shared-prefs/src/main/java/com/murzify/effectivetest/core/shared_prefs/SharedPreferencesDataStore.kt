package com.murzify.effectivetest.core.shared_prefs

interface SharedPreferencesDataStore {

    suspend fun getPreviousFrom(): String?

    suspend fun setFrom(from: String)

}