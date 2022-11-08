package org.csystem.android.app.a010_quadraticequationapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.csystem.util.kotlin.math.solveQuadraticEquation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Demo code
        val (x1, x2, status) = solveQuadraticEquation(1.0, 4.0, 4.0)

        Toast.makeText(this, "x1 = $x1, x2 = $x2, status = $status", Toast.LENGTH_LONG).show()
    }
}