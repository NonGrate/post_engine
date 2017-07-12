package kg.nongrate.post.config

import android.app.Application
import android.content.SharedPreferences

/**
 * @author Arsenii Lisunov: arsenii.lisunov@showmax.com
 * @since 5/26/17
 *
 */
class Config(application: Application) {
    val PREFERENCES_NAME: String = "prayer_preferences"
    val PREFERENCES_ACCOUNT: String = "prayer_preferences_account"
    val PREFERENCES_TOKEN: String = "prayer_preferences_token"

    val ACCOUNT_RESPONSE: Int = 123

    var preferences: SharedPreferences = application.getSharedPreferences(PREFERENCES_NAME, 0)

    fun getToken(): String? {
        val token = preferences.getString(PREFERENCES_TOKEN, null)
        return token
    }

    fun saveToken(token: String) {
        val editor = preferences.edit()
        editor.putString(PREFERENCES_TOKEN, token)
        editor.apply()
    }

    fun getAccount(): String? {
        val account = preferences.getString(PREFERENCES_ACCOUNT, null)
        return account
    }

    fun saveAccount(account: String) {
        val editor = preferences.edit()
        editor.putString(PREFERENCES_ACCOUNT, account)
        editor.apply()
    }
}