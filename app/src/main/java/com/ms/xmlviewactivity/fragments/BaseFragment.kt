package com.ms.xmlviewactivity.fragments

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ms.utils.loge

open class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        printMethodName("onActivityCreated")
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        printMethodName("onAttach")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        printMethodName("onAttach")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        printMethodName("onAttachFragment")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        printMethodName("onConfigurationChanged")
    }

    override fun onDestroy() {
        super.onDestroy()
        printMethodName("onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        printMethodName("onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        printMethodName("onDetach")
    }

    override fun onPause() {
        super.onPause()
        printMethodName("onPause")
    }

    override fun onResume() {
        super.onResume()
        printMethodName("onResume")
    }

    override fun onStart() {
        super.onStart()
        printMethodName("onStart")
    }

    override fun onStop() {
        super.onStop()
        printMethodName("onStop")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        printMethodName("onSaveInstanceState")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printMethodName("onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        printMethodName("onViewStateRestored")
    }



    private fun printMethodName(methodName: String) {
        loge("Transition======", methodName)
//        Log.e("======", "printMethodName:  " + methodName)
    }
}