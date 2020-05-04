package com.bereguliak.arscheduler.core

import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem

class LocalRotationTransformableNode(transformationSystem: TransformationSystem) :
        TransformableNode(transformationSystem) {

    //region Description
    override fun onUpdate(frameTime: FrameTime?) {
        super.onUpdate(frameTime)
        updateWorldRotationPosition()
    }
    //endregion

    //region Utility API
    private fun updateWorldRotationPosition() {
        scene?.camera?.localRotation?.let {
            worldRotation = it
        }
    }
    //endregion
}