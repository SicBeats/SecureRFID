package com.example.myapplication

import android.nfc.NfcAdapter
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

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
            Toast.makeText(this,
                "NFC IS NOT SUPPORTED ON THIS DEVICE!",
                Toast.LENGTH_LONG).show()
            finish()
        } else if (!nfcAdapter!!.isEnabled) {
            Toast.makeText(this,
                "NFC IS SUPPORTED BUT NOT ENABLED!",
                Toast.LENGTH_LONG).show()
            finish()
        }
    }
}