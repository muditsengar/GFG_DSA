package com.ms.cheezycode.playlists.architecturecomponentandjetpack.lifecycleawarecomponents

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class Observer : LifecycleEventObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun callOnCreate() {

    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

    }


}