package pro.fateeva.chuchasdictionarymvp.extensions

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import pro.fateeva.chuchasdictionarymvp.view.MainActivity

fun Fragment.showLoader(show: Boolean) {
    setFragmentResult(
        MainActivity.showLoaderRequestKey,
        bundleOf(MainActivity.showLoaderResultKey to show)
    )
}