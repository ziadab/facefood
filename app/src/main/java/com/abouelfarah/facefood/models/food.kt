package com.abouelfarah.facefood.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class food(val foodImage: String, val foodName: String, val foodDescription: String) : Parcelable {
    constructor() : this("", "", "")
}