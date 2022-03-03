package com.example.myapplication

import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_main.*

class NFCActivity : AppCompatActivity(), NfcAdapter.ReaderCallback {
    private var nfcAdapter: NfcAdapter? = null
    private lateinit var spinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_nfcactivity)

        spinner = findViewById(R.id.rfidProgressBar)

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
    }

    override fun onResume()
    {
        super.onResume()
        nfcAdapter?.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A or NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK, null)
    }

    override fun onPause()
    {
        super.onPause()
        nfcAdapter?.disableReaderMode(this)
    }

    override fun onTagDiscovered(tag: Tag?) {
        // spinner.visibility = View.GONE
        val isoDep = IsoDep.get(tag)
        isoDep.connect()
        Log.d("TAG", "onTagDiscovered prompted")
        val response = isoDep.transceive(Utils.hexStringToByteArray(
            "A000000003101001"))

        Log.d("TAG", "\nCard Response: " + Utils.toHex(response))
        isoDep.close()
    }
}

