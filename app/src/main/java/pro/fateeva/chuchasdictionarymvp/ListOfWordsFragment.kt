package pro.fateeva.chuchasdictionarymvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pro.fateeva.chuchasdictionarymvp.databinding.ActivityMainBinding
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentListOfWordsBinding
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentSearchDialogBinding
import pro.fateeva.chuchasdictionarymvp.databinding.ItemWordBinding

class ListOfWordsFragment : Fragment() {
    private lateinit var _binding: FragmentListOfWordsBinding
    val binding: FragmentListOfWordsBinding
        get() = _binding

    var translation : Translation = Translation("Медведь")
    var translation2 : Translation = Translation("Выносить")
    var meanings: List<Translation> = listOf(translation, translation2)
    var words: List<Word> = listOf(Word("Bear", meanings))

    private val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

    val adapter = RecyclerAdapter<Word>(
        emptyList(),
        R.layout.item_word
    ) { word, _ ->
        ItemWordBinding.bind(this).headerTextview.text = word.text
        ItemWordBinding.bind(this).descriptionTextview.text = word.meanings.toString()
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
            searchDialogFragment.show(requireActivity().supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

        binding.recyclerview.adapter = adapter
        adapter.itemList = words
    }

    companion object {
        fun newInstance() = ListOfWordsFragment()
    }
}