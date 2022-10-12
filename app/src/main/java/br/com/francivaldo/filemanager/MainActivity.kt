package br.com.francivaldo.filemanager

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.francivaldo.filemanager.pages.ShowFilesPage
import br.com.francivaldo.filemanager.ui.theme.FileManagerTheme
import br.com.francivaldo.filemanager.viewmodel.ConsoleViewModel

class MainActivity : ComponentActivity() {
    lateinit var viewModel:ConsoleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!checPermission())
            requestPermission()
        viewModel = ConsoleViewModel
        setContent {
            FileManagerTheme {
                ShowFilesPage()
            }
        }
    }
    private fun requestPermission(){
        val permission = ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(permission){
            Toast.makeText(this,getString(R.string.message_permission_requere),Toast.LENGTH_LONG).show()
            return
        }
        ActivityCompat.requestPermissions(this, Array<String>(1){android.Manifest.permission.WRITE_EXTERNAL_STORAGE},111)
    }
    private fun checPermission():Boolean{
        val result = ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(result ==  PackageManager.PERMISSION_GRANTED)
            return true
        return false
    }
    override fun onBackPressed() {
        if(!viewModel.comand("cd ..")){
            super.onBackPressed()
        }
    }
}