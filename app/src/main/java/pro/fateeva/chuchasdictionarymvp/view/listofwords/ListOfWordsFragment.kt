package pro.fateeva.chuchasdictionarymvp.view.listofwords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import pro.fateeva.chuchasdictionarymvp.model.AppState
import pro.fateeva.chuchasdictionarymvp.R
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentListOfWordsBinding
import pro.fateeva.chuchasdictionarymvp.databinding.ItemWordBinding
import pro.fateeva.chuchasdictionarymvp.extensions.showLoader
import pro.fateeva.chuchasdictionarymvp.model.Word
import pro.fateeva.chuchasdictionarymvp.view.RecyclerAdapter
import pro.fateeva.chuchasdictionarymvp.view.SearchDialogFragment

class ListOfWordsFragment : Fragment() {

    private var _binding: FragmentListOfWordsBinding? = null
    val binding: FragmentListOfWordsBinding
        get() = _binding!!

    private val viewModel: ListOfWordsViewModel by lazy {
        ViewModelProvider(this).get(ListOfWordsViewModel::class.java)
    }

    private val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

    val adapter = RecyclerAdapter<Word>(
        emptyList(),
        R.layout.item_word
    ) { word, _ ->
        ItemWordBinding.bind(this).apply {
            headerTextview.text = word.text
            descriptionTextview.text = word.meanings?.first()?.translation?.translation
        }
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

        setFragmentResultListener(SearchDialogFragment.fragmentResult) { _, bundle ->
            viewModel.getData(bundle.getString(SearchDialogFragment.wordResultKey) ?: "")
        }

        viewModel.wordLiveData.observe(viewLifecycleOwner) {
            renderData(it)
        }

        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
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

    private fun renderData(appState: AppState) {
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
        showLoader(false)
        binding.errorTextview.visibility = View.VISIBLE
        binding.errorTextview.text = error
    }

    private fun showViewSuccess() {
        binding.recyclerview.visibility = View.VISIBLE
        binding.errorTextview.visibility = View.GONE
        showLoader(false)
    }

    private fun showViewLoading() {
        binding.recyclerview.visibility = View.GONE
        showLoader(true)
        binding.errorTextview.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}