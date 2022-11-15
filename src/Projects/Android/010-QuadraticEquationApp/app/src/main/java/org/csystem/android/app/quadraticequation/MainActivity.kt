package org.csystem.android.app.quadraticequation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.quadraticequation.databinding.ActivityMainBinding
import org.csystem.android.app.quadraticequation.viewmodel.QuadraticEquationViewModel
import org.csystem.util.kotlin.math.solveQuadraticEquation

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.viewModel = QuadraticEquationViewModel(this)
        mBinding.result = ""
    }

    private fun initialize()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun solveButtonClicked()
    {
        val (x1, x2, status) = solveQuadraticEquation(mBinding.viewModel!!.a, mBinding.viewModel!!.b, mBinding.viewModel!!.c)

        mBinding.result = if (status) "x1 = $x1, x2 = $x2" else "NO REAL ROOT!..."
    }
}