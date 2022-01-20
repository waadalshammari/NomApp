package com.example.finalcapstone_nomapp.main.identity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.main.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // hide action bar
        supportActionBar?.hide()


       // get context
        val fullName : EditText = findViewById(R.id.fullnameEditText)
        val email : EditText = findViewById(R.id.email_editText_register)
        val password : EditText = findViewById(R.id.password_editText_register)
        val signupButton : Button = findViewById(R.id.register_button)
        val signupTextview : TextView = findViewById(R.id.already_member_textView)


          // make the button clickable
        signupButton.setOnClickListener {
            val emil : String = email.text.toString()
            val password : String = password.text.toString()
            val fullName :String = fullName.text.toString()


            // make suer email&&password not blank and fullName not empty
            if (emil.isNotBlank() && password.isNotBlank() && fullName.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emil,password)

                    .addOnCompleteListener(){
                            task ->
                        if (task.isSuccessful){
                            val firebaseUser : FirebaseUser = task.result!!.user!!
                            Toast.makeText(this,"You Registered Successfully", Toast.LENGTH_SHORT)
                                .show()
                            // Navigate to main Activity
                            val intent = Intent(this,MainActivity::class.java)
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
            // make the text clickable and navigate to login activity
        signupTextview.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        }


    }
