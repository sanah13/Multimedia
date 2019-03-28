package uk.ac.solent.multimedia

import android.app.Service
import android.content.Context
import android.content.Intent
import android.gesture.GestureLibraries.fromFile
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.IBinder
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
    class MainActivity : AppCompatActivity() {
        val mediaFile = File("${Environment.getExternalStorageDirectory().getAbsolutePath()}/Music/3D_Printer_Slow_Edit_for_Android.mp3")
        val player = MediaPlayer()
        override fun onCreate(savedInstanceState: Bundle?) {
            bindService(bindIntent, serviceConn,  Context.BIND_AUTO_CREATE)
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            val startIntent = Intent(this, MusicService::class.java)
            val bindIntent = Intent(this, MusicService::class.java);
            startService(startIntent)
            player.setAudioStreamType(AudioManager.STREAM_MUSIC)
            player.setDataSource(applicationContext, Uri.fromFile(mediaFile))
            player.setOnPreparedListener { mp ->
                mp.start()
            }
            player.prepareAsync()

            btn1.setOnClickListener {
                player.start()
            }
            btn2.setOnClickListener {
                player.pause()
            }
            btn3.setOnClickListener {
                player.seekTo(0)
            }
        }
        override fun onBind(intent: Intent) : IBinder {
            return MusicService.MusicServiceBinder()
    }

