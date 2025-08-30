package com.ms.xmlviewactivity

import android.Manifest
import android.app.Fragment
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.media.AudioManager
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ms.gfg_dsa.R
import com.ms.gfg_dsa.databinding.XMLViewLayoutBinding
import com.ms.hilt.UserRepo
import com.ms.utils.loge
import com.ms.workmanager.NoNetworkWorker
import com.ms.xmlviewactivity.fragments.AFragment
import com.ms.xmlviewactivity.fragments.BFragment
import com.ms.xmlviewactivity.fragments.CFragment
import com.ms.xmlviewactivity.fragments.DFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

import androidx.lifecycle.LiveData
import androidx.work.NetworkType
import androidx.work.WorkInfo
import kotlinx.coroutines.channels.Channel

@AndroidEntryPoint
class XMLViewActivity : AppCompatActivity() {
    private val TAG = "XMLViewActivity======"
    private var binding: XMLViewLayoutBinding? = null

    private var uri: Uri? = null

    private val viewModel: XMLViewModel by lazy {
        ViewModelProvider(this)[XMLViewModel::class.java]
    }

    @Inject
    lateinit var userRepo: UserRepo

    val flowProducer: Flow<Int> = flow<Int> {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach {
            delay(500)
            Log.e(TAG, "flowProducer: $it")
            emit(it)
        }
    }.catch {

    }

    private val channel: Channel<Int> = Channel<Int>()

    fun producer() {
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
        }
    }

    fun consumer() {
        CoroutineScope(Dispatchers.Main).launch {
            val receive = channel.receive()
            Log.e(TAG, "receive: " + receive)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printMethodName("onCreate")

        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_xmlview)

        producer()
        consumer()

//        setContentView(R.layout.activity_xmlview)

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        userRepo.saveInDb("ms", "developer")

        uri = intent.data

        if (uri != null) {
            binding?.tvMessage?.text = uri.toString()
        }

        binding?.tvMessage?.setOnClickListener {
//            startActivity(Intent(this, ParameterizedConstructorActivity::class.java))
            return@setOnClickListener
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_NOTIFICATION_POLICY), 111)

                Log.e(TAG, "onCreate:ask  1111")
            } else {
                Log.e(TAG, "onCreate granted: 22222")
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                if (notificationManager.isNotificationPolicyAccessGranted) {
                    Log.e(TAG, "noti 33333: ")
                    notificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_ALL) // Set DND mode off
                } else {
                    val intent = Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS)
//                    startActivity(intent)
                    Log.e(TAG, "nnoti 4444: ")
                    // Handle lack of permission or show a message
                }
                setRingVolume(this, 6)
                // Permission already granted, proceed with changing DND settings
            }
//            setRingVolume(this, 6)
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

//        CoroutineScope(Dispatchers.IO).launch {
////            val first = flowProducer.first()
//            flowProducer
////                .buffer(5)
//                .collect { it: Int ->
//                    delay(1000)
//                    Log.e(TAG, "collect: " + it)
//                }
//        }

//        CoroutineScope(Dispatchers.IO).launch {
//            delay(4000)
//            flowProducer.map {
//                it * 2
//            }.filter {
//                it < 8
//            }.collect { it: Int ->
//                Log.e(TAG, "collect====: " + it)
//            }
//        }

//        enqueueWorkIfNotExists(this)
    }

    private fun setUpWorkManagerConstrains() {
        // Define the constraints for no network connectivity
        val constraints = Constraints.Builder().setRequiredNetworkType(androidx.work.NetworkType.NOT_REQUIRED) // No network required
            .build()

        // Create a work request that will execute after 15 minutes
        val workRequest = PeriodicWorkRequestBuilder<NoNetworkWorker>(15, TimeUnit.MINUTES).setConstraints(constraints)
            .addTag("NoNetworkWorkerTag") // Add tag to identify and cancel the work
            .setInitialDelay(15, TimeUnit.MINUTES) // Wait for 15 minutes before executing
            .build()

        // Enqueue the work
        WorkManager.getInstance(this).enqueue(workRequest)

    }

    fun setRingVolume(context: Context, volumeLevel: Int) {
        val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        // Get the maximum volume level for the ring stream
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
        Log.e(TAG, "maxVolume:  " + maxVolume)
        // Set the volume to the desired level, ensuring it does not exceed the maximum
        audioManager.setStreamVolume(/* streamType = */ AudioManager.STREAM_RING,/* index = */ maxVolume / 3, // Limit the volume between 0 and max
            /* flags = */ 0 // Flags (use 0 for no additional behaviors)
        )
    }

    fun checkIfWorkExists(context: Context, tag: String, onWorkExists: () -> Unit, onNoWorkExists: () -> Unit) {
        // Get the work with the given tag
        val workInfos: LiveData<List<WorkInfo>> = WorkManager.getInstance(context).getWorkInfosByTagLiveData(tag)

        // Observe the work status
        workInfos.observeForever { workInfoList ->
            if (workInfoList.isNullOrEmpty()) {
                // No work with this tag exists
                onNoWorkExists()
            } else {
                // Work with this tag exists (could be running, queued, or finished)
                val workInfo = workInfoList.firstOrNull()
                if ((workInfo != null) && ((workInfo.state == WorkInfo.State.ENQUEUED) || (workInfo.state == WorkInfo.State.RUNNING))) {
                    // Work is either pending or running
                    onWorkExists()
                } else {
                    // No running or enqueued work, allow scheduling new work
                    onNoWorkExists()
                }
            }
        }
    }

    // Function to start work if no work with the tag exists
    fun enqueueWorkIfNotExists(context: Context) {
        val workTag = "NoNetworkWorkerTag"

        // Check if work exists before starting new one
        checkIfWorkExists(context, workTag, onWorkExists = {
            // Work already exists, do nothing
            Log.d("WorkManager", "Work with tag $workTag is already running or enqueued.")
        }, onNoWorkExists = {
            // No work exists, so start a new one
            Log.d("WorkManager", "No existing work found. Enqueuing new work.")

            // Define constraints: Work only when there is no network
            val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.NOT_REQUIRED) // No network required
                .build()

            // Create a PeriodicWorkRequest that runs every 15 minutes
            val periodicWorkRequest = PeriodicWorkRequestBuilder<NoNetworkWorker>(15, TimeUnit.MINUTES).setConstraints(constraints)
                .addTag(workTag) // Add tag to identify the work
                .build()

            // Enqueue the PeriodicWorkRequest
            WorkManager.getInstance(context).enqueue(periodicWorkRequest)
        })
    }


    private fun inflateFragmentA() {
        supportFragmentManager.beginTransaction()
            .add(/* containerViewId = */ binding?.fragmentContainerA?.id!!,/* fragment = */ AFragment(),/* tag = */ AFragment::class.java.simpleName
            ).addToBackStack(AFragment::class.java.simpleName).commit()
    }

    private fun inflateFragmentB() {
        supportFragmentManager.beginTransaction()
            .add(/* containerViewId = */ binding?.fragmentContainerB?.id!!,/* fragment = */ BFragment(),/* tag = */ BFragment::class.java.simpleName
            ).addToBackStack(BFragment::class.java.simpleName).commit()
    }

    private fun inflateFragmentC() {
        supportFragmentManager.beginTransaction()
            .add(/* containerViewId = */ binding?.fragmentContainerB?.id!!,/* fragment = */ CFragment(),/* tag = */ CFragment::class.java.simpleName
            ).addToBackStack(CFragment::class.java.simpleName).commit()
    }

    private fun inflateFragmentD() {
        supportFragmentManager.beginTransaction()
            .add(/* containerViewId = */ binding?.fragmentContainerB?.id!!,/* fragment = */ DFragment(),/* tag = */ DFragment::class.java.simpleName
            ).addToBackStack(DFragment::class.java.simpleName).commit()
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