package br.com.francivaldo.filemanager.model

import androidx.annotation.DrawableRes

data class UiFileModel(val fileName:String,val onClik:()->Unit,@DrawableRes var ImageRes:Int = 0)