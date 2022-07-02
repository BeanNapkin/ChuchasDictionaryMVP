package pro.fateeva.chuchasdictionarymvp.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import pro.fateeva.chuchasdictionarymvp.R
import pro.fateeva.chuchasdictionarymvp.room.WordEntity
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentHistoryBinding
import pro.fateeva.chuchasdictionarymvp.databinding.ItemWordBinding
import pro.fateeva.chuchasdictionarymvp.view.RecyclerAdapter
import pro.fateeva.chuchasdictionarymvp.view.WordDetailsFragment

class HistoryFragment : DialogFragment() {

    private var _binding: FragmentHistoryBinding? = null
    val binding: FragmentHistoryBinding
        get() = _binding!!

    private val viewModel: HistoryViewModel by stateViewModel()

    val adapter = RecyclerAdapter<WordEntity>(
        emptyList(),
        R.layout.item_word
    ) { word, _ ->
        ItemWordBinding.bind(this).apply {
            headerTextview.text = word.word
            descriptionTextview.text = word.translation
            setOnClickListener {
                WordDetailsFragment.newInstance(word)
                    .show(requireActivity().supportFragmentManager, WordDetailsFragment.TAG)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Light)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = adapter

        viewModel.wordsLiveData.observe(viewLifecycleOwner){
            adapter.itemList = it
        }

        viewModel.getAllWords()
    }

    companion object {
        const val TAG = "HistoryFragment"
        fun newInstance() = HistoryFragment()
    }
}