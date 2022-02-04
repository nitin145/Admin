package com.provider.citoCabs.ui.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.provider.citoCabs.R
import com.provider.citoCabs.base.Prefs


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.apply {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        Handler().postDelayed({
            startActivity(
                Intent(
                    this, if (Prefs.init().isLogIn == "1") {
                        MainActivity::class.java
                    } else {
                        AuthHandlerActivity::class.java
                    }
                )
            )

            finish()

        }, 1000)
    }


}