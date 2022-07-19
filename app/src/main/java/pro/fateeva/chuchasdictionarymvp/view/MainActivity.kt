package pro.fateeva.chuchasdictionarymvp.view

import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import pro.fateeva.chuchasdictionarymvp.R
import pro.fateeva.chuchasdictionarymvp.databinding.ActivityMainBinding
import pro.fateeva.chuchasdictionarymvp.view.listofwords.ListOfWordsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 800L
                slideUp.doOnEnd { splashScreenView.remove() }
                slideUp.start()

            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListOfWordsFragment.newInstance())
            .commit()

        supportFragmentManager.setFragmentResultListener(showLoaderRequestKey, this) { _, bundle ->
            if (bundle.getBoolean(showLoaderResultKey)) {
                binding.progressBarHorizontal.visibility = View.VISIBLE
            } else {
                binding.progressBarHorizontal.visibility = View.GONE
            }
        }
    }

    companion object {
        val showLoaderRequestKey = "showLoaderRequestKey"
        val showLoaderResultKey = "showLoaderResultKey"
    }
}