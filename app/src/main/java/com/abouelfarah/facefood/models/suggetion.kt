package com.abouelfarah.facefood.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class suggetion(val name:String, val content:String) : Parcelable {
    constructor() : this("", "")
}