package com.bereguliak.arscheduler.ui.fragments.mycalendar.view

import android.os.Bundle
import android.view.*
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.ui.fragments.mycalendar.MyCalendarContract
import com.google.ar.core.Frame
import com.google.ar.core.Plane
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MyCalendarArFragment @Inject constructor() : ArFragment(), MyCalendarContract.View {

    private lateinit var viewRenderable: ViewRenderable

    private var gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            onSingleTap(e)
            return true
        }

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }
    })

    @Inject
    internal lateinit var presenter: MyCalendarContract.Presenter

    //region ArFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)

        val view = super.onCreateView(inflater, container, savedInstanceState)

        ViewRenderable.builder()
                .setView(context, R.layout.item_ar_calendar)
                .build()
                .thenAccept { viewRenderable = it }

        arSceneView.scene.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        return view
    }
    //endregion

    //region Utility API
    private fun onSingleTap(tap: MotionEvent) {
        arSceneView.arFrame?.let {
            tryPlaceView(tap, it)
        }
    }

    private fun tryPlaceView(tap: MotionEvent?, frame: Frame) {
        if (tap != null && frame.camera.trackingState == TrackingState.TRACKING) {
            for (hit in frame.hitTest(tap)) {
                hit.takeIf { it.trackable is Plane }?.let {
                    AnchorNode(it.createAnchor()).apply {
                        setParent(arSceneView.scene)
                        addChild(createViewRenderableNode())
                    }
                }
            }
        }
    }

    private fun createViewRenderableNode() = TransformableNode(transformationSystem).apply {
        renderable = viewRenderable
    }
    //endregion

    //region Utility structure
    companion object {
        @JvmStatic
        fun newInstance() = MyCalendarArFragment()
    }
    //endregion
}