package cz.levinzonr.spotistats.presentation.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import cz.levinzonr.spotistats.domain.repository.CODE_ALZA
import cz.levinzonr.spotistats.domain.repository.RepositoryException
import java.io.IOException

class ViewErrorController(val context: Context) {



    fun showErrorDialog(error: ViewError, cancelable: Boolean = true, dismissAction: (() -> Unit)? = null) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(error.title)
        builder.setMessage(error.message)
        builder.setPositiveButton("Ok") { _, _ ->
            isShowingError = false
        }
        builder.setOnDismissListener {
            isShowingError = false
            dismissAction?.invoke()
        }
        if (!isShowingError) {
            isShowingError = true
            val dialog = builder.show()
            dialog.setCancelable(cancelable)
            dialog.setCanceledOnTouchOutside(cancelable)
        }
    }

    fun showErrorSnackbar(view: View, error: ViewError, showAction: Boolean = false, dismissAction: (() -> Unit)? = null) {
        val showLength = if (showAction) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
        val snackbar = Snackbar.make(view, error.message ?: "Error", showLength)
        if (showAction) {
            snackbar.setAction("Ok") {
                isShowingError = false
                dismissAction?.invoke()
            }
        }
        if (!isShowingError) {
            isShowingError = true
            snackbar.show()
        }
    }

    companion object {
        var isShowingError = false

        fun mapThrowable(throwable: Throwable): ViewError {
            return when (throwable) {
                is IOException -> {
                    ViewError("Network error", message = "Check your internet connection")
                }
                is RepositoryException -> {
                    when (throwable.code) {
                        CODE_ALZA -> {
                            ViewError(
                                    title = "Something went wrong",
                                    message = throwable.msg
                            )
                        }
                        401, 403 -> {
                            ViewError(
                                    title = "401, 403",
                                    message = "Auth Error",
                                    code = -1
                            )
                        }
                        402, in 404..500 -> {
                            ViewError(
                                    title = "402, 404-500",
                                    message ="Auth eror",
                                    code = -1
                            )
                        }
                        in 500..600 -> {
                            ViewError(
                                    title = "402, 404-500",
                                    message ="Auth eror",
                                    code = -1
                            )
                        }
                        else -> {
                            ViewError(
                                    title = "402, 404-500",
                                    message ="Auth eror",
                                    code = -1
                            )
                        }
                    }
                }
                else -> {
                    ViewError(
                            title = "402, 404-500",
                            message ="Auth eror",
                            code = -1
                    )
                }
            }
        }
    }
}
