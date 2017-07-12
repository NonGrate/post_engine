package kg.nongrate.prayer

import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kg.nongrate.prayer.network.entity.Post

/**
 * @author Arsenii Lisunov: arsenii.lisunov@showmax.com
 * @since 5/20/17
 *
 */
class PostsAdapter(private val listener: LikeListener): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    var posts: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vh_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(post: Post) {
            with(post) {
                val tvContent = itemView.findViewById(R.id.content) as TextView
                tvContent.text = "$id : $content"
                val tvRating = itemView.findViewById(R.id.rating) as TextView
                tvRating.text = rating.toString()
                val ivLike = itemView.findViewById(R.id.iv_like) as ImageView
                ivLike.setOnClickListener({_ -> listener.click(id)})
                DrawableCompat.setTint(ivLike.drawable, color)
            }
        }
    }
}