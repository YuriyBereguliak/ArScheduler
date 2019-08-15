package com.bereguliak.arscheduler.ui.fragments.connection.view

import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.support.annotation.LayoutRes
import android.widget.Toast
import com.bereguliak.arscheduler.R
import com.bereguliak.arscheduler.core.ui.BaseFragment
import com.bereguliak.arscheduler.ui.fragments.connection.ConnectionContract
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_connection.*
import javax.inject.Inject

class ConnectionFragment : BaseFragment(), ConnectionContract.View {

    @Inject
    lateinit var presenter: ConnectionContract.Presenter

    //region BaseFragment
    @LayoutRes
    override fun getContentViewId() = R.layout.fragment_connection

    override fun initView() {
        AndroidSupportInjection.inject(this)

        presenter.loadUserInfo()

        fragmentConnectionGoToArButton.setOnClickListener {
            navigator.showArSchedulerScreen()
        }
    }
    //endregion

    //region Fragment
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_ACCOUNT_PICKER -> handleSelectedAccountResult(resultCode, data)
            REQUEST_AUTHORIZATION -> handleAccountAuthorization(resultCode)
        }
    }
    //endregion

    //region ConnectionContract.View
    override fun chooseAccount(credentials: GoogleAccountCredential) {
        startActivityForResult(credentials.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER)
    }

    override fun accountConnected() {
        Toast.makeText(context!!, "Account connected", Toast.LENGTH_SHORT).show()
        presenter.startDownloadDataFromCalendar()
    }

    override fun authorizationRequired(intent: Intent) {
        startActivityForResult(intent, REQUEST_AUTHORIZATION)
    }
    //endregion

    //region Utility API
    private fun handleSelectedAccountResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            data.extras?.let { extras ->
                extras.getString(AccountManager.KEY_ACCOUNT_NAME)?.let { userName ->
                    presenter.saveUserName(userName)
                    presenter.startDownloadDataFromCalendar()
                }
            }
        }
    }

    private fun handleAccountAuthorization(resultCode: Int) {
        when (resultCode) {
            Activity.RESULT_OK -> presenter.startDownloadDataFromCalendar()
            else -> presenter.prepareChooseAccount()
        }
    }
    //endregion

    //region Utility structure
    companion object {
        const val REQUEST_ACCOUNT_PICKER = 1
        const val REQUEST_AUTHORIZATION = 2

        @JvmStatic
        fun newInstance() = ConnectionFragment()
    }
    //endregion
}