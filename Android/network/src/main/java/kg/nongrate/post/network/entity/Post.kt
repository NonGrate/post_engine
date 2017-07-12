package kg.nongrate.post.network.entity

import com.google.gson.annotations.SerializedName

/**
 * @author Arsenii Lisunov: arsenii.lisunov@showmax.com
 * *
 * @since 5/19/17
 */

data class Post(@SerializedName("id") val id: Long = 0,
                @SerializedName("content") val content: String,
                @SerializedName("rating") val rating: Int = 0,
                @SerializedName("color") val color: Int)
