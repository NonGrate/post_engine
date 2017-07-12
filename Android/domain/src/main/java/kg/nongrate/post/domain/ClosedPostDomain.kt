package kg.nongrate.post.domain

import io.reactivex.Single
import kg.nongrate.post.network.ClosedPostApi
import kg.nongrate.post.network.entity.EntityId
import kg.nongrate.post.network.entity.Post

class ClosedPostDomain(closedPostApi: ClosedPostApi) {
    val api: ClosedPostApi = closedPostApi

    fun all(): Single<List<Post>> = api.all()

    fun byId(id: Long): Single<Post> = api.byId(id)

    fun revert(id: Long): Single<EntityId> = api.revert(id)
}
