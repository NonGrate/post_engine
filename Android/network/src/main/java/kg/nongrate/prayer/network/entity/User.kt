package kg.nongrate.prayer.network.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Arsenii Lisunov: arsenii.lisunov@showmax.com
 * *
 * @since 5/19/17
 */

data class User(@SerializedName("id") val id: Long = 0,
                @SerializedName("name") val name: String)
