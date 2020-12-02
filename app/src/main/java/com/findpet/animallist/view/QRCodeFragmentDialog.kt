package com.findpet.animallist.view

import android.R.attr.bitmap
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.findpet.R
import com.google.zxing.WriterException
import kotlinx.android.synthetic.main.dialog_qrcode.*


class QRCodeDialogFragment(context: Context, val animalId: Int?): Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_qrcode)

        val qrgEncoder =
            QRGEncoder(animalId.toString(), null, QRGContents.Type.TEXT, 1000)
        qrgEncoder.colorBlack = Color.BLACK
        qrgEncoder.colorWhite = Color.WHITE
        try {
            // Getting QR-Code as Bitmap
            val bitmap = qrgEncoder.bitmap
            // Setting Bitmap to ImageView
            image_view.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.v("TAG", e.toString())
        }
    }
}