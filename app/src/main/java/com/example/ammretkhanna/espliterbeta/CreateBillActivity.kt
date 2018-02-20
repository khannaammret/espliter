package com.example.ammretkhanna.espliterbeta

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_bill.*

class CreateBillActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_bill)

        addfbill.setOnClickListener {
            val one = Intent(this@CreateBillActivity, NavigationActivity::class.java)
            startActivity(one)
        }
    }
}
