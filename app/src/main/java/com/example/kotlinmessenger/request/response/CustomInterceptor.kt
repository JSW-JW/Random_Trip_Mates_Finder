package com.example.kotlinmessenger.request.response

import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class CustomInterceptor (private val category: Int): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val requestBody = request.body
        val subtype = requestBody?.contentType()?.subtype
        if(subtype != null
            && subtype.contains("json")) {
            val bodyWithToken = addCategoryParamToJSONBody(requestBody)
            if(null != bodyWithToken){
                val requestBuilder = request.newBuilder()
                request = requestBuilder
                    .post(bodyWithToken)
                    .build()
            }
        }
        return chain.proceed(request)
    }

    private fun addCategoryParamToJSONBody(requestBody: RequestBody?) : RequestBody? {
        val customRequest = bodyToString(requestBody)
        return try{
            val jsonObject = JSONObject(customRequest)
            jsonObject.put("category", category)
            RequestBody.create(requestBody?.contentType(), jsonObject.toString())
        } catch (e: JSONException){
            e.printStackTrace()
            null
        }
    }

    private fun bodyToString(requestBody: RequestBody?): String{
        return try {
            val buffer = Buffer()
            requestBody?.writeTo(buffer)
            buffer.readUtf8()
        } catch (e: IOException){
            e.printStackTrace()
            ""
        }
    }
}