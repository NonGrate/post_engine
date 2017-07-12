package kg.nongrate.post

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance
import com.google.android.gms.common.AccountPicker
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kg.nongrate.post.config.Config
import kg.nongrate.post.domain.PostDomain
import kg.nongrate.post.domain.UserDomain
import kg.nongrate.post.network.entity.Post
import kg.nongrate.post.network.entity.User


class MainActivity : KodeinAppCompatActivity() {
    private val ACCOUNT_REQUEST_CODE: Int = 100

    private val postDomain: PostDomain by instance()
    private val userDomain: UserDomain by instance()
    private val config: Config by instance()
    private val likeListener = object : LikeListener {
        override fun click(id: Long) = likeNeed(id)
    }
    private lateinit var rvNeeds: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener { openAddNeedActivity() }

        progressBar = findViewById(R.id.progressBar) as ProgressBar
        prepareRecyclerView()
    }

    private fun openAddNeedActivity() {
        /* TODO revisit
         * startActivity(intentFor<SearchResultsActivity>())
         * Or even:
         * startActivity<SearchResultsActivity>()
         */
        startActivity(Intent(this, AddNeedActivity::class.java))
    }

    private fun prepareRecyclerView() {
        rvNeeds = findViewById(R.id.rv_posts) as RecyclerView

        val layoutManager = LinearLayoutManager(this)
        rvNeeds.layoutManager = layoutManager

        postsAdapter = PostsAdapter(likeListener)
        rvNeeds.adapter = postsAdapter
    }

    override fun onResume() {
        super.onResume()
        showLoading()
        checkRegistered()
    }

    private fun checkRegistered() {
        val account: String? = config.getAccount()

        if (account == null) {
            val intent = AccountPicker.newChooseAccountIntent(null, null, arrayOf("com.google"), false, null, null,
                    null, null)
            startActivityForResult(intent, ACCOUNT_REQUEST_CODE)
        } else {
            updateApiData()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == ACCOUNT_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
            saveAccount(accountName)
        }
    }

    private fun saveAccount(accountName: String) {
        config.saveAccount(accountName)
        userDomain.add(User(name = accountName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            config.saveToken(it.id.toString())
                            updateApiData()
                        },
                        onError = { showError(it) }
                )
    }

    private fun updateApiData() {
        postDomain.all()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { showData(it) },
                        onError = { showError(it) }
                )
    }

    private fun likeNeed(id: Long) {
        postDomain.like(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            Toast.makeText(this, it.rating.toString(), Toast.LENGTH_SHORT).show()
                            updateApiData()
                        },
                        onError = { showError(it) }
                )
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showData(list: List<Post>) {
        hideLoading()
        postsAdapter.posts = list
        postsAdapter.notifyDataSetChanged()
    }

    private fun showError(it: Throwable) {
        it.printStackTrace()
        val post = Post(content = "Some error occurred", color = 0)
        showData(listOf(post))
        postsAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
