package pro.fateeva.chuchasdictionarymvp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pro.fateeva.chuchasdictionarymvp.databinding.ActivityMainBinding
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
        binding.searchButtonTextview.setOnClickListener {
            Toast.makeText(requireContext(),
                binding.searchEditText.text.toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        fun newInstance() = SearchDialogFragment()
    }
}