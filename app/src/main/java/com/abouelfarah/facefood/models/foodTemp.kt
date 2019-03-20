package com.abouelfarah.facefood.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class foodTemp (val foodImage : Int, val foodName:String,val foodDescription:Int ):Parcelable{
    constructor() : this(1,"", 1)
}