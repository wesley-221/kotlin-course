package com.example.task2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.task2.adapters.LinkAdapter
import com.example.task2.models.Link
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val CREATE_NEW_LINK = 100

class MainActivity : AppCompatActivity() {
    private val links = arrayListOf<Link>()
    private val linkAdapter = LinkAdapter(links, onClickListener = this::clickLink)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startCreateNewLink()
        }

        initViews()
    }

    private fun initViews() {
        rvItems.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        rvItems.adapter = linkAdapter

        // Dummy data for easy testing if the browser works
        links.add(Link("https://www.google.com", "Google"))
        links.add(Link("https://www.twitter.com", "Twitter"))

        linkAdapter.notifyDataSetChanged()
    }

    private fun startCreateNewLink() {
        val intent = Intent(this, CreateLink::class.java)
        startActivityForResult(intent, CREATE_NEW_LINK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CREATE_NEW_LINK -> {
                    val link = data!!.getParcelableExtra<Link>(NEW_LINK)

                    links.add(link)
                    linkAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun clickLink(view: View, link: Link) {
        val builder = CustomTabsIntent.Builder()

        builder.setToolbarColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
        builder.addDefaultShareMenuItem()

        val customTabsIntent = builder.build()

        customTabsIntent.launchUrl(this, Uri.parse(link.link))
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
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
