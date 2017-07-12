package kg.nongrate.post

import android.app.Application
import com.github.salomonbrys.kodein.*
import kg.nongrate.post.config.Config
import kg.nongrate.post.domain.ClosedPostDomain
import kg.nongrate.post.domain.PostDomain
import kg.nongrate.post.domain.SelectionDomain
import kg.nongrate.post.domain.UserDomain
import kg.nongrate.post.network.ClosedPostApi
import kg.nongrate.post.network.PostApi
import kg.nongrate.post.network.SelectionApi
import kg.nongrate.post.network.UserApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Arsenii Lisunov: arsenii.lisunov@showmax.com
 * @since 5/19/17
 *
 */
class PostsApp : Application(), KodeinAware {
    override val kodein by Kodein.lazy {
        /* bindings */
        bind<Retrofit>() with singleton { setupRetrofit() }
        bind<PostDomain>() with singleton { PostDomain(setupPostApi(instance())) }
        bind<ClosedPostDomain>() with singleton { ClosedPostDomain(setupClosedPostApi(instance())) }
        bind<UserDomain>() with singleton { UserDomain(setupUserApi(instance())) }
        bind<SelectionDomain>() with singleton { SelectionDomain(setupSelectionApi(instance())) }
        bind<Config>() with singleton { Config(this@PostsApp) }
    }

    private fun setupRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://sandbox-165618.appspot.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun setupPostApi(retrofit: Retrofit): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    private fun setupClosedPostApi(retrofit: Retrofit): ClosedPostApi {
        return retrofit.create(ClosedPostApi::class.java)
    }

    private fun setupUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    private fun setupSelectionApi(retrofit: Retrofit): SelectionApi {
        return retrofit.create(SelectionApi::class.java)
    }
}