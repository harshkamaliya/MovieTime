package com.example.movie.Broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.example.movie.ui.clickListener.NetworkListener

class MyNetworkBroadCast(val context: Context, val networkListner: NetworkListener):BroadcastReceiver() {

    private val TAG = "tag"

    override fun onReceive(p0: Context?, p1: Intent?) {

        try {
            if (isOnline(context)) {
                Log.d(TAG, "Online Connect Intenet ")
                networkListner.onNetworkChangeUpdates("YES")

            } else {

                Log.d(TAG, "Conectivity Failure !!! ")
                networkListner.onNetworkChangeUpdates("NO")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }

    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }
}