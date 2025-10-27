package com.ferreteriamapocho.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserPrefs {
    private val KEY_NAME = stringPreferencesKey("name")
    private val KEY_LASTNAME = stringPreferencesKey("lastname")
    private val KEY_EMAIL = stringPreferencesKey("email")
    private val KEY_PHONE = stringPreferencesKey("phone")
    private val KEY_ADDRESS = stringPreferencesKey("address")
    private val KEY_PASSWORD = stringPreferencesKey("password")

    fun profileFlow(context: Context): Flow<UserProfile> =
        context.dataStore.data.map { p ->
            UserProfile(
                name = p[KEY_NAME].orEmpty(),
                lastName = p[KEY_LASTNAME].orEmpty(),
                email = p[KEY_EMAIL].orEmpty(),
                phone = p[KEY_PHONE].orEmpty(),
                address = p[KEY_ADDRESS].orEmpty(),
                password = p[KEY_PASSWORD].orEmpty()
            )
        }

    suspend fun save(context: Context, profile: UserProfile) {
        context.dataStore.edit { p ->
            p[KEY_NAME] = profile.name
            p[KEY_LASTNAME] = profile.lastName
            p[KEY_EMAIL] = profile.email
            p[KEY_PHONE] = profile.phone
            p[KEY_ADDRESS] = profile.address
            p[KEY_PASSWORD] = profile.password
        }
    }

    suspend fun clear(context: Context) {
        context.dataStore.edit { it.clear() }
    }
}
