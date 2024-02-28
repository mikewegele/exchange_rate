package app.tbo.bitcoin.data.util

import com.google.gson.Gson
import java.lang.reflect.Type

class JsonParser {

    fun <T> fromJson(json: String, type: Type): T? {
        return Gson().fromJson(json, type)
    }

}