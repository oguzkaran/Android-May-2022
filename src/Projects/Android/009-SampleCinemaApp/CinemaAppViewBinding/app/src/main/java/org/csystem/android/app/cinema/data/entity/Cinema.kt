package org.csystem.android.app.cinema.data.entity

import java.io.Serializable

data class Cinema(var name: String, var type: Int, var year: Int, var director: String, var company: String) : Serializable {
    var id: Int = 0
    override fun toString() = name
}