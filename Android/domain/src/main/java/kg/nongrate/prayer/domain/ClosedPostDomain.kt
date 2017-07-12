package kg.nongrate.prayer.domain

import io.reactivex.Single
import kg.nongrate.prayer.network.ClosedPostApi
import kg.nongrate.prayer.network.entity.EntityId
import kg.nongrate.prayer.network.entity.Post

class ClosedPostDomain(closedPostApi: ClosedPostApi) {
    val api: ClosedPostApi = closedPostApi

    fun all(): Single<List<Post>> = api.all()

    fun byId(id: Long): Single<Post> = api.byId(id)

    fun revert(id: Long): Single<EntityId> = api.revert(id)
}
