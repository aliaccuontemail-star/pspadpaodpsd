package ir.pinbom.app.data

import android.content.Context

/** The last few search phrases, kept across launches. Plain prefs: it is a
 * handful of short strings, not worth a table. */
object RecentSearches {
    private const val FILE = "pinbom_recent_searches"
    private const val KEY = "items"
    private const val MAX = 8

    fun load(context: Context): List<String> =
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
            .getString(KEY, "")
            .orEmpty()
            .split('\n')
            .filter { it.isNotBlank() }
            .take(MAX)

    fun save(context: Context, items: List<String>) {
        context.getSharedPreferences(FILE, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY, items.take(MAX).joinToString("\n"))
            .apply()
    }
}
