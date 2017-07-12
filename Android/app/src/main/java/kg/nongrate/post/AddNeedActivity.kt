package kg.nongrate.post

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kg.nongrate.post.domain.PostDomain
import kg.nongrate.post.network.entity.Post
import petrov.kristiyan.colorpicker.ColorPicker

class AddNeedActivity : KodeinAppCompatActivity() {
    val postDomain: PostDomain by instance()
    var color = 0
    lateinit var colorPicker: ColorPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val addNeed = findViewById(R.id.bt_add_post)
        val chooseColor = findViewById(R.id.bt_choose_color)

        addNeed.setOnClickListener({ sendNeed() })
        chooseColor.setOnClickListener({ prepareColorPicker() })
    }

    private fun prepareColorPicker() {
        colorPicker = ColorPicker(this)
        colorPicker.setOnChooseColorListener(object : ColorPicker.OnChooseColorListener {
            override fun onChooseColor(position: Int, color: Int) {
                this@AddNeedActivity.color = color
            }

            override fun onCancel() {
                // do nothing
            }
        })
        colorPicker.show()
    }

    private fun sendNeed() {
        val content = findViewById(R.id.tv_content) as TextView
        postDomain.add(Post(content = content.text.toString(), color = color))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {
                            println(it.id)
                            Toast.makeText(this, "Post has been added", Toast.LENGTH_SHORT).show()
                        },
                        onError = {
                            it.printStackTrace()
                            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show()
                        }
                )
    }
}
