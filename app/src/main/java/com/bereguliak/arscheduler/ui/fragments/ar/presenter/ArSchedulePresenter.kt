package com.bereguliak.arscheduler.ui.fragments.ar.presenter

import android.content.Context
import com.bereguliak.arscheduler.ui.fragments.ar.ArScheduleContract
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import java.io.IOException

class ArSchedulePresenter(private val view: ArScheduleContract.View) : ArScheduleContract.Presenter {

    private val IMAGE_DATABASE = "sample_database.imgdb"

    //region ArScheduleContract.Presenter
    override fun initDatabase(context: Context, config: Config, session: Session) {
        context.assets?.let { assetManager ->
            try {
                val database = AugmentedImageDatabase.deserialize(session, assetManager.open(IMAGE_DATABASE))
                config.augmentedImageDatabase = database
            } catch (e: IOException) {

            }
        }
    }
    //endregion
}