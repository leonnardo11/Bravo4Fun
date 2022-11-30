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
            Snackbar.make(binding.root, "Não foi possível abrir a câmera", Snackbar.LENGTH_LONG).show()
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
                    "Sem permissão de uso da câmera não é possível ler QR Codes. Habilite a permissão nas configurações da aplicação do Android", Snackbar.LENGTH_LONG).show()
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }

    private fun mostrarDialogoPermissaoCamera() {
        AlertDialog.Builder(this)
            .setTitle("Permissão de câmera")
            .setMessage(
                "Habilite a permissão de uso da câmera do aplicativo em Configurações"
            )
            .setCancelable(false)
            .setPositiveButton(
                "Ir para configurações") { dialogInterface, i ->
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
