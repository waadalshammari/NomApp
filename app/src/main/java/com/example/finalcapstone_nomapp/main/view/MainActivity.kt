package com.example.finalcapstone_nomapp.main.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
// global variables for binding and nav
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    // global variables for notification

    private val CHANNEL_ID = "channel_id_example_01"
   private val notification = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)
        // for bottom nav menu
        NavigationUI.setupWithNavController(binding.bottomNavigationView,navController)

        createNotificationChannel()
        sendNotification()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
     // notification channel
    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val title = "Notification Title"
            val bodyText = "Notification body"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel : NotificationChannel = NotificationChannel(CHANNEL_ID,title,importance).apply {
                description = bodyText
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

     private fun sendNotification(){
         val intent = Intent(this,FavoriteFragment::class.java).apply {
             flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
         }
         val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
         val builder = NotificationCompat.Builder(this,CHANNEL_ID)
             .setSmallIcon(R.drawable.letscook)
             .setContentTitle("let's cook")
             .setContentText("it's your time to be a chief")
             .setPriority(NotificationCompat.PRIORITY_DEFAULT)
             // Set the intent that will fire when the user taps the notification
             .setContentIntent(pendingIntent)
             .setAutoCancel(true)

          with(NotificationManagerCompat.from(this)){
              notify(notification, builder.build())
          }
     }

    }
