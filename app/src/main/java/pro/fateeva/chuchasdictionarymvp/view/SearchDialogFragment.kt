package pro.fateeva.chuchasdictionarymvp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pro.fateeva.chuchasdictionarymvp.AppState
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentSearchDialogBinding
import pro.fateeva.chuchasdictionarymvp.presenter.Presenter
import pro.fateeva.chuchasdictionarymvp.presenter.PresenterImpl

class SearchDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    private var onSearchClickListener: OnSearchClickListener? = null

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable?) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            binding.searchButtonTextview.isEnabled =
                binding.searchEditText.text != null && !binding.searchEditText.text.toString()
                    .isEmpty()
        }
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.searchEditText.text.toString())
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    interface OnSearchClickListener {
        fun onClick(searchWord: String)
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
        binding.searchButtonTextview.setOnClickListener(onSearchButtonClickListener)
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = SearchDialogFragment()
    }
}