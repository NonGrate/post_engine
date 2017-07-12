package kg.nongrate.post.domain

import io.reactivex.Single
import kg.nongrate.post.network.SelectionApi
import kg.nongrate.post.network.entity.EntityId
import kg.nongrate.post.network.entity.Post
import kg.nongrate.post.network.entity.Selection
import okhttp3.ResponseBody

class SelectionDomain(selectionApi: SelectionApi) {
    val api: SelectionApi = selectionApi

    fun add(selection: Selection): Single<EntityId> = api.add(selection)

    fun remove(selection: Selection): Single<ResponseBody> = api.remove(selection)

    fun byUser(id: Long): Single<List<Post>> = api.byUser(id)

    fun byNeed(id: Long): Single<List<Post>> = api.byPost(id)
}