package org.csystem.android.app.hiltdi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalTimeAuthInterceptor
import org.csystem.android.app.hiltdi.configuration.inject.annotattion.mapper.TimeStringMapperAuthInterceptor
import org.csystem.android.app.hiltdi.data.IMapper
import java.time.temporal.Temporal
import javax.inject.Inject

@AndroidEntryPoint
class SecondActivity : AppCompatActivity() {

    @LocalTimeAuthInterceptor
    @Inject
    lateinit var time: Temporal

    @TimeStringMapperAuthInterceptor
    @Inject
    lateinit var timeStringMapper: IMapper<Temporal, String>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        Toast.makeText(this, "SecondActvity -> Mapper Info: ${timeStringMapper.toType2(time)}", Toast.LENGTH_LONG).show()
    }
}