package com.bereguliak.arscheduler.core.node

import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Node

class LocalRotationNode : Node() {

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