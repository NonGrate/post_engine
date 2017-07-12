package kg.nongrate.prayer.domain

import io.reactivex.Single
import kg.nongrate.prayer.network.UserApi
import kg.nongrate.prayer.network.entity.EntityId
import kg.nongrate.prayer.network.entity.User
import okhttp3.ResponseBody

class UserDomain(userApi: UserApi) {
    val api: UserApi = userApi

    fun all(): Single<List<User>> = api.all()

    fun byId(id: Long): Single<User> = api.byId(id)

    fun add(user: User): Single<EntityId> = api.add(user)

    fun like(id: Long): Single<ResponseBody> = api.like(id)
}