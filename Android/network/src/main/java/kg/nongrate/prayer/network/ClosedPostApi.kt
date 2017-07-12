package kg.nongrate.prayer.network

import io.reactivex.Single
import kg.nongrate.prayer.network.entity.EntityId
import kg.nongrate.prayer.network.entity.Post
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClosedPostApi {
    @GET("/post/closed/all")
    fun all(): Single<List<Post>>

    @GET("/post/closed/{id}")
    fun byId(@Path("id") id: Long): Single<Post>

    @POST("/post/closed/revert/{id}")
    fun revert(@Body id: Long): Single<EntityId>
}
