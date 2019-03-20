package com.abouelfarah.facefood.models

class food(val foodPicture: String, val foodName: String, val foodDescription: String) {
    constructor() : this("", "", "")
}