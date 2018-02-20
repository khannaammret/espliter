package com.example.ammretkhanna.espliterbeta

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        cbact.setOnClickListener {
            val createbill = Intent(this@NavigationActivity,CreateBillActivity ::class.java)
            startActivity(createbill)
        }

        vbbutton.setOnClickListener {
            val createbill = Intent(this@NavigationActivity, CreateBillActivity::class.java)
            startActivity(createbill)
        }

    }


}
