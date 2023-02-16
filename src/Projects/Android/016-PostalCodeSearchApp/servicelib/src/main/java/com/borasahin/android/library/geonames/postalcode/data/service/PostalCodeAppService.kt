package com.borasahin.android.library.geonames.postalcode.data.service

import android.os.Handler
import android.os.Looper
import android.os.Message
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.IPostalCodeMapper
import com.erbaris.android.library.geonames.postalcode.data.dal.PostalCodeAppHelper
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCodeInfo
import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class PostalCodeAppService @Inject constructor() {
    @Inject
    lateinit var postalCodeAppHelper: PostalCodeAppHelper

    @Inject
    lateinit var postalCodeMapper: IPostalCodeMapper

    @Inject
    lateinit var threadPool: ExecutorService

    private fun savePostalCodeThreadCallBack(postalCodeSaveDTOs: List<PostalCodeSaveDTO>, handler: Handler, resultBlock: (Boolean) -> Unit,
                                             failBlock: (DataServiceException) -> Unit)
    {
        try {
            if (postalCodeSaveDTOs.isEmpty()) {
                resultBlock(false)
                return
            }
            val code = postalCodeSaveDTOs[0].code;
            val list = postalCodeSaveDTOs.map { postalCodeMapper.toPostalCode(it) }.toList();

            handler.sendMessage(handler.obtainMessage(0, postalCodeAppHelper.savePostalCode(PostalCodeInfo(code), list)))
        }
        catch (ex: RepositoryException) {
            failBlock(DataServiceException("PostalCodeAppService.savePostalCode", ex.cause))
        }
    }

    private fun findPostalCodesByCodeThreadCallback(code: Int, resultBlock: (MutableIterable<PostalCodeDTO>) -> Unit,
                                      failBlock: (DataServiceException) -> Unit)
    {
        try {
            resultBlock(postalCodeAppHelper.findPostalCodesByCode(code).map { postalCodeMapper.toPostalCodeDTO(it) }.toMutableList())
        }
        catch (ex: RepositoryException) {
            failBlock(DataServiceException("PostalCodeAppService.savePostalCode", ex.cause))
        }
    }

    fun savePostalCode(postalCodeSaveDTOs: List<PostalCodeSaveDTO>, resultBlock: (Boolean) -> Unit,
                       failBlock: (DataServiceException) -> Unit)
    {
        val handler = object: Handler(Looper.myLooper()!!) {
            override fun handleMessage(msg: Message)
            {
                //...
                resultBlock(msg.obj as Boolean )
            }
        }
        threadPool.execute{savePostalCodeThreadCallBack(postalCodeSaveDTOs, handler, resultBlock, failBlock)}
    }

    fun findPostalCodesByCode(code: Int, resultBlock: (MutableIterable<PostalCodeDTO>) -> Unit,
                              failBlock: (DataServiceException) -> Unit)
    {

        threadPool.execute{findPostalCodesByCodeThreadCallback(code, resultBlock, failBlock)}
    }
}