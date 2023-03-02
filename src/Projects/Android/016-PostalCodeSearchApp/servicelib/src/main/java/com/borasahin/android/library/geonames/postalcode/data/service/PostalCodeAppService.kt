package com.borasahin.android.library.geonames.postalcode.data.service

import android.os.Handler
import android.widget.Toast
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeDTO
import com.borasahin.android.library.geonames.postalcode.data.service.dto.PostalCodeSaveDTO
import com.borasahin.android.library.geonames.postalcode.data.service.mapper.IPostalCodeMapper
import com.erbaris.android.library.geonames.postalcode.data.dal.PostalCodeAppHelper
import com.erbaris.android.library.geonames.postalcode.data.entity.PostalCodeInfo
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.api.IPostalCodeSearch
import com.gokhandiyaroglu.android.library.geonames.postalcodesearch.retrofit.data.entity.PostalCodes
import com.karandev.util.data.repository.exception.RepositoryException
import com.karandev.util.data.service.DataServiceException
import com.karandev.util.retrofit.RetrofitUtil
import com.karandev.util.retrofit.putQueue
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.ExecutorService
import javax.inject.Inject

class PostalCodeAppService @Inject constructor() {
    @Inject
    lateinit var postalCodeAppHelper: PostalCodeAppHelper

    @Inject
    lateinit var postalCodeMapper: IPostalCodeMapper

    @Inject
    lateinit var threadPool: ExecutorService

    @Inject
    lateinit var handler: Handler

    @Inject
    lateinit var postalCodeSearch: IPostalCodeSearch

    private fun responseCallback(code: String, response: Response<PostalCodes>) : MutableIterable<PostalCodeDTO>
    {
        try {
            val postalCodes = response.body()

            if (postalCodes != null) {
                return postalCodes.codes.map {
                    it.code = code; postalCodeMapper.toPostalCodeDTO(it)
                }.toMutableList()
            }
            else
                throw RuntimeException("Problem occurs")
        }
        catch (ex: Throwable) {
            throw ex
        }
    }

    private fun failCallback(call: Call<PostalCodes>, ex: Throwable)
    {
        //...
        call.cancel()
    }

    private fun savePostalCodeThreadCallBack(postalCodeSaveDTOs: List<PostalCodeSaveDTO>, resultBlock: (Boolean) -> Unit,
                                             failBlock: (DataServiceException) -> Unit)
    {
        try {
            if (postalCodeSaveDTOs.isEmpty()) {
                resultBlock(false)
                return
            }
            val code = postalCodeSaveDTOs[0].code;
            val list = postalCodeSaveDTOs.map { postalCodeMapper.toPostalCode(it) }.toList();
            val ho = SavePostalCodeResultHandlerObject(postalCodeAppHelper.savePostalCode(PostalCodeInfo(code), list), resultBlock)

            handler.sendMessage(handler.obtainMessage(1, ho))
        }
        catch (ex: RepositoryException) {
            val ho = FailHandlerObject(DataServiceException("PostalCodeAppService.savePostalCode", ex.cause), failBlock)

            handler.sendMessage(handler.obtainMessage(2, ho))
        }
    }

    private fun findPostalCodesByCodeThreadCallback(code: Int, resultBlock: (MutableIterable<PostalCodeDTO>) -> Unit,
                                      failBlock: (DataServiceException) -> Unit)
    {
        try {
            val list = postalCodeAppHelper.findPostalCodesByCode(code).map { postalCodeMapper.toPostalCodeDTO(it) }.toMutableList()

            if (list.isNotEmpty()) {
                val ho = FindPostalCodeByCodeResultHandlerObject(list, resultBlock)

                handler.sendMessage(handler.obtainMessage(3, ho))
            }
            else {
                val call = postalCodeSearch.findPostalCode(code.toString())
                var codes : MutableIterable<PostalCodeDTO> = ArrayList<PostalCodeDTO>()

                call.putQueue({_, r -> codes = responseCallback(code.toString(), r)}) {c, ex -> failCallback(c, ex)}

                val ho = FindPostalCodeByCodeResultHandlerObject(codes, resultBlock)

                handler.sendMessage(handler.obtainMessage(3, ho))
            }
        }
        catch (ex: Throwable) {
            val cause = if (ex.cause != null) ex.cause else null
            val ho = FailHandlerObject(DataServiceException("PostalCodeAppService.findPostalCodesByCodeThreadCallback", cause), failBlock)

            handler.sendMessage(handler.obtainMessage(4, ho))
        }
    }

    fun findPostalCodesByCode(code: Int, resultBlock: (MutableIterable<PostalCodeDTO>) -> Unit,
                              failBlock: (DataServiceException) -> Unit)
    {

        threadPool.execute{findPostalCodesByCodeThreadCallback(code, resultBlock, failBlock)}
    }

    fun savePostalCode(postalCodeSaveDTOs: List<PostalCodeSaveDTO>, resultBlock: (Boolean) -> Unit,
                       failBlock: (DataServiceException) -> Unit)
    {
        threadPool.execute{savePostalCodeThreadCallBack(postalCodeSaveDTOs, resultBlock, failBlock)}
    }
}