package com.example.myapplication

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar

class NFCActivity : AppCompatActivity() {
    private lateinit var mNfcAdapter: NfcAdapter
    private lateinit var mPendingIntent: PendingIntent
    private lateinit var spinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_nfcactivity)

        spinner = findViewById(R.id.rfidProgressBar)

        Log.d("TAG", "New Activity started")

        val launchIntent = Intent(this, this.javaClass)

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        mPendingIntent = PendingIntent.getActivity(this, 0, launchIntent, PendingIntent.FLAG_IMMUTABLE)

        readNfcTag(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        spinner.visibility = View.GONE
        Log.d("TAG", "New intent discovered")

        readNfcTag(intent)
    }

    private fun readNfcTag(intent: Intent?)
    {
        if (intent != null) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
                Log.d("TAG", "New NFC")
                Log.d("TAG", intent.toString())
                intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                    val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                    for (message in messages) {
                        Log.d("TAG", message.toString())
                    }
                }
            }
        }
    }
}

