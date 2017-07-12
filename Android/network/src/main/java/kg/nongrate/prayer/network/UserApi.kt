package kg.nongrate.prayer.network

import io.reactivex.Single
import kg.nongrate.prayer.network.entity.EntityId
import kg.nongrate.prayer.network.entity.User
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("/user/all")
    fun all(): Single<List<User>>

    @GET("/user/{id}")
    fun byId(@Path("id") id: Long): Single<User>

    @POST("/user/add")
    fun add(@Body user: User): Single<EntityId>

    @GET("/user/remove/{id}")
    fun like(@Path("id") id: Long): Single<ResponseBody>
}