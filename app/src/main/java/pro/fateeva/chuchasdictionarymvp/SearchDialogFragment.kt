package pro.fateeva.chuchasdictionarymvp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pro.fateeva.chuchasdictionarymvp.databinding.ActivityMainBinding
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentSearchDialogBinding

class SearchDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = SearchDialogFragment()
    }
}