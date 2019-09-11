package com.bereguliak.arscheduler.core.node

import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.ux.TransformableNode
import com.google.ar.sceneform.ux.TransformationSystem

class LocalRotationTransformableNode(transformationSystem: TransformationSystem) :
    TransformableNode(transformationSystem) {

    //region Description
    override fun onUpdate(frameTime: FrameTime?) {
        super.onUpdate(frameTime)
        scene?.camera?.localRotation?.let {
            worldRotation = it
        }
    }
    //endregion
}