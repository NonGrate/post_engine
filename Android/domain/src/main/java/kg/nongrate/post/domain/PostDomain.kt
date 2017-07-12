package kg.nongrate.post.domain

import io.reactivex.Single
import kg.nongrate.post.network.PostApi
import kg.nongrate.post.network.entity.EntityId
import kg.nongrate.post.network.entity.Post
import okhttp3.ResponseBody

/**
 * @author Arsenii Lisunov: arsenii.lisunov@showmax.com
 * @since 5/27/17
 *
 */
class PostDomain(postApi: PostApi) {
    val api: PostApi = postApi

    fun all(): Single<List<Post>> = api.all()

    fun byId(id: Long): Single<Post> = api.byId(id)

    fun add(post: Post): Single<EntityId> = api.add(post)

    fun like(id: Long): Single<Post> = api.like(id)

    fun close(id: Long): Single<EntityId> = api.close(id)

    fun remove(id: Long): Single<ResponseBody> = api.remove(id)
}