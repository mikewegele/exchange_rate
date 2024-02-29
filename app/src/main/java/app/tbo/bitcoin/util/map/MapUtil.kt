package app.tbo.bitcoin.util.map

fun <K, V> getKeyByPropertyValue(map: Map<K, V>, propertyName: String, propertyValue: Any): K? {
    for ((key, mapValue) in map) {
        val property = mapValue!!::class.members.find { it.name == propertyName }
        if (property != null && property.call(mapValue) == propertyValue) {
            return key
        }
    }
    return null
}
