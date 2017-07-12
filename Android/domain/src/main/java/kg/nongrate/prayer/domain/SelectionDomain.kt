package kg.nongrate.prayer.domain

import io.reactivex.Single
import kg.nongrate.prayer.network.SelectionApi
import kg.nongrate.prayer.network.entity.EntityId
import kg.nongrate.prayer.network.entity.Post
import kg.nongrate.prayer.network.entity.Selection
import okhttp3.ResponseBody

class SelectionDomain(selectionApi: SelectionApi) {
    val api: SelectionApi = selectionApi

    fun add(selection: Selection): Single<EntityId> = api.add(selection)

    fun remove(selection: Selection): Single<ResponseBody> = api.remove(selection)

    fun byUser(id: Long): Single<List<Post>> = api.byUser(id)

    fun byNeed(id: Long): Single<List<Post>> = api.byPost(id)
}