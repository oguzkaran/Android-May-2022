package org.csystem.android.app.randomtextgenerator.data.entity

import java.io.Serializable

data class SensorInfo(var name: String, var host: String, var port: Int, var status: SensorStatus) : Serializable