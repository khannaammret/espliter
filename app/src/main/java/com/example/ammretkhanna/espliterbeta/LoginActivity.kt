package com.example.ammretkhanna.espliterbeta

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var progress: ProgressDialog? = null
    private var RC_SIGN_IN = 1
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mAuthStateListener: FirebaseAuth.AuthStateListener? = null
    private val TAG = "Error"


    override fun onCreate(savedInstanceState: Bundle?) {

        progress = ProgressDialog(this@LoginActivity)
        progress!!.setCanceledOnTouchOutside(false)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { Toast.makeText(applicationContext, "Network Error.", Toast.LENGTH_SHORT).show() }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {

                // Calling userDetails function
                progress!!.dismiss()
                val i = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(i)
                finish()
            }

            // Action to for google sign in button
            signinbutton.setOnClickListener {

                // Calling signIn function
                signIn()
            }


        }
    }

    // Function for sign in operations
    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onStart() {
        mAuth!!.addAuthStateListener(mAuthStateListener!!)
        super.onStart()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {

                myProgressDialog("Please Wait","We are loading your profile")
                // Google Sign In was successful, authenticate with Firebase
                val account = result.signInAccount
                firebaseAuthWithGoogle(account!!)

            } else {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful)

                    // If sign in fails, display HomeActivity message to the user. If sign in succeeds
                    // the auth state listener will be notified and logic to handle the
                    // signed in user can be handled in the listener.
                    if (!task.isSuccessful) {
                        Log.w(TAG, "signInWithCredential", task.exception)
                        Toast.makeText(applicationContext, task.exception!!.toString() + "", Toast.LENGTH_SHORT).show()

                    } else {
                    }
                }
    }
    private fun myProgressDialog(title: String, message: String){
        progress!!.setTitle(title)
        progress!!.setMessage(message)
        progress!!.show()
    }
}
