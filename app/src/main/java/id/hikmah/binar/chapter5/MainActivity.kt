package id.hikmah.binar.chapter5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import id.hikmah.binar.chapter5.service.APIClient
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var objectUser: JSONObject
    private lateinit var arrayObjectUser: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createJsonObject()
        getDataFromJsonObject()
        removeDataFromJsonObject()
        createJsonArray()
    }

    private fun createJsonObject() {
        /**
         * {
         * }
         */
        objectUser = JSONObject()
        Log.e("objectUser", objectUser.toString())

        /**
         * {
         *      "nama": "hadi"
         * }
         */
        objectUser.put("nama", "hadi")

        Log.e("objectUser", objectUser.toString())

        /**
         * {
         *      "nama": "hadi",
         *      "umur": 26
         * }
         */
        objectUser.put("umur", 26)
        Log.e("objectUser", objectUser.toString())

        /**
         * {
         *      "nama": "nurrahman hadi",
         *      "umur": 26
         * }
         */
        objectUser.put("nama", "nurrahman hadi")
        Log.e("objectUser", objectUser.toString())

        /**
         * {
         *      "nama": "nurrahman hadi",
         *      "umur": 26,
         *      "null": null
         * }
         */
        objectUser.put("null", null)
        Log.e("objectUser", objectUser.toString())

        /**
         * {
         *      "nama": "nurrahman hadi",
         *      "umur": 26,
         *      "null": null,
         *      "isSingle: false
         * }
         */
        objectUser.put("isSingle", false)
        Log.e("objectUser", objectUser.toString())
    }

    private fun getDataFromJsonObject() {
        // get nama dari json object
        val getNama = objectUser.getString("nama")

        // get umur dari json object
        val getUmur = objectUser.getInt("umur")

        // get null dari json object
//        val getNull = objectUser.getString("null").toString()

        // get isSingle dari json object
        val getIsSingle = objectUser.getBoolean("isSingle")

    }

    private fun removeDataFromJsonObject() {
        // remove nama dari json object
        objectUser.remove("nama")
    }

    private fun createJsonArray() {
        /**
         * [
         * ]
         */
        arrayObjectUser = JSONArray()
        Log.e("arrayUser", arrayObjectUser.toString())

        /**
         * [
         *      26
         * ]
         */
        arrayObjectUser.put(26)
        Log.e("arrayUser", arrayObjectUser.toString())

        /**
         * [
         *      26,
         *      {
         *              "nama": "nurrahman hadi",
         *              "umur": 26,
         *              "null": null,
         *              "isSingle: false
         *      }
         * ]
         */
        arrayObjectUser.put(objectUser)
        Log.e("arrayUser", arrayObjectUser.toString())

        /**
         * [
         *      26,
         *      {
         *              "nama": "nurrahman hadi",
         *              "umur": 26,
         *              "null": null,
         *              "isSingle: false
         *      },
         *      false
         * ]
         */
        arrayObjectUser.put(false)
        Log.e("arrayUser", arrayObjectUser.toString())

        arrayObjectUser.getInt(0)
        arrayObjectUser.getJSONObject(1)
        arrayObjectUser.getBoolean(2)

        arrayObjectUser.remove(2)
    }

    private fun getDataNetwork(){
        val apiService = APIClient.instance
    }
}