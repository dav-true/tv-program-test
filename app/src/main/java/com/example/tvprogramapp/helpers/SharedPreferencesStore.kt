package com.example.tvprogramapp.helpers

import android.content.Context
import android.content.SharedPreferences

private const val sharePreferencesName: String = "borders_id_info"

class SharedPreferencesStore(
    val context: Context
) {
    var firstBorderId: Int
        set(value) {
            val shared: SharedPreferences = context.getSharedPreferences(
                sharePreferencesName,
                Context.MODE_PRIVATE
            )
            shared.apply {
                edit().apply {
                    putInt("firstBorderId", value)
                    apply()
                }
            }
        }
        get() =
            context.getSharedPreferences(sharePreferencesName, Context.MODE_PRIVATE)
                .getInt("firstBorderId", 0)

    var lastBorderId: Int
        set(value) {
            val shared: SharedPreferences = context.getSharedPreferences(
                sharePreferencesName,
                Context.MODE_PRIVATE
            )
            shared.apply {
                edit().apply {
                    putInt("lastBorderId", value)
                    apply()
                }
            }
        }
        get() =
            context.getSharedPreferences(sharePreferencesName, Context.MODE_PRIVATE)
                .getInt("lastBorderId", 0)
}