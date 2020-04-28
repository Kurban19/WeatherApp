package com.shkiper.whether

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.shkiper.whether.common.Common
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mFusedLocationClient: FusedLocationProviderClient

    private val PERMISSION_ID = 42
    //val CITY: String = "dhaka,bd"
    //val API: String = "a6f096e9c7045fffc90db97209788939"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()

        //weatherTask().execute()

    }

    inner class WeatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            loader.visibility = View.VISIBLE
            errorText.visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            return try{
                    URL("https://api.openweathermap.org/data/2.5/weather?lat=${Common.current_location.latitude}&lon=${Common.current_location.longitude}&units=metric&appid=${Common.APP_ID}&lang=ru").readText(
                        Charsets.UTF_8
                    )
            }catch (e: Exception){
                null
            }
        }

        @SuppressLint("SetTextI18n")
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /*Extracting JSON returns from the API*/
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val temp = main.getString("temp")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val description = weather.getString("main")
                //val updatedAt:Long = jsonObj.getLong("dt")
                //val updatedAtText = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).format(Date(updatedAt*1000))
                val sys = jsonObj.getJSONObject("sys")
                val address = jsonObj.getString("name")+", "+sys.getString("country")


                val index: Int = temp.indexOf(".") //Removing all the useless characters in temp. Example 12.32342 -> 12
                curr_temp.text = temp.substring(0, index) + "°C"
                txt_city.text = address
                //txt_updated_at.text = updatedAtText
                txt_description.text = description


                loader.visibility = View.GONE



            } catch (e: Exception) {
                println(e.stackTrace)
                //txt_city.text = e.message
                loader.visibility = View.GONE
                errorText.visibility = View.VISIBLE
            }

        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(this) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        requestNewLocationData()
                    } else {
                        Common.current_location.latitude = location.latitude
                        Common.current_location.longitude = location.longitude
                        WeatherTask().execute()
                    }
                }
            } else {
                Toast.makeText(this, "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }
    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }

    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation
        }
    }


    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }


    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true
        }
        return false
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }


//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        if(requestCode == PERMISSION_ID) {
//           // if((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                // Granted. Start getting the location information
//
//            //}
//        }
//    }
}