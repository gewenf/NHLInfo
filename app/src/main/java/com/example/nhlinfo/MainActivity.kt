package com.example.nhlinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener{
            getNHLInfo()
        }
    }

    fun getNHLInfo(){
        val queue = Volley.newRequestQueue(this)
        val url : String = "https://statsapi.web.nhl.com/api/v1/teams"

        val stringReq = StringRequest(
            Request.Method.GET, url,
            Response.Listener<String> { response ->
                var strResp = response.toString()
                val jsonObj : JSONObject = JSONObject(strResp)
                val jsonArray: JSONArray = jsonObj.getJSONArray("teams")

                val nameArray = Array<String>(jsonArray.length()){"null"}
                val locationArray = Array<String>(jsonArray.length()){"null"}
                val yearArray = Array<String>(jsonArray.length()){"null"}
                var index = 0
                for(i in 0 until jsonArray.length()){
                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
                    nameArray[index] = jsonInner.get("teamName") as String
                    locationArray[index] = jsonInner.get("locationName") as String
                    yearArray[index] = jsonInner.get("firstYearOfPlay") as String
                    index++
                }
                val myListAdapter = MyListAdapter(this, nameArray, locationArray, yearArray)
                listView.adapter = myListAdapter
            },
            Response.ErrorListener {  Toast.makeText(applicationContext,"That didn't work", Toast.LENGTH_LONG).show()})
        queue.add(stringReq)
    }
}
