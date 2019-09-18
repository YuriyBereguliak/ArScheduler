package com.bereguliak.arscheduler.ui.fragments.ar.view

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bereguliak.arscheduler.core.node.AugmentedScheduleNode
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.ui.fragments.ar.ArScheduleContract
import com.bereguliak.arscheduler.utilities.L
import com.google.ar.core.*
import com.google.ar.sceneform.ux.ArFragment
import dagger.android.support.AndroidSupportInjection
import java.io.IOException
import javax.inject.Inject

class ArScheduleFragment : ArFragment(), ArScheduleContract.View {

    @Inject
    internal lateinit var calendarOrchestrator: CalendarOrchestrator

    @Inject
    internal lateinit var presenter: ArScheduleContract.Presenter

    //region BaseArFragment
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)

        val view = super.onCreateView(inflater, container, savedInstanceState)

        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        arSceneView.scene.addOnUpdateListener { onFrameUpdated() }

        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        try {

            context?.assets?.let { manager ->
                val augmentedImageDatabase = AugmentedImageDatabase(session)

                augmentedImageDatabase.add("Genius Room", "icons/genius.png", manager)
                augmentedImageDatabase.add("Urban Room", "icons/urban.png", manager)
                augmentedImageDatabase.add("AcademyX Room", "icons/academyX.png", manager)
                augmentedImageDatabase.add("Algorithm Room", "icons/algorithm.png", manager)
                augmentedImageDatabase.add("Jazz Room", "icons/jazz.png", manager)
                augmentedImageDatabase.add("Travel Room", "icons/travel.png", manager)
                augmentedImageDatabase.add("Green Room", "icons/green.png", manager)

                config.augmentedImageDatabase = augmentedImageDatabase
            }
        } catch (e: IOException) {
            Log.e(javaClass.name, e.message)
        }
        return config
    }
    //endregion

    //region ArScheduleContract.View
    override fun createNode(image: AugmentedImage) {
        context?.let {
            AugmentedScheduleNode(it).apply {
                setSource(image, calendarOrchestrator)
            }.also { node ->
                presenter.addNode(image, node)
                arSceneView.scene.addChild(node)
            }
        }
    }
    //endregion

    //region Utility API
    private fun onFrameUpdated() {
        arSceneView.arFrame?.takeIf { it.camera.trackingState == TrackingState.TRACKING }
                ?.let { frame ->
                    frame.getUpdatedTrackables(AugmentedImage::class.java).forEach { image ->
                        when (image.trackingState) {
                            TrackingState.TRACKING -> presenter.trackImage(image)
                            TrackingState.STOPPED -> presenter.removeImage(image)
                            else -> L.e("Tracking state not supported")
                        }
                    }
                }
    }

    private fun AugmentedImageDatabase.add(name: String, resource: String, assetManager: AssetManager) {
        addImage(name, loadAugmentedImageBitmap(assetManager, resource))
    }

    private fun loadAugmentedImageBitmap(assetManager: AssetManager, name: String): Bitmap? {
        try {
            assetManager.open(name).use { stream -> return BitmapFactory.decodeStream(stream) }
        } catch (e: IOException) {
            Log.e(javaClass.name, "IO exception loading augmented image bitmap.", e)
        }
        return null
    }
    //endregion

    //region Utility structure
    companion object {
        @JvmStatic
        fun newInstance() = ArScheduleFragment()
    }
    //endregion
}