package com.abouelfarah.facefood.models


class User(val uid: String, val firstName: String, val lastName: String, val email: String, val pwd: String) {
    constructor() : this("", "", "", "", "")
}