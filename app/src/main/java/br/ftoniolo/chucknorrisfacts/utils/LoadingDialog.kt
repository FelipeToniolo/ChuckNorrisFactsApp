package br.ftoniolo.chucknorrisfacts.utils

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.Button
import br.ftoniolo.chucknorrisfacts.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoadingDialog(private var activity: Activity) {
    var dialog: Dialog? = null

    fun showDialogLoading() {
        dialog = Dialog(activity)
        dialog?.let { dialog ->
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.item_dialog_loading)
            dialog.show()
        }
    }

    fun hideDialogLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            dialog?.cancel()
        }
    }
}