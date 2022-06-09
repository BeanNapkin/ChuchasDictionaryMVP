package pro.fateeva.chuchasdictionarymvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pro.fateeva.chuchasdictionarymvp.AppState
import pro.fateeva.chuchasdictionarymvp.R
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentListOfWordsBinding
import pro.fateeva.chuchasdictionarymvp.databinding.ItemWordBinding
import pro.fateeva.chuchasdictionarymvp.model.Word
import pro.fateeva.chuchasdictionarymvp.presenter.Presenter
import pro.fateeva.chuchasdictionarymvp.presenter.PresenterImpl

class ListOfWordsFragment : BaseFragment<AppState>() {

    private lateinit var _binding: FragmentListOfWordsBinding
    val binding: FragmentListOfWordsBinding
        get() = _binding

//    var translation: Translation = Translation("Медведь")
//    var translation2: Translation = Translation("Выносить")
//    var meanings: List<Translation> = listOf(translation, translation2)
//    var words: List<Word> = listOf(Word("Bear", meanings))

    private val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

    val adapter = RecyclerAdapter<Word>(
        emptyList(),
        R.layout.item_word
    ) { word, _ ->
        ItemWordBinding.bind(this).headerTextview.text = word.text
        ItemWordBinding.bind(this).descriptionTextview.text = word.meanings?.first()?.translation?.translation
    }

    override fun createPresenter(): Presenter<AppState, FragmentView> {
        return PresenterImpl()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListOfWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object :
                SearchDialogFragment.OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    presenter.getData(searchWord)
                }
            })
            searchDialogFragment.show(
                requireActivity().supportFragmentManager,
                BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
            )
        }

        binding.recyclerview.adapter = adapter
    }

    companion object {
        fun newInstance() = ListOfWordsFragment()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val word = appState.data
                if (word == null || word.isEmpty()) {
                    showViewError("Empty response")
                } else {
                    adapter.itemList = word
                    showViewSuccess()
                }
            }
            is AppState.Loading -> {
                showViewLoading()
            }
            is AppState.Error -> {
                showViewError(appState.error.message.toString())
            }
        }
    }

    private fun showViewError(error: String) {
        binding.recyclerview.visibility = View.GONE
        binding.progressBarHorizontal.visibility = View.GONE
        binding.errorTextview.visibility = View.VISIBLE
        binding.errorTextview.text = error
    }

    private fun showViewSuccess() {
        binding.recyclerview.visibility = View.VISIBLE
        binding.errorTextview.visibility = View.GONE
        binding.progressBarHorizontal.visibility = View.GONE
    }

    private fun showViewLoading() {
        binding.recyclerview.visibility = View.GONE
        binding.progressBarHorizontal.visibility = View.VISIBLE
        binding.errorTextview.visibility = View.GONE
    }

}