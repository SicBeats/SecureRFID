package com.example.myapplication

import android.annotation.SuppressLint
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.ByteBuffer

class NFCActivity : AppCompatActivity(), NfcAdapter.ReaderCallback {
    private var nfcAdapter: NfcAdapter? = null
    private lateinit var spinner: ProgressBar
    private var nfcFound: TextView? = null
    private var cardInfo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_nfcactivity)

        spinner = findViewById(R.id.rfidProgressBar)
        nfcFound = findViewById(R.id.nfc_found)
        cardInfo = findViewById(R.id.nfc_information)

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
        // This is APDU
        // spinner.visibility = View.GONE
        val isoDep : IsoDep = IsoDep.get(tag)
        isoDep.connect()
        if (isoDep.isConnected)
        {
            spinner.visibility = View.GONE
            nfcFound?.visibility = View.VISIBLE
            Log.d("TAG", "onTagDiscovered prompted")
            // val pse = "1PAY.SYS.DDF01".toByteArray()
            val pse = Utils.hexStringToByteArray("A0000000031010")
            val response = isoDep.transceive(getSelectCommand(pse))
            // Log.d("TAG", "\nCard Response: " + Utils.toHex(response))
            val len = response.size
            val data = ByteArray(len - 2)
            System.arraycopy(response, 0, data, 0, len - 2)
            val str = String(data).trim { it <= ' ' }
            Log.d("TAG", "\nCard Response: $str")
            val msg = "\nCard Response: $str"
            cardInfo?.text = msg
            cardInfo?.visibility = View.VISIBLE
        }
        isoDep.close()
    }

    // Code taken from https://github.com/chinaxstar/KotlinTest/blob/master/app/src/main/java/xstar/com/kotlintest/nfc.kt
    private fun getSelectCommand(aid: ByteArray): ByteArray {
        val cmdOse = ByteBuffer.allocate(aid.size + 6)
        cmdOse.put(0x00.toByte()) // CLA Class
            .put(0xA4.toByte()) // INS Instruction
            .put(0x04.toByte()) // P1 Parameter 1
            .put(0x00.toByte()) // P2 Parameter 2
            .put(aid.size.toByte()) // Lc
            .put(aid).put(0x00.toByte()) // aid + Le
        return cmdOse.array()
    }
}

