package pro.fateeva.chuchasdictionarymvp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentSearchDialogBinding

class SearchDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable?) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            binding.searchButtonTextview.isEnabled =
                binding.searchEditText.text != null && !binding.searchEditText.text.toString()
                    .isEmpty()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchEditText.addTextChangedListener(textWatcher)
        binding.searchButtonTextview.setOnClickListener{
            setFragmentResult(fragmentResult, bundleOf(wordResultKey to binding.searchEditText.text.toString()))
            dismiss()
        }
    }

    companion object {
        val fragmentResult = "SEARCH_ON_DIALOGFRAGMENT_RESULT"
        val wordResultKey = "word"
        fun newInstance() = SearchDialogFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}