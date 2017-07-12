package kg.nongrate.prayer.network

import io.reactivex.Single
import kg.nongrate.prayer.network.entity.EntityId
import kg.nongrate.prayer.network.entity.Post
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostApi {
    @GET("/post/all")
    fun all(): Single<List<Post>>

    @GET("/post/{id}")
    fun byId(@Path("id") id: Long): Single<Post>

    @POST("/post/add")
    fun add(@Body post: Post): Single<EntityId>

    @GET("/post/like/{id}")
    fun like(@Path("id") id: Long): Single<Post>

    @GET("/post/close/{id}")
    fun close(@Path("id") id: Long): Single<EntityId>

    @GET("/post/remove/{id}")
    fun remove(@Path("id") id: Long): Single<ResponseBody>
}
