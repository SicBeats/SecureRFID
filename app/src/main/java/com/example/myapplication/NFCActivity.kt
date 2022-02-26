package com.example.myapplication

import android.app.PendingIntent
import android.content.Intent
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NFCActivity : AppCompatActivity() {
    private lateinit var mNfcAdapter: NfcAdapter
    private lateinit var mPendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfcactivity)

        Log.d("TAG", "New Activity started")

        val launchIntent = Intent(this, this.javaClass)

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        mPendingIntent = PendingIntent.getActivity(this, 0, launchIntent, PendingIntent.FLAG_IMMUTABLE)

        readNfcTag(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("TAG", "New intent discovered")

        readNfcTag(intent)
    }

    private fun readNfcTag(intent: Intent?)
    {
        if (intent != null) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action)
            {
                Log.d("TAG", "New NFC")
                Log.d("TAG", intent.toString())
            }
        }
    }
}

