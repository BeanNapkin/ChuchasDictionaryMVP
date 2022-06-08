package pro.fateeva.chuchasdictionarymvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pro.fateeva.chuchasdictionarymvp.databinding.ActivityMainBinding
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentListOfWordsBinding
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentSearchDialogBinding

class ListOfWordsFragment : Fragment() {
    private lateinit var _binding: FragmentListOfWordsBinding
    val binding: FragmentListOfWordsBinding
        get() = _binding

    private val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "BOTTOM_SHEET_FRAGMENT_DIALOG_TAG"

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
    }

    companion object {
        fun newInstance() = ListOfWordsFragment()
    }
}