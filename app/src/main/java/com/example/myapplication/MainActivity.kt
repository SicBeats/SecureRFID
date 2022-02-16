package com.example.myapplication

import android.media.AudioManager
import android.media.MediaPlayer
import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View

var NFCMediaPlayer: MediaPlayer? = null

class MainActivity : AppCompatActivity() {

    private var nfcAdapter: NfcAdapter? = null
    private var textViewInfo: TextView? = null
    private var textViewTagInfo:TextView? = null
    private var textViewBlock:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewInfo = findViewById(R.id.info)
        textViewTagInfo = findViewById(R.id.taginfo)
        textViewBlock = findViewById(R.id.block)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            playNFCSound()
            Toast.makeText(this,
                "NFC IS NOT SUPPORTED ON THIS DEVICE!",
                Toast.LENGTH_LONG).show()
            stopNFCSound()
//            finish()
        } else if (!nfcAdapter!!.isEnabled) {
            playNFCSound()
            Toast.makeText(this,
                "NFC IS SUPPORTED BUT NOT ENABLED!",
                Toast.LENGTH_LONG).show()
            stopNFCSound()
//            finish()
        } else {
            Toast.makeText(this,
                "NFC IS SUPPORTED, APP IS RUNNING!",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun playNFCSound() {
        if (NFCMediaPlayer == null) {
            NFCMediaPlayer = MediaPlayer.create(this, R.raw./*name of file*/)
            NFCMediaPlayer!!.isLooping = true
            NFCMediaPlayer!!.start()
        } else {
            NFCMediaPlayer!!.start()
        }
    }

    private fun stopNFCSound() {
        if (NFCMediaPlayer != null) {
            NFCMediaPlayer!!.stop()
            NFCMediaPlayer!!.release()
            NFCMediaPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        if (NFCMediaPlayer != null) {
            NFCMediaPlayer!!.release()
            NFCMediaPlayer = null
        }
    }
}