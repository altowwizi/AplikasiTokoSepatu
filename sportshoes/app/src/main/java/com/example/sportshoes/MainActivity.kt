package com.example.sportshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout       : DrawerLayout
    private lateinit var actionBarToggle    : ActionBarDrawerToggle
    private lateinit var navDrawerView      : NavigationView
    private lateinit var bottomNavigation   :BottomNavigationView

    var myAdapter : ProductAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //START PRODUCT ITEM
        val arrayItem = ArrayList<ProductModel>()
        arrayItem.add(ProductModel("Adidas",        "Adidas GRADE ORIGINAL Kualitas Premium",       R.drawable.adidas, 200000))
        arrayItem.add((ProductModel("Brodo",        "Brodo Soft Cotton Profile Tipis",              R.drawable.brodo, 399000)))
        arrayItem.add((ProductModel("Converse",     "Converse Premium Quality 1.1 ",                R.drawable.converse, 149000)))
        arrayItem.add((ProductModel("Diadora",      "Diadora Inovasi Teknis, Performa dan Gaya",    R.drawable.diadora, 279000)))
        arrayItem.add((ProductModel("New Balance",  "New Balance Import for Mens Premium Quality",  R.drawable.newbalance, 165000)))
        arrayItem.add((ProductModel("Nike",         "Nike Sole Unit with Soft, Bouncy Cushioning",  R.drawable.nike, 500000)))
        arrayItem.add((ProductModel("Puma",         "Puma Expert Quality 100% Original",            R.drawable.puma, 850000)))
        arrayItem.add((ProductModel("Reebok",       "Reebok Casual Running Classic Suede",          R.drawable.rebook, 189000)))
        arrayItem.add((ProductModel("Vans",         "Vans Oldskool Import Premium Quality",         R.drawable.vans, 159999)))
        arrayItem.add((ProductModel("Wakai",        "Wakai Slimfit Black Shark",                    R.drawable.wakai, 250000)))

        myAdapter = ProductAdapter(this)
        myAdapter!!.setData(arrayItem)

        //Product_RecyclerView berasal dari id recyclerView pada activity_main.xml
        Product_RecycleView.layoutManager = LinearLayoutManager(this)
        Product_RecycleView.adapter = myAdapter
        //END PRODUCT ITEM

        //start bottom navigation
        bottomNavigation = findViewById(R.id.navBottom)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.history -> {
                    Toast.makeText(this, "Go to History Transaction", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }


        //DRAWER MENU
        drawerLayout = findViewById(R.id.drawer)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)

        drawerLayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        actionBarToggle.syncState()

        navDrawerView = findViewById(R.id.navdrawer)

        navDrawerView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.myProfile -> {
                    val intent = Intent(applicationContext, Profile::class.java)
                    startActivity(intent)
                    true
                }
                R.id.myContact -> {
                    Toast.makeText(this, "Go to Our Contact", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.myHelp -> {
                    Toast.makeText(this, "Go to Our Help", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)){
            this.drawerLayout.closeDrawer(GravityCompat.START)
        }
        else {
            this.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

//toolbar menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.toolbar_menu, menu)
    val searchItem = menu?.findItem(R.id.search)
    if (searchItem != null) {
        val searchView = searchItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(filterString: String?): Boolean {
                myAdapter!!.filter.filter(filterString)
                return true
            }
        })
    }
    return true
}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.shopping){
            Toast.makeText(this, "View Shopping Cart", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (id == R.id.account){
            Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (id == R.id.logout){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            true
        }
        return super.onOptionsItemSelected(item)
    }
}