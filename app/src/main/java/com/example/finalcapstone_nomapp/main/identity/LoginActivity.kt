package com.example.finalcapstone_nomapp.main.identity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.main.view.MainActivity
import com.example.finalcapstone_nomapp.main.view.SHARED_PREF
import com.example.finalcapstone_nomapp.main.view.STATE
import com.example.finalcapstone_nomapp.main.view.USERID
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

 lateinit var sharedPref : SharedPreferences
 lateinit var sharedEditor : SharedPreferences.Editor

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       // hide action bar
        supportActionBar?.hide()


        // get context
        val email : EditText = findViewById(R.id.email_editText_login)
        val password : EditText = findViewById(R.id.password_editText_login)
        val loginButton : Button = findViewById(R.id.login_button)
        val loginTextview : TextView = findViewById(R.id.new_here_textView)


        // make the button clickable
        loginButton.setOnClickListener {

            val emil : String = email.text.toString()
            val password : String = password.text.toString()


            // make suer email&&password not empty
            if (emil.isNotEmpty() && password.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emil,password)
                    .addOnCompleteListener(){
                            task ->
                        if (task.isSuccessful){
                            val firebaseUser : FirebaseUser = task.result!!.user!!
                            Toast.makeText(this,"You Login Successfully", Toast.LENGTH_SHORT)
                                .show()
                            sharedEditor = sharedPref.edit()
                            sharedEditor.putBoolean(STATE,true)
                            sharedEditor.putString(USERID,FirebaseAuth.getInstance().currentUser!!.uid)
                            sharedEditor.commit()

                            // Navigate to main Activity
                            val intent = Intent(this, MainActivity::class.java)
                            intent.putExtra("UserId", firebaseUser.uid)
                            intent.putExtra("Email",firebaseUser.email)
                            startActivity(intent)
                            finish()
                        }
                        else{
                            Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                    // when add on complete fun doesn't complete for any reason >> error handling
                    .addOnFailureListener{
                        println(it.message)
                    }
            }
        }
        // make the text clickable and navigate to sign up activity
        loginTextview.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }
    }
}