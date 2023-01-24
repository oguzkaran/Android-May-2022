package org.csystem.android.app.veterinarian

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.karandev.util.retrofit.putQueue
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.veterinarian.api.IVeterinarianService
import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianSave
import org.csystem.android.app.veterinarian.api.di.annotation.VeterinarianPostServiceInterceptor
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarianSaveBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianSaveActivityViewModel
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class VeterinarianSaveActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarianSaveBinding
    @VeterinarianPostServiceInterceptor
    @Inject
    lateinit var veterinarianService: IVeterinarianService

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

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarian_save)
        mBinding.viewModel = VeterinarianSaveActivityViewModel(this)
    }

    private fun init()
    {
        initBinding()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        init()
    }

    fun saveButtonClicked()
    {
        val call = veterinarianService.save(mBinding.viewModel!!.veterinarianSave!!)

        call.putQueue({_, r -> responseCallback(r)}) {c, ex -> failCallback(c, ex)}
    }

    fun exitButtonClicked() = finish()
}