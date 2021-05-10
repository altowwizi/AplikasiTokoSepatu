package com.example.sportshoes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.activity_pembayaran.*
import kotlinx.android.synthetic.main.activity_total_pembayaran.*

class Pembayaran : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayaran)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent

        val aProduct    = intent.getStringExtra("name")
        val aPrice      = intent.getIntExtra("price", 0)
        val aQty        = intent.getIntExtra("qty", 0)
        val aTot        = intent.getIntExtra("tot", 0)

        actionBar.setTitle("Pembayaran " +aProduct)

        nameProduct.text    = aProduct
        totPrice.text       = aPrice.toString()
        qtyTotal .text      = aQty.toString()
        harTot.text         = aTot.toString()

        backPay.setOnClickListener {
            onBackPressed()
        }

        payNow.setOnClickListener {
            val intent = Intent(this, TotalPembayaran::class.java)

            intent.putExtra("nama", enterName.text.toString())
            intent.putExtra("nohp", enterPhone.text.toString())
            intent.putExtra("alamat", enterAddress.text.toString())

            intent.putExtra("name", aProduct)
            intent.putExtra("price", aPrice.toString().toInt())
            intent.putExtra("qty", qtyTotal.text.toString().toInt())
            intent.putExtra("tot", harTot.text.toString().toInt())

            startActivity(intent)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

