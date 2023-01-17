package org.csystem.android.app.veterinarian

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.retrofit.RetrofitUtil
import org.csystem.android.app.veterinarian.api.GET_SERVICE_BASE_URL
import org.csystem.android.app.veterinarian.api.IVeterinarianService
import org.csystem.android.app.veterinarian.api.data.entity.VeterinariansInfo
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarinarianFindByLastNameBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianFindByLastNameActivityViewModel
import retrofit2.Call
import retrofit2.Response

import com.karandev.util.retrofit.putQueue

class VeterinarianFindByLastNameActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarinarianFindByLastNameBinding
    private lateinit var mVeterinarianService: IVeterinarianService

    private fun responseCallback(response: Response<VeterinariansInfo>)
    {
        val vi = response.body()

        if (vi != null)
            if (vi.veterinarians.isNotEmpty())
                vi.veterinarians.forEach { mBinding.viewModel!!.adapter.add(it) }
            else
                Toast.makeText(this, R.string.no_veterinarian_message, Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, R.string.problem_message, Toast.LENGTH_LONG).show()
    }

    private fun failCallback(call: Call<VeterinariansInfo>, ex: Throwable)
    {
        Toast.makeText(this, R.string.problem_try_again_message, Toast.LENGTH_LONG).show()
        Log.d("veterinarians_response", ex.message!!)
        call.cancel()
    }

    private fun initVeterinarianService()
    {
        mVeterinarianService = RetrofitUtil.createRetrofit(GET_SERVICE_BASE_URL).create(IVeterinarianService::class.java)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarinarian_find_by_last_name)
        mBinding.viewModel = VeterinarianFindByLastNameActivityViewModel(this)
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

    fun findButtonClicked()
    {
        mBinding.viewModel!!.adapter.clear()
        val call = mVeterinarianService.findByLastName(mBinding.viewModel!!.text)
        call.putQueue({_, r -> responseCallback(r)}) {c, r -> failCallback(c, r)}
    }

    fun exitButtonClicked() = finish()
}