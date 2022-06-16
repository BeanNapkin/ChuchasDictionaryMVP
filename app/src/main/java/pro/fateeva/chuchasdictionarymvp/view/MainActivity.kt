package pro.fateeva.chuchasdictionarymvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pro.fateeva.chuchasdictionarymvp.R
import pro.fateeva.chuchasdictionarymvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ListOfWordsFragment.newInstance())
            .commit()

        supportFragmentManager.setFragmentResultListener(showLoaderRequestKey, this){ _, bundle ->
            if (bundle.getBoolean(showLoaderResultKey)){
                binding.progressBarHorizontal.visibility = View.VISIBLE
            } else {
                binding.progressBarHorizontal.visibility = View.GONE
            }
        }
    }

    companion object{
        val showLoaderRequestKey = "showLoaderRequestKey"
        val showLoaderResultKey = "showLoaderResultKey"
    }
}