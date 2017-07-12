package kg.nongrate.post.domain

import io.reactivex.Single
import kg.nongrate.post.network.UserApi
import kg.nongrate.post.network.entity.EntityId
import kg.nongrate.post.network.entity.User
import okhttp3.ResponseBody

class UserDomain(userApi: UserApi) {
    val api: UserApi = userApi

    fun all(): Single<List<User>> = api.all()

    fun byId(id: Long): Single<User> = api.byId(id)

    fun add(user: User): Single<EntityId> = api.add(user)

    fun like(id: Long): Single<ResponseBody> = api.like(id)
}