package kg.nongrate.prayer.network.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Arsenii Lisunov: arsenii.lisunov@showmax.com
 * *
 * @since 5/19/17
 */

data class Selection(@SerializedName("user_id") val userId: Long,
                     @SerializedName("post_id") val postId: Long)
