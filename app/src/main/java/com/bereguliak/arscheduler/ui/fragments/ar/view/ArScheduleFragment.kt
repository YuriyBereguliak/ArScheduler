package com.bereguliak.arscheduler.ui.fragments.ar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bereguliak.arscheduler.ui.ar.AugmentedImageNode
import com.bereguliak.arscheduler.ui.fragments.ar.ArScheduleContract
import com.bereguliak.arscheduler.ui.fragments.ar.presenter.ArSchedulePresenter
import com.google.ar.core.*
import com.google.ar.sceneform.ux.ArFragment
import java.io.IOException
import java.util.*

class ArScheduleFragment : ArFragment(), ArScheduleContract.View {

    private val augmentedImageMap = HashMap<AugmentedImage, AugmentedImageNode>()

    private val presenter: ArScheduleContract.Presenter by lazy { ArSchedulePresenter(this) }

    //region BaseArFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        arSceneView.scene.addOnUpdateListener { onFrameUpdated() }

        return view
    }

    override fun getSessionConfiguration(session: Session?): Config {
        val config = super.getSessionConfiguration(session)
        context?.assets?.let { assetManager ->
            try {
                val open = assetManager.open("sample_database.imgdb")
                val database = AugmentedImageDatabase.deserialize(session, open)
                config.augmentedImageDatabase = database
            } catch (e: IOException) {

            }
        }
        return config
    }
    //endregion

    //region Utility API
    private fun onFrameUpdated() {
        arSceneView.arFrame?.takeIf { it.camera.trackingState != TrackingState.TRACKING }?.let { frame ->
            frame.getUpdatedTrackables(AugmentedImage::class.java).forEach { image ->
                when (image.trackingState) {
                    TrackingState.PAUSED -> {
                        Toast.makeText(context, "Detecting :: ${image.index}", Toast.LENGTH_SHORT).show()
                    }
                    TrackingState.TRACKING -> {
                        augmentedImageMap.containsKey(image).takeIf { !it }?.let {
                            AugmentedImageNode(context)
                                .apply {
                                    this.image = image
                                }.also { node ->
                                    augmentedImageMap[image] = node
                                    arSceneView.scene.addChild(node)
                                }
                        }
                    }
                    TrackingState.STOPPED -> {
                        augmentedImageMap.remove(image)
                    }
                }
            }
        }
    }
    //endregion

    //region Utility API
    companion object {
        @JvmStatic
        fun newInstance() = ArScheduleFragment()
    }
    //endregion
}