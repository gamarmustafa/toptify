package com.example.toptify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.toptify.R
import com.example.toptify.artists.Artists
import com.example.toptify.databinding.ActivityTopGenresBinding
import com.example.toptify.utils.API
import retrofit.*

class TopGenresActivity : BaseActivity() {
    lateinit var  binding:ActivityTopGenresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTopGenresBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val token: String = bundle!!.getString("token").toString()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service: API = retrofit.create(API::class.java)
        binding.btn4Weeks.setOnClickListener {
            showProgressDialog()
            val listCall: Call<Artists> =service.getArtists(": Bearer "+token,30,0, FOUR_WEEKS)
            listCall.enqueue(object : Callback<Artists> {
                override fun onResponse(response: Response<Artists>?, retrofit: Retrofit?) {

                    if (response?.body() != null) {
                        val intent = Intent(this@TopGenresActivity,GenreResultActivity::class.java)
                        intent.putExtra("list",response.body())
                        intent.putExtra("title","Top Artists\n(last 4 weeks)")
                        hideProgressDialog()
                        startActivity(intent)
                    }


                    if (response?.body() == null) {
                        Log.i("Response!", "null response body /4weeks")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    Log.e("Error", t!!.message.toString())
                }

            })
        }
        binding.btn6Month.setOnClickListener {
            showProgressDialog()
            val listCall: Call<Artists> =service.getArtists(": Bearer "+token,30,0, SIX_MONTHS)
            listCall.enqueue(object : Callback<Artists> {
                override fun onResponse(response: Response<Artists>?, retrofit: Retrofit?) {

                    if (response?.body() != null) {
                        val intent = Intent(this@TopGenresActivity,GenreResultActivity::class.java)
                        intent.putExtra("list",response.body())
                        intent.putExtra("title","Top Artists\n(last 6 months)")
                        hideProgressDialog()
                        startActivity(intent)
                    }


                    if (response?.body() == null) {
                        Log.i("Response!", "null response body /4weeks")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    Log.e("Error", t!!.message.toString())
                }

            })
        }
        binding.btnAllTime.setOnClickListener {
            showProgressDialog()
            val listCall: Call<Artists> =service.getArtists(": Bearer "+token,30,0, ALL_TIME)
            listCall.enqueue(object : Callback<Artists> {
                override fun onResponse(response: Response<Artists>?, retrofit: Retrofit?) {

                    if (response?.body() != null) {
                        val intent = Intent(this@TopGenresActivity,GenreResultActivity::class.java)
                        intent.putExtra("list",response.body())
                        intent.putExtra("title","Top Artists\n(all time)")
                        hideProgressDialog()
                        startActivity(intent)
                    }


                    if (response?.body() == null) {
                        Log.i("Response!", "null response body /4weeks")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    Log.e("Error", t!!.message.toString())
                }

            })
        }
    }
}