package pro.fateeva.chuchasdictionarymvp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pro.fateeva.chuchasdictionarymvp.R
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentHistoryBinding
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentWordDetailsBinding

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    val binding: FragmentHistoryBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = HistoryFragment()
    }
}