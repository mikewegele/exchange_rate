package app.tbo.bitcoin.util.json

import com.google.gson.Gson
import java.lang.reflect.Type

fun <T> fromJson(json: String, type: Type): T? {
    return Gson().fromJson(json, type)
}
