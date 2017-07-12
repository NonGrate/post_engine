package kg.nongrate.post.network

import io.reactivex.Single
import kg.nongrate.post.network.entity.EntityId
import kg.nongrate.post.network.entity.Post
import kg.nongrate.post.network.entity.Selection
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SelectionApi {
    @POST("/selection/add")
    fun add(@Body selection: Selection): Single<EntityId>

    @POST("/selection/remove")
    fun remove(@Body selection: Selection): Single<ResponseBody>

    @GET("/selection/by_user/{id}")
    fun byUser(@Path("id") id: Long): Single<List<Post>>

    @GET("/selection/by_post/{id}")
    fun byPost(@Path("id") id: Long): Single<List<Post>>
}