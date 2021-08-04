package com.evgeniykim.mvpmoxyretrofitrxjavamoxy.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.R
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.data.api.models.Configuration
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.databinding.FragmentSplashBinding
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.presenters.SplashPresenter
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.presentation.views.SplashView
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.utils.gone
import com.evgeniykim.mvpmoxyretrofitrxjavamoxy.utils.visible
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter

class SplashFragment : MvpAppCompatFragment(), SplashView {

    @InjectPresenter
    lateinit var presenter: SplashPresenter

    private val HANDLER_DELAY: Long = 3000

    private var fragmentBinding: FragmentSplashBinding? = null

    private val mGoToNextFragmentHandler = Handler()
    private val mGoToNextFragmentRunnable = {
        findNavController().navigate(
            R.id.moviesFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(
                    R.id.splashFragment,
                    true
                ).build()
        )
    }

    lateinit var textMessage: TextView
    lateinit var progressLoad: ProgressBar
    lateinit var buttonRetry: Button

    init {
        textMessage.findViewById<TextView>(R.id.textViewMessage)
        progressLoad.findViewById<ProgressBar>(R.id.progressLoading)
        buttonRetry.findViewById<Button>(R.id.buttonRetry)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)
        fragmentBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSplashBinding.bind(view)
        fragmentBinding = binding
        presenter.loadConfiguration()

        binding.buttonRetry.setOnClickListener { presenter.loadConfiguration() }
    }

    override fun onResume() {
        super.onResume()
        startHandler()
    }

    override fun onPause() {
        super.onPause()
        stopHandler()
    }

    private fun startHandler() {
        mGoToNextFragmentHandler.postDelayed(mGoToNextFragmentRunnable, HANDLER_DELAY)
    }

    private fun stopHandler() {
        mGoToNextFragmentHandler.removeCallbacks(mGoToNextFragmentRunnable)
        mGoToNextFragmentHandler.removeCallbacksAndMessages(null)
    }

    override fun onConfigLoaded(configuration: Configuration) {
        Log.d(javaClass.simpleName, "onConfigLoaded...")

        if (lifecycle.currentState === Lifecycle.State.RESUMED)
            startHandler()
    }

    override fun onLoad() {
        Log.d(javaClass.simpleName, "onLoad...")
        textMessage.text = getString(R.string.loading)
        progressLoad.visible()

    }

    override fun OnError(error: Throwable) {
        Log.d(javaClass.simpleName, "onError...")
        progressLoad.gone()
        textMessage.text = error.message
        textMessage.visible()
        buttonRetry.visible()
    }
}