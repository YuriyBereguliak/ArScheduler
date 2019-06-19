package com.bereguliak.arscheduler.ui.fragments.ar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bereguliak.arscheduler.ui.fragments.ar.ArScheduleContract
import com.bereguliak.arscheduler.ui.fragments.ar.presenter.ArSchedulePresenter
import com.google.ar.sceneform.ux.ArFragment

class ArScheduleFragment : ArFragment(), ArScheduleContract.View {

    private val presenter: ArScheduleContract.Presenter by lazy { ArSchedulePresenter(this) }

    //region BaseArFragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false

        return view
    }
    //endregion

    //region Utility API
    companion object {
        @JvmStatic
        fun newInstance() = ArScheduleFragment()
    }
    //endregion
}