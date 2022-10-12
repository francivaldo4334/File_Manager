package br.com.francivaldo.filemanager.viewmodel

import android.os.Environment
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.francivaldo.filemanager.R
import br.com.francivaldo.filemanager.model.UiFileModel
import java.io.File

object ConsoleViewModel {
    var localFile by mutableStateOf("")
    var isFileEmpty by mutableStateOf(false)
    var listUiFiles by mutableStateOf(listOf<UiFileModel>())
    init {
        localFile = Environment.getExternalStorageDirectory().path
        setFiles(localFile)
    }
    private fun getStringToStringListByFirstCharacter(path:String, character:Char):ArrayList<String>{
        var result = ArrayList<String>()
        var init = 0;
        var _path = path+character
        if(path[0] != character) _path = character+_path
        for(i in 0.._path.length-1){
            if(_path[i] == character && i != 0){
                result.add(_path.subSequence(init+1,i).toString())
                init = i
            }
        }
        return result
    }
    private fun getPathByNavigateString(navigeteString: String):String{
        val argList = getStringToStringListByFirstCharacter(navigeteString,'/')
        val pathList = getStringToStringListByFirstCharacter(localFile,'/')
        for (i in 0..argList.size-1){
            if(argList.get(i) == ".."&&localFile != Environment.getExternalStorageDirectory().path)
                    pathList.removeLast()
            else if(argList.get(i) != "..")
                pathList.add(argList.get(i))
            else{
                return "*CAMPO INVALIDO*"
            }
        }
        var result = ""
        for (i in 0..pathList.size-1){
            result+="/"+pathList.get(i)
        }
        return result
    }
    fun comand(arg:String):Boolean{
        val argList = getStringToStringListByFirstCharacter(arg,' ')
        when(argList[0]){
            "cd"->{
                val navigeteString = argList[1]
                var result = getPathByNavigateString(navigeteString)
                if(result == "*CAMPO INVALIDO*")
                    return false
                localFile = result
                setFiles(localFile)
                return true
            }
        }
        return false
    }
    private fun setFiles(path:String){
        localFile = path
        val root = File(path)
        val filesAndFolders = root.listFiles()
        if(filesAndFolders == null || filesAndFolders.size == 0){
            isFileEmpty = true
        }else{
            isFileEmpty = false
            listUiFiles = filesAndFolders.map { it.toUiFileModel() } as ArrayList<UiFileModel>
        }
    }
    fun File.toUiFileModel():UiFileModel{
        var resulteModel = UiFileModel(this.name,{
            comand("cd /${this.name}")
        })
        if(this.isDirectory)
            resulteModel.ImageRes =  R.drawable.ic_diretory
        else
            resulteModel.ImageRes = R.drawable.ic_file
        return resulteModel
    }
}