package com.ossmurfy.bravo4fun

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.BarcodeFormat
import com.ossmurfy.bravo4fun.databinding.ActivityQrCodeBinding

class QrCodeActivity : AppCompatActivity() {
    lateinit var binding: ActivityQrCodeBinding
    lateinit var leitorQr: CodeScanner
    var permissaoConcedida = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        verificarPermissaoCamera()
    }

    private fun verificarPermissaoCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        } else {
            permissaoConcedida = true
            inicializarLeitorQrCode()
        }
    }

    /** Inicializa o leitor de QRCode **/
    private fun inicializarLeitorQrCode() {
        leitorQr = CodeScanner(this, binding.scannerView)
        leitorQr.camera = CodeScanner.CAMERA_BACK
        leitorQr.formats = listOf(BarcodeFormat.QR_CODE)
        leitorQr.isAutoFocusEnabled = true
        leitorQr.autoFocusMode = AutoFocusMode.SAFE
        leitorQr.scanMode = ScanMode.SINGLE
        leitorQr.isFlashEnabled = false

        leitorQr.decodeCallback = DecodeCallback {
            val respIntent = Intent()
            respIntent.putExtra("qrcode", it.text)
            setResult(RESULT_OK, respIntent)
            finish()
        }

        leitorQr.errorCallback = ErrorCallback {
            Snackbar.make(binding.root, "N??o foi poss??vel abrir a c??mera", Snackbar.LENGTH_LONG).show()
            Log.e("QrCodeActivity", "inicializarLeitorQrCode", it)
            setResult(RESULT_CANCELED)
            finish()
        }


        leitorQr.startPreview()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissaoConcedida = true
                inicializarLeitorQrCode()
            } else if (!shouldShowRequestPermissionRationale(permissions[0])) {
                mostrarDialogoPermissaoCamera()
            } else {
                permissaoConcedida = false
                Snackbar.make(binding.root,
                    "Sem permiss??o de uso da c??mera n??o ?? poss??vel ler QR Codes. Habilite a permiss??o nas configura????es da aplica????o do Android", Snackbar.LENGTH_LONG).show()
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }

    private fun mostrarDialogoPermissaoCamera() {
        AlertDialog.Builder(this)
            .setTitle("Permiss??o de c??mera")
            .setMessage(
                "Habilite a permiss??o de uso da c??mera do aplicativo em Configura????es"
            )
            .setCancelable(false)
            .setPositiveButton(
                "Ir para configura????es") { dialogInterface, i ->
                val i = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts("package", packageName, null)
                i.data = uri
                startActivity(i)
                setResult(RESULT_CANCELED)
                finish()
            }
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialogInterface, i ->
                setResult(RESULT_CANCELED)
                finish()
            })
            .create()
            .show()
    }
}
