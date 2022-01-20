package com.example.finalcapstone_nomapp.main.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.constraintlayout.motion.widget.MotionLayout
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.ActivitySplashBinding
import com.example.finalcapstone_nomapp.main.identity.LoginActivity
import com.example.finalcapstone_nomapp.main.identity.sharedPref
import com.example.finalcapstone_nomapp.repository.ApiRepository
import com.example.finalcapstone_nomapp.repository.FavoriteApiRepository

const val SHARED_PREF = "login"
const val USERID = "userId"
const val STATE = "state"

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        ApiRepository.init(this)
        FavoriteApiRepository.init(this)

        // for hiding action bar on the splash screen
        supportActionBar?.hide()

       //  hiding statusBar have a full splash screen
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.statusBars())

        }else{
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set motion for splash

        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {


            }


            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

                sharedPref = getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)
                // when user login successfully keep the user login then navigate to
                //main Activity >> recipes Fragment
                if (sharedPref.getBoolean("state", false)) {

                    val intent = Intent(this@SplashActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else{
                 // user does not login or register then navigate to FirstActivity
                    val intent = Intent(this@SplashActivity, FirstActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })
    }
}