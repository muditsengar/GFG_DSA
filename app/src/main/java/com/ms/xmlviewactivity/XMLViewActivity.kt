package com.ms.xmlviewactivity

import android.app.Fragment
import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.ms.gfg_dsa.R
import com.ms.gfg_dsa.databinding.XMLViewLayoutBinding
import com.ms.hilt.UserRepo
import com.ms.utils.loge
import com.ms.xmlviewactivity.fragments.AFragment
import com.ms.xmlviewactivity.fragments.BFragment
import com.ms.xmlviewactivity.fragments.CFragment
import com.ms.xmlviewactivity.fragments.DFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class XMLViewActivity : AppCompatActivity() {
    private val TAG = "XMLViewActivity======"
    private var binding: XMLViewLayoutBinding? = null

    private var uri: Uri? = null

    @Inject
    lateinit var userRepo: UserRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printMethodName("onCreate")

        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_xmlview)

//        setContentView(R.layout.activity_xmlview)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userRepo.saveInDb("ms", "developer")

        uri = intent.data

        if (uri != null) {
            binding?.tvMessage?.text = uri.toString()
        }

        binding?.fragmentContainerA?.setOnClickListener {
            inflateFragmentA()
        }
        binding?.fragmentContainerB?.setOnClickListener {
            inflateFragmentB()
        }
        binding?.fragmentContainerC?.setOnClickListener {
            inflateFragmentC()
        }
        binding?.fragmentContainerD?.setOnClickListener {
            inflateFragmentD()
        }

    }

    private fun inflateFragmentA() {
        supportFragmentManager.beginTransaction()
            .add(
                /* containerViewId = */ binding?.fragmentContainerA?.id!!,
                /* fragment = */ AFragment(),
                /* tag = */ AFragment::class.java.simpleName
            )
            .addToBackStack(AFragment::class.java.simpleName)
            .commit()
    }

    private fun inflateFragmentB() {
        supportFragmentManager.beginTransaction()
            .add(
                /* containerViewId = */ binding?.fragmentContainerB?.id!!,
                /* fragment = */ BFragment(),
                /* tag = */ BFragment::class.java.simpleName
            )
            .addToBackStack(BFragment::class.java.simpleName)
            .commit()
    }

    private fun inflateFragmentC() {
        supportFragmentManager.beginTransaction()
            .add(
                /* containerViewId = */ binding?.fragmentContainerB?.id!!,
                /* fragment = */ CFragment(),
                /* tag = */ CFragment::class.java.simpleName
            )
            .addToBackStack(CFragment::class.java.simpleName)
            .commit()
    }

    private fun inflateFragmentD() {
        supportFragmentManager.beginTransaction()
            .add(
                /* containerViewId = */ binding?.fragmentContainerB?.id!!,
                /* fragment = */ DFragment(),
                /* tag = */ DFragment::class.java.simpleName
            )
            .addToBackStack(DFragment::class.java.simpleName)
            .commit()
    }


    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        printMethodName("onAttachFragment")
    }

    override fun onRestart() {
        super.onRestart()
        printMethodName("onRestart")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        printMethodName("onRestoreInstanceState")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        printMethodName("onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        printMethodName("onDestroy")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        printMethodName("onConfigurationChanged")
    }

    override fun onPostResume() {
        super.onPostResume()
        printMethodName("onPostResume")
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

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment) {
        super.onAttachFragment(fragment)
        printMethodName("onAttachFragment")
        Log.e(TAG, "onAttachFragment: name - " + fragment.javaClass.simpleName)
    }

    override fun onPause() {
        super.onPause()
        printMethodName("onPause")
    }

    override fun onResume() {
        super.onResume()
        printMethodName("onResume")
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        printMethodName("onResumeFragments")
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        printMethodName("onCreateView")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        printMethodName("onBackPressed")
        finish()
    }

    private fun printMethodName(methodName: String) {
        loge("Transition======", methodName)
    }
}