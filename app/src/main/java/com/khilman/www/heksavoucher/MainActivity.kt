package com.khilman.www.heksavoucher

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.khilman.www.heksavoucher.adapter.VoucherAdapter
import com.khilman.www.heksavoucher.database.DatabaseHelper
import com.khilman.www.heksavoucher.model.PolisModel
import com.khilman.www.heksavoucher.model.VoucherModel
import com.khilman.www.heksavoucher.view.TambahPolisActivity
import com.khilman.www.heksavoucher.view.TambahVoucherActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var database : DatabaseHelper ? = DatabaseHelper(this)
    var listData : List<PolisModel> ? = null
    var dataVoucher: List<VoucherModel> ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // inisilisasi database
        database = DatabaseHelper(this)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            startActivity(Intent(this, TambahVoucherActivity::class.java))
        }
        // tampilkan data
        dataVoucher = database?.getDataVoucher()

        Log.d("Data", "" + dataVoucher?.size)
        //listData = database?.dataVoucher()

        var layoutManager = LinearLayoutManager(applicationContext)

        listVoucher.layoutManager = layoutManager as RecyclerView.LayoutManager?

        var adapter = VoucherAdapter(this, dataVoucher)

        listVoucher.adapter = adapter
        Log.d("data", "" + listData?.toString())

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        //

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_view_voucher -> {
                // Handle the camera action
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_polis -> {
                startActivity(Intent(this, TambahPolisActivity::class.java))

            }
            R.id.nav_voucher -> {
                startActivity(Intent(this, TambahVoucherActivity::class.java))
            }
            R.id.nav_about -> {

            }
            R.id.nav_exit -> {
                confirmExit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun confirmExit() {
        alert("Exit Application ?") {
            yesButton {
                System.exit(0)
            }
            noButton {

            }
        }.show()
    }
}
