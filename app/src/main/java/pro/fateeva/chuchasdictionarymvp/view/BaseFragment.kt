package pro.fateeva.chuchasdictionarymvp.view

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.presenter.Presenter

abstract class BaseFragment<A : AppState> : Fragment(), FragmentView {
    protected lateinit var presenter: Presenter<A, FragmentView>

    protected abstract fun createPresenter(): Presenter<A, FragmentView>
    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachFragment(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachFragment(this)
    }
}