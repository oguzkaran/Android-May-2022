package org.csystem.android.app.hiltdi.data


import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import org.csystem.android.app.hiltdi.configuration.inject.annotation.datetime.LocalDateTimeAuthInterceptor
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScoped
class DateTimeInfo @Inject constructor (@LocalDateTimeAuthInterceptor private val mTemporal: Temporal,
                                        private val mFormatter: DateTimeFormatter,
                                        @ActivityContext private val mActivityContext: Context,
                                        @ApplicationContext private val mApplicationContext: Context) {
    init {
        Toast.makeText(mActivityContext, "Init:${mFormatter.format(mTemporal)}", Toast.LENGTH_LONG).show()

        AlertDialog.Builder(mActivityContext)
            .setTitle("Dikkat")
            .setMessage("Merhaba")
            .create()
            .show()
        Toast.makeText(mApplicationContext, "Application Init:${mFormatter.format(mTemporal)}", Toast.LENGTH_LONG).show()
    }
    override fun toString(): String = mFormatter.format(mTemporal)
}