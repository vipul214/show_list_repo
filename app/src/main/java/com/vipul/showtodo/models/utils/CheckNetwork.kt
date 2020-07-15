package com.vipul.showtodo.models.utils

import android.content.Context
import android.net.Network
import android.net.ConnectivityManager
import kotlinx.coroutines.CompletableDeferred


class CheckNetwork() {

    // Network Check
    suspend fun getNetWorkStatus(): Boolean {
        val response = CompletableDeferred<Boolean>()
        try {
            val connectivityManager =
                ShowToDoApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            //val builder = NetworkRequest.Builder()

            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    //super.onAvailable(network)
                    response.complete(true)
                }

                override fun onUnavailable() {
                    //super.onUnavailable()
                    response.complete(false)
                }

                override fun onLost(network: Network) {
                    //super.onLost(network)
                    response.complete(false)
                }

                /*override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    response.complete(networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
                }*/
            })

        } catch (e: Exception) {
            return false
        }

        return response.await()

    }

}