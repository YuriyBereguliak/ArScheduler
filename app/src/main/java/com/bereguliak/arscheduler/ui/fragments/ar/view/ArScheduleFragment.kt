package com.bereguliak.arscheduler.ui.fragments.ar.view

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bereguliak.arscheduler.ui.ar.AugmentedImageNode
import com.bereguliak.arscheduler.ui.fragments.ar.ArScheduleContract
import com.bereguliak.arscheduler.ui.fragments.ar.presenter.ArSchedulePresenter
import com.google.ar.core.*
import com.google.ar.sceneform.ux.ArFragment
import java.io.IOException

class ArScheduleFragment : ArFragment(), ArScheduleContract.View {


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
//                val open = assetManager.open("sample_database.imgdb")
//                val database = AugmentedImageDatabase.deserialize(session, open)

                context?.assets?.let { manager ->
                    val augmentedImageDatabase = AugmentedImageDatabase(session)

//                    augmentedImageDatabase.add("icons/urban_room.png", manager)

//                    augmentedImageDatabase.add("icons/algorithm_room.png", manager)
                    augmentedImageDatabase.add("icons/genius_room.png", manager)
                    augmentedImageDatabase.add("icons/travel_room.png", manager)
//                    augmentedImageDatabase.add("icons/green_room.png", manager)

                    config.augmentedImageDatabase = augmentedImageDatabase
                }
            } catch (e: IOException) {
                Log.e(javaClass.name, e.message)
            }
        }
        return config
    }
    //endregion

    //region ArScheduleContract.View
    override fun createNode(image: AugmentedImage) {
        AugmentedImageNode(context)
            .apply {
                this.image = image
            }.also { node ->
                presenter.addNode(image, node)
                arSceneView.scene.addChild(node)
            }
    }
    //endregion

    //region Utility API
    private fun onFrameUpdated() {
        arSceneView.arFrame?.takeIf { it.camera.trackingState != TrackingState.TRACKING }?.let { frame ->
            frame.getUpdatedTrackables(AugmentedImage::class.java).forEach { image ->
                when (image.trackingState) {
                    TrackingState.TRACKING -> presenter.trackImage(image)
                    TrackingState.STOPPED -> presenter.removeImage(image)
                }
            }
        }
    }

    private fun AugmentedImageDatabase.add(resource: String, assetManager: AssetManager) {
        addImage(resource, loadAugmentedImageBitmap(assetManager, resource))
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