package com.example.ammretkhanna.espliterbeta

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        dspName.text = FirebaseAuth.getInstance().currentUser!!.displayName.toString()


        toone.setOnClickListener {
            val one = Intent(this@HomeActivity, NavigationActivity::class.java)
            startActivity(one)
        }
    }
}
