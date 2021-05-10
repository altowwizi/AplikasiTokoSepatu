package com.example.sportshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_pembayaran.*
import kotlinx.android.synthetic.main.activity_total_pembayaran.*

class TotalPembayaran : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_pembayaran)

        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent

        val aNama           = intent.getStringExtra("nama")
        val aNohp           = intent.getStringExtra("nohp")
        val aAlamat         = intent.getStringExtra("alamat")

        val aProduct        = intent.getStringExtra("name")
        val aPrice          = intent.getIntExtra("price", 0)
        val aQty            = intent.getIntExtra("qty",0)
        val aTot            = intent.getIntExtra("tot",0)

        actionBar.setTitle("Struct " +aProduct)

        pemName.text            = aNama
        pemNo.text              = aNohp.toString()
        pemAlamat.text          = aAlamat
        namapemProduct.text     = aProduct
        hargapemnorProduct.text = aPrice.toString()
        qtytotpemProduct.text   = aQty.toString()
        nilaitotpemProduct.text = aTot.toString()

        backPayNow.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            true
        }

        cetak.setOnClickListener{
            Toast.makeText(this, "Cetak Struk", Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}