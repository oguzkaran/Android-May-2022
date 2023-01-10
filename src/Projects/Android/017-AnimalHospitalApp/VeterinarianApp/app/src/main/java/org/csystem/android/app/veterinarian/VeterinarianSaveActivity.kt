package org.csystem.android.app.veterinarian

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.karandev.util.retrofit.RetrofitUtil
import com.karandev.util.retrofit.putQueue
import org.csystem.android.app.veterinarian.api.IVeterinarianService
import org.csystem.android.app.veterinarian.api.POST_SERVICE_BASE_URL
import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianSave
import org.csystem.android.app.veterinarian.databinding.ActivityMainBinding
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarianSaveBinding
import org.csystem.android.app.veterinarian.viewmodel.MainActivityViewModel
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianSaveActivityViewModel
import retrofit2.Call
import retrofit2.Response

class VeterinarianSaveActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarianSaveBinding
    private lateinit var mVeterinarianService: IVeterinarianService

    private fun responseCallback(response: Response<VeterinarianSave>)
    {
        val veterinarian = response.body()

        if (veterinarian != null) {
            Toast.makeText(this, "${veterinarian.diplomaNo} ${veterinarian.citizenId}", Toast.LENGTH_SHORT).show()
            //...
        }
        else
            Toast.makeText(this, R.string.problem_message, Toast.LENGTH_LONG).show()
    }

    private fun failCallback(call: Call<VeterinarianSave>, ex: Throwable)
    {
        Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        call.cancel()
    }

    private fun initVeterinarianService()
    {
        mVeterinarianService = RetrofitUtil.createRetrofitWithLogging(POST_SERVICE_BASE_URL).create(IVeterinarianService::class.java)
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarian_save)
        mBinding.viewModel = VeterinarianSaveActivityViewModel(this)
    }

    private fun init()
    {
        initBinding()
        initVeterinarianService()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        init()
    }

    fun saveButtonClicked()
    {
        val call = mVeterinarianService.save(mBinding.viewModel!!.veterinarianSave!!)

        call.putQueue({_, r -> responseCallback(r)}) {c, ex -> failCallback(c, ex)}
    }

    fun exitButtonClicked() = finish()
}