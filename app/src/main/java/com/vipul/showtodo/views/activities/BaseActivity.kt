package com.vipul.showtodo.views.activities

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.vipul.showtodo.R


open class BaseActivity : AppCompatActivity() {
    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /*
    * set up the progress bar
    * context:- context of the activity in which progress bar is to be shown
    * */
    fun createProgressDialog(context: Context) {
        progressBar = (context as Activity).findViewById(R.id.llProgressBar)
    }

    /*
    * setup progressbar if nit initialize & make it visible
    * */
    fun showProgressDialog() {
        if (!::progressBar.isInitialized) {
            createProgressDialog(this)
        }
        progressBar.visibility = View.VISIBLE
    }

    /*
    * hide progree bar if it is showing
    * */
    fun hideProgressDialog() {
        if (::progressBar.isInitialized && progressBar.visibility == View.VISIBLE)
            progressBar.visibility = View.GONE
    }
}