package com.ms.cheezycode.playlists.architecturecomponentandjetpack.lifecycleawarecomponents

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ms.gfg_dsa.R

class LifecycleObserverFragment : Fragment() {

    companion object {
        fun newInstance() = LifecycleObserverFragment()
    }

    private val viewModel: LifecycleObserverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_lifecycle_observer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(Observer())
    }
}