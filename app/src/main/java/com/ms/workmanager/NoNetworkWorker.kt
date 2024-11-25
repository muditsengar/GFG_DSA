package com.ms.workmanager

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters

class NoNetworkWorker(context: Context, workParams: WorkerParameters) : Worker(context, workParams) {
    override fun doWork(): Result {

        return if (!isNetworkConnected(applicationContext)) {
            Log.d("NoNetworkWorker", "No network for 15 minutes. Running background task.")

            // Perform your background task here, like logging, data caching, etc.

            // Return success, and WorkManager will run this again after 15 minutes.
            Result.success()
        } else {
            Log.d("NoNetworkWorker", "Network is connected. Cancelling periodic work.")

            // Cancel the work to stop further periodic executions
            WorkManager.getInstance(applicationContext).cancelAllWorkByTag("NoNetworkWorkerTag")

            // Return success, but no more periodic work will be triggered after this
            Result.success()
        }
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }
}