package cz.levinzonr.spotistats.presentation.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import cz.levinzonr.spotistats.presentation.R
import cz.levinzonr.spotistats.presentation.base.BaseActivity
import cz.levinzonr.spotistats.presentation.extensions.toggleDarkMode
import kotlinx.android.synthetic.main.activity_main.*
import net.hockeyapp.android.UpdateManager

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.setupWithNavController(findNavController(R.id.nav_host_fragment))
        darkModeBtn.setOnClickListener {
            toggleDarkMode()
        }
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, MainActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
    }


}
