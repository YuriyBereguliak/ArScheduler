package com.bereguliak.arscheduler.ui.fragments.mycalendar.view

import android.os.Bundle
import android.view.*
import android.widget.FrameLayout
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.LocalRotationTransformableNode
import com.bereguliak.arscheduler.domain.calendar.location.CalendarOrchestrator
import com.bereguliak.arscheduler.model.CalendarLocation
import com.bereguliak.arscheduler.ui.fragments.details.view.CalendarEventsView
import com.bereguliak.arscheduler.ui.fragments.mycalendar.MyCalendarContract
import com.google.ar.core.Frame
import com.google.ar.core.Plane
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.DpToMetersViewSizer
import com.google.ar.sceneform.rendering.ViewRenderable
import com.google.ar.sceneform.ux.ArFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MyCalendarArFragment @Inject constructor() : ArFragment(), MyCalendarContract.View {

    @Inject
    internal lateinit var presenter: MyCalendarContract.Presenter

    @Inject
    internal lateinit var calendarOrchestrator: CalendarOrchestrator

    private var isScheduleAdded = false
    private var eventsView: CalendarEventsView? = null
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

    //region ArFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)

        val view = super.onCreateView(inflater, container, savedInstanceState)
        initViewRenderable()

        arSceneView.scene.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }

        return view
    }

    override fun onStop() {
        super.onStop()
        eventsView?.release()
        presenter.unSubscribe()
    }
    //endregion

    //region Utility API
    private fun initViewRenderable() {
        ViewRenderable.builder()
                .setView(context, R.layout.item_ar_container)
                .setSizer(DpToMetersViewSizer(1500))
                .build()
                .thenAccept {
                    viewRenderable = it
                    viewRenderable.view.findViewById<FrameLayout>(R.id.fragmentContainer).prepareEventsView()
                }
    }

    private fun FrameLayout.prepareEventsView() {
        eventsView = CalendarEventsView(context!!).apply {
            addOrchestrator(calendarOrchestrator)
            arguments?.let { args ->
                args.getParcelable<CalendarLocation>(ARG_CALENDAR_INFO)?.let { info ->
                    calendarInfo = info
                }
            }
        }
        addView(eventsView)
    }

    private fun onSingleTap(tap: MotionEvent) {
        arSceneView.arFrame?.let {
            if (!isScheduleAdded && tryPlaceView(tap, it)) {
                isScheduleAdded = true
            }
        }
    }

    private fun tryPlaceView(tap: MotionEvent?, frame: Frame): Boolean {
        if (tap != null && frame.camera.trackingState == TrackingState.TRACKING) {
            for (hit in frame.hitTest(tap)) {
                hit.takeIf { it.trackable is Plane }?.let { hitResult ->
                    AnchorNode(hitResult.createAnchor()).apply {
                        localPosition = Vector3(0.25f, 0.25f, 0.25f)
                        setParent(arSceneView.scene)
                        addChild(createViewRenderableNode())
                        eventsView?.loadData()
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun createViewRenderableNode() = LocalRotationTransformableNode(transformationSystem).apply {
        renderable = viewRenderable
    }
    //endregion

    //region Utility structure
    companion object {

        private const val ARG_CALENDAR_INFO =
                "com.bereguliak.arscheduler.ui.fragments.mycalendar.view.CALENDAR_INFO"

        fun newInstance(calendar: CalendarLocation): MyCalendarArFragment {
            return MyCalendarArFragment().apply {
                arguments = Bundle(1).apply {
                    putParcelable(ARG_CALENDAR_INFO, calendar)
                }
            }
        }
    }
    //endregion
}