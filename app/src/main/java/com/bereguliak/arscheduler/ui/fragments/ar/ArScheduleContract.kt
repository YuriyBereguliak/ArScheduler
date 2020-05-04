package com.bereguliak.arscheduler.ui.fragments.ar

import com.bereguliak.arscheduler.core.node.AugmentedScheduleNode
import com.google.ar.core.AugmentedImage

interface ArScheduleContract {
    interface View {
        fun createNode(image: AugmentedImage)
    }

    interface Presenter {
        fun addNode(image: AugmentedImage, node: AugmentedScheduleNode)

        fun trackImage(image: AugmentedImage)

        fun removeImage(image: AugmentedImage)
    }
}