package com.bereguliak.arscheduler.core.node

import android.content.Context
import android.widget.FrameLayout
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.ui.fragments.details.view.CalendarEventsView
import com.google.ar.core.AugmentedImage
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.DpToMetersViewSizer
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.TransformationSystem
import java.util.concurrent.CompletableFuture

class AugmentedScheduleNode(context: Context,
                            private val transformationSystem: TransformationSystem) : AnchorNode() {

    private val viewCompletableFuture: CompletableFuture<ViewRenderable> = ViewRenderable.builder()
            .setView(context, R.layout.item_ar_container)
            .setSizer(DpToMetersViewSizer(1500))
            .build()

    private var viewRenderable: ViewRenderable? = null

    private var eventsView: CalendarEventsView? = null

    //region Description
    fun setSource(image: AugmentedImage) {
        if (viewRenderable == null) {
            viewCompletableFuture.thenAccept { viewRenderable ->
                this.viewRenderable = viewRenderable
                viewRenderable?.view?.findViewById<FrameLayout>(R.id.fragmentContainer)?.prepareEventsView()
                setSource(image)
            }
            return
        }

        // Set the anchor based on the center of the image.
        anchor = image.createAnchor(image.centerPose)

        val localPosition = Vector3()

        // Lower left corner.
//        localPosition.set(-0.5f * image.extentX, 0.0f, 0.5f * image.extentZ)

        LocalRotationTransformableNode(transformationSystem).apply {
            renderable = viewRenderable
//            setLocalPosition(localPosition)
            eventsView?.loadCalendarEventsByName(image.name)
        }.also { node ->
            addChild(node)
        }
    }
    //endregion

    //region Utility API
    private fun FrameLayout.prepareEventsView() {
        eventsView = CalendarEventsView(context).apply {

        }
        addView(eventsView)
    }
    //endregion
}