package com.example.myapplication

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.nfc.NfcAdapter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

var NFCMediaPlayer: MediaPlayer? = null

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var nfcAdapter: NfcAdapter? = null
    private var textViewInfo: TextView? = null
    private var textViewTagInfo:TextView? = null
    private var textViewBlock:TextView? = null
    private var btn: Button? = null
    private var i = 0
    private var j = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViewInfo = findViewById(R.id.info)
        textViewTagInfo = findViewById(R.id.taginfo)
        textViewBlock = findViewById(R.id.block)

        btn = findViewById(R.id.btnRFID)
        btn!!.setOnClickListener(this)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if (nfcAdapter == null) {
            playNFCSound()
            while (i < 4) {
                Toast.makeText(
                    this,
                    "NFC IS NOT SUPPORTED ON THIS DEVICE!",
                    Toast.LENGTH_LONG
                ).show()
                i++
            }
            stopNFCSound()
//            finish()
        } else if (!nfcAdapter!!.isEnabled) {
            playNFCSound()
            while (j < 4) {
                Toast.makeText(
                    this,
                    "NFC IS SUPPORTED BUT NOT ENABLED!",
                    Toast.LENGTH_LONG
                ).show()
                j++
            }
            stopNFCSound()
//            finish()
        } else {
            Toast.makeText(this,
                "NFC IS SUPPORTED, APP IS RUNNING!",
                Toast.LENGTH_LONG).show()
        }
    }




    override fun onClick(v: View?) {
        btn?.text = "RFID Blocking (ON)"
        // Call function to mask RFID from card? Or we set to a switch...
    }

    private fun playNFCSound() {
        if (NFCMediaPlayer == null) {
            NFCMediaPlayer = MediaPlayer.create(this, R.raw.error)
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