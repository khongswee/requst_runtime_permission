package com.kh.requset.requestpermission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.security.Permission

class MainActivity : AppCompatActivity() {
    private val requestMultiPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGrantedPermission ->
            Log.d("MainActivity", isGrantedPermission.toString())
            val listNotGrant = isGrantedPermission.filter { it.value == false }
            if (listNotGrant.isEmpty()) {
                doSomething("Granted all permissions")
            }

        }

    private val requestSinglePermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                doSomething("Granted permission")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun singlePermission(v: View) {
        requestSinglePermission.launch(Manifest.permission.READ_CONTACTS)
    }

    fun multiplePermission(v: View) {
        requestMultiPermission.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )
        )
    }

    private fun doSomething(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}