package org.csystem.android.app.veterinarian

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.csystem.android.app.veterinarian.api.IVeterinarianService
import org.csystem.android.app.veterinarian.api.data.entity.VeterinariansInfo
import org.csystem.android.app.veterinarian.databinding.ActivityVeterinarinarianFindByLastNameBinding
import org.csystem.android.app.veterinarian.viewmodel.VeterinarianFindByLastNameActivityViewModel
import retrofit2.Call
import retrofit2.Response

import com.karandev.util.retrofit.putQueue
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.veterinarian.api.data.entity.VeterinarianInfo
import org.csystem.android.app.veterinarian.api.di.annotation.VeterinarianGetServiceInterceptor
import javax.inject.Inject

@AndroidEntryPoint
class VeterinarianFindByLastNameActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityVeterinarinarianFindByLastNameBinding
    @VeterinarianGetServiceInterceptor
    @Inject
    lateinit var veterinarianService: IVeterinarianService

    private fun offlineProc(pos: Int)
    {
        val diplomaNo = mBinding.viewModel!!.adapter.getItem(pos)?.diplomaNo;

        Toast.makeText(this, "$diplomaNo", Toast.LENGTH_LONG).show()
    }

    private fun onlineModeResponseCallback(response: Response<VeterinarianInfo>)
    {
        if (response.code() != 200) {
            Toast.makeText(this, R.string.no_veterinarian_message, Toast.LENGTH_LONG).show()
            return
        }

        val vi = response.body()

        if (vi != null) {
            val diplomaNo = vi.diplomaNo;
            val lastName = vi.lastName
            Toast.makeText(this, "Diploma No: $diplomaNo, Last Name:$lastName", Toast.LENGTH_LONG).show()
        }
        else
            Toast.makeText(this, R.string.problem_message, Toast.LENGTH_LONG).show()
    }

    private fun onlineModeFailCallback(call: Call<VeterinarianInfo>, ex: Throwable)
    {
        Toast.makeText(this, R.string.problem_try_again_message, Toast.LENGTH_LONG).show()
        Log.d("veterinarians_response", ex.message!!)
        call.cancel()
    }

    private fun onlineProc(pos: Int)
    {
        val diplomaNo = mBinding.viewModel!!.adapter.getItem(pos)?.diplomaNo
        val call = veterinarianService.findByDiplomaNo(diplomaNo!!)
        call.putQueue({_, r -> onlineModeResponseCallback(r)}) { c, r -> onlineModeFailCallback(c, r)}
    }

    private fun onItemClickListenerCallback(pos: Int)
    {
        AlertDialog.Builder(this)
            .setTitle(R.string.alert_dialog_offline_title)
            .setPositiveButton(R.string.alert_dialog_offline_positive_button_text) {_, _ -> onlineProc(pos)}
            .setNegativeButton(R.string.alert_dialog_offline_negative_button_text) {_, _ -> offlineProc(pos)}
            .setNeutralButton(R.string.alert_dialog_offline_neutral_button_text) {_, _ -> }
            .create().show()
    }

    private fun findResponseCallback(response: Response<VeterinariansInfo>)
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

    private fun findFailCallback(call: Call<VeterinariansInfo>, ex: Throwable)
    {
        Toast.makeText(this, R.string.problem_try_again_message, Toast.LENGTH_LONG).show()
        Log.d("veterinarians_response", ex.message!!)
        call.cancel()
    }

    private fun initVeterinariansListView()
    {
        mBinding.veterinarianFindByLastNameActivityVeterinarians
            .setOnItemClickListener{_, _, pos, _ -> onItemClickListenerCallback(pos)}
    }

    private fun initBinding()
    {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_veterinarinarian_find_by_last_name)
        mBinding.viewModel = VeterinarianFindByLastNameActivityViewModel(this)
    }

    private fun initialize()
    {
        initBinding()
        initVeterinariansListView()
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        initialize()
    }

    fun findButtonClicked()
    {
        mBinding.viewModel!!.adapter.clear()
        val call = veterinarianService.findByLastName(mBinding.viewModel!!.text)
        call.putQueue({_, r -> findResponseCallback(r)}) { c, r -> findFailCallback(c, r)}
    }

    fun exitButtonClicked() = finish()
}