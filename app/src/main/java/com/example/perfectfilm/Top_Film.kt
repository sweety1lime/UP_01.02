package com.example.perfectfilm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.perfectfilm.Model.ViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Top_Film : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sendNetvorkReauest()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_expected__film, container, false)
    }

    fun sendNetvorkReauest()
    {
        var const = Const()
        val builder = Retrofit.Builder().baseUrl(const.URL).addConverterFactory(GsonConverterFactory.create())
        val retrofit= builder.build()
        val interfase = retrofit.create<Interface>(Interface::class.java)

        val call: retrofit2.Call<ViewModel> = interfase.getTop_Movie()

        call.enqueue(object : retrofit2.Callback<ViewModel>
        {
            override fun onFailure(call: Call<ViewModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<ViewModel>, response: Response<ViewModel>) {

                Log.i("Test",  response.body()!!.result!!.get(0).title!!)
            }

        })
    }
}