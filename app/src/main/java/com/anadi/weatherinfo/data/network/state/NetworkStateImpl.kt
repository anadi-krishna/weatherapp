package com.anadi.weatherinfo.data.network.state

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import com.anadi.weatherinfo.data.network.state.NetworkState

class NetworkStateImpl : NetworkState {
    override val isConnected: MutableLiveData<Boolean> = MutableLiveData(false)
    override var network: Network? = null
    override var linkProperties: LinkProperties? = null
    override var networkCapabilities: NetworkCapabilities? = null
}