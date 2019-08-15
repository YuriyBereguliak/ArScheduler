package com.bereguliak.arscheduler.ui.fragments.ar.presenter

import com.bereguliak.arscheduler.ui.ar.AugmentedImageNode
import com.bereguliak.arscheduler.ui.fragments.ar.ArScheduleContract
import com.google.ar.core.AugmentedImage
import java.util.*

class ArSchedulePresenter(private val view: ArScheduleContract.View) : ArScheduleContract.Presenter {

    private val augmentedImageMap = HashMap<AugmentedImage, AugmentedImageNode>()

    //region ArScheduleContract.Presenter
    override fun trackImage(image: AugmentedImage) {
        augmentedImageMap.containsKey(image).takeIf { !it }?.let {
            view.createNode(image)
        }
    }

    override fun addNode(image: AugmentedImage, node: AugmentedImageNode) {
        augmentedImageMap[image] = node
    }

    override fun removeImage(image: AugmentedImage) {
        augmentedImageMap.remove(image)
    }
    //endregion
}