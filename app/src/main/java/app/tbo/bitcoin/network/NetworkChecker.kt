package app.tbo.bitcoin.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Class to perform an action if there is a valid internet connection.
 * @param connectivityManager the connectivity manager to check network status
 */
class NetworkChecker(val connectivityManager: ConnectivityManager) {

    fun performAction(action: () -> Unit) {
        if (hasValidInternetConnection()) {
            action()
        }
    }

    private fun hasValidInternetConnection(): Boolean {
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}