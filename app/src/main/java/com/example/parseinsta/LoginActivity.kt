package com.example.parstagram


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //check if there's a user logged in
        //if there is, take them to the main activity
        if(ParseUser.getCurrentUser()!=null)
        {
            goToMainActivity()
        }
        //findViewById<Button>(R.id.login_button).setOnClickListener{
          //  val username = findViewById<EditText>(R.id.et_username).text.toString()
            //val password = findViewById<EditText>(R.id.et_password).text.toString()
            //loginUser(username, password)
        //}
        findViewById<Button>(R.id.signupBtn).setOnClickListener{
            val username = findViewById<EditText>(R.id.et_username).text.toString()
            val password = findViewById<EditText>(R.id.et_password).text.toString()
            signUpUser(username, password)
        }

    }

    private fun signUpUser(username: String, password: String)
    {
        val user = ParseUser()
        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // user has successfully created a new account
                goToMainActivity()
                Toast.makeText(this,"user has signed up successfully", Toast.LENGTH_SHORT).show()
                //signed up for an account.
            } else {
                //show a toast
                Toast.makeText(this,"user has failed to sign up", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                goToMainActivity()
                // Hooray!  The user is logged in.
            } else {
                // Signup failed.  Look at the ParseException to see what happened.
                e.printStackTrace()
                Toast.makeText(this,"user login failed", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object{
        const val TAG = "LoginActivity"
    }


}
