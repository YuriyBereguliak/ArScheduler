package com.bereguliak.arscheduler.ui.fragments.ar

import android.content.Context
import com.bereguliak.arscheduler.ui.ar.AugmentedImageNode
import com.google.ar.core.AugmentedImage
import com.google.ar.core.Config
import com.google.ar.core.Session

interface ArScheduleContract {
    interface View {
        fun createNode(image: AugmentedImage)
    }

    interface Presenter {
        fun addNode(image: AugmentedImage, node: AugmentedImageNode)

        fun trackImage(image: AugmentedImage)

        fun removeImage(image: AugmentedImage)
    }
}