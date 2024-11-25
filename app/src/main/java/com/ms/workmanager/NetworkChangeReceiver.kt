package com.ms.workmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            if (!isNetworkConnected(it)) {
                Log.d("NetworkChangeReceiver", "Network disconnected, scheduling work after 15 minutes.")
                // Schedule WorkManager task after 15 minutes
                val workRequest = PeriodicWorkRequestBuilder<NoNetworkWorker>(15, TimeUnit.MINUTES)
                    .setInitialDelay(15, TimeUnit.MINUTES)
                    .build()

                WorkManager.getInstance(it).enqueue(workRequest)
            } else {
                Log.d("NetworkChangeReceiver", "Network connected, cancelling scheduled work.")
                // Cancel all existing NoNetworkWorker tasks if the network is reconnected
                WorkManager.getInstance(it).cancelAllWorkByTag("NoNetworkWorkerTag")
            }
        }
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}