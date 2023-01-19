package org.csystem.android.app.veterinarian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karandev.util.retrofit.RetrofitUtil
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.veterinarian.api.GET_SERVICE_BASE_URL
import org.csystem.android.app.veterinarian.api.IVeterinarianService
import org.csystem.android.app.veterinarian.api.data.entity.CountInfo
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarianInfoBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianInfoActivityViewModel
import retrofit2.Call
import retrofit2.Response

@AndroidEntryPoint
class VeterinarianInfoActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarianInfoBinding
    private lateinit var mVeterinarianService: IVeterinarianService

    private fun responseCallback(response: Response<CountInfo>)
    {
        val countInfo = response.body();

        if (countInfo != null)
            Toast.makeText(this, "Number Of Veterinarians:${countInfo.count}", Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, R.string.problem_message, Toast.LENGTH_LONG).show()
    }

    private fun failCallback(call: Call<CountInfo>, ex: Throwable)
    {
        Toast.makeText(this, R.string.problem_try_again_message, Toast.LENGTH_LONG).show()
        Log.d("veterinarian_response", ex.message!!)
        call.cancel()
    }

    private fun initVeterinarianService()
    {
        mVeterinarianService = RetrofitUtil.createRetrofit(GET_SERVICE_BASE_URL).create(IVeterinarianService::class.java)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarian_info)
        mBinding.viewModel = VeterinarianInfoActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initVeterinarianService()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun countButtonClicked()
    {
        val call = mVeterinarianService.count()

        RetrofitUtil.enqueue(call, {_, r -> responseCallback(r)}) {c, r -> failCallback(c, r)}
    }

    fun findByLastNameButtonClicked()
    {
        Intent(this, VeterinarianFindByLastNameActivity::class.java).apply { startActivity(this) }
    }

    fun exitButtonClicked() = finish()
}