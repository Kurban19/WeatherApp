package com.shkiper.whether

import androidx.fragment.app.Fragment
import com.shkiper.whether.fragments.WeatherFragment

class MainActivity : SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return WeatherFragment.newInstance()
    }


//    inner class WeatherTask() : AsyncTask<String, Void, String>() {
//        override fun onPreExecute() {
//            super.onPreExecute()
//            /* Showing the ProgressBar, Making the main design GONE */
//            loader.visibility = View.VISIBLE
//            errorText.visibility = View.GONE
//        }
//
//        override fun doInBackground(vararg params: String?): String? {
//            return try{
//                URL("https://api.openweathermap.org/data/2.5/weather?id=${CITY}&appid=${Common.APP_ID}&lang=ru").readText(
//                    Charsets.UTF_8
//                )
//            }catch (e: Exception){
//                null
//            }
//        }
//
//        @SuppressLint("SetTextI18n")
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            try {
//                /*Extracting JSON returns from the API*/
//                val jsonObj = JSONObject(result)
//                val main = jsonObj.getJSONObject("main")
//                val temp = main.getString("temp")
//                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
//                val description = weather.getString("main")
//                //val updatedAt:Long = jsonObj.getLong("dt")
//                //val updatedAtText = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH).format(Date(updatedAt*1000))
//                val sys = jsonObj.getJSONObject("sys")
//                val address = jsonObj.getString("name")+", "+sys.getString("country")
//
//
//                val index: Int = temp.indexOf(".") //Removing all the useless characters in temp. Example 12.32342 -> 12
//                curr_temp.text = temp.substring(0, index) + "°C"
//                txt_city.text = address
//                //txt_updated_at.text = updatedAtText
//                txt_description.text = description
//
//
//                loader.visibility = View.GONE
//
//
//
//            } catch (e: Exception) {
//                println(e.stackTrace)
//                //txt_city.text = e.message
//                loader.visibility = View.GONE
//                errorText.visibility = View.VISIBLE
//            }
//
//        }
//    }

}