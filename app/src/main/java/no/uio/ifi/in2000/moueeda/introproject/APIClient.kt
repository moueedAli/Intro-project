package no.uio.ifi.in2000.moueeda.introproject

import java.io.IOException
import okhttp3.*

object APIClient {
    private val client = OkHttpClient()

    fun fetchNumberFact(number: Int, callback: (String?) -> Unit) {
        val request = Request.Builder()
            .url("https://numbersapi.p.rapidapi.com/${number}/math")
            .get()
            .addHeader("x-rapidapi-key", "ecc273744fmshfb5a7b464deb199p1c8da8jsn2580c13dceb8")
            .addHeader("x-rapidapi-host", "numbersapi.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {callback(it)}
            }
        })
    }

    fun fetchDateFact(month: Int, day: Int, callback: (String?) -> Unit) {
        val request = Request.Builder()
            .url("https://numbersapi.p.rapidapi.com/${month}/${day}/date")
            .addHeader("x-rapidapi-key", "ecc273744fmshfb5a7b464deb199p1c8da8jsn2580c13dceb8")
            .addHeader("x-rapidapi-host", "numbersapi.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let {callback(it)}
            }
        })
    }
}

