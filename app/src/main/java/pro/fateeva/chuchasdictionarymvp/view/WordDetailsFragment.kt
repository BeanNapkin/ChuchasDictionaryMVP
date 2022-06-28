package pro.fateeva.chuchasdictionarymvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentWordDetailsBinding
import pro.fateeva.chuchasdictionarymvp.model.Word

class WordDetailsFragment : DialogFragment() {

    private val word: Word?
        get() = requireArguments().getParcelable(WORD_ARG)

    private var _binding: FragmentWordDetailsBinding? = null
    val binding: FragmentWordDetailsBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Light)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWordDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wordTextview.text = word?.text
        binding.translationTextview.text = word?.meanings?.first()?.translation?.translation
        Glide.with(this)
            .load("https:" + word?.meanings?.first()?.imageUrl)
            .centerCrop()
            .into(binding.image)
    }

    companion object {
        const val TAG = "WordDetailsFragment"
        const val WORD_ARG = "WORD_ARG"
        fun newInstance(word: Word) = WordDetailsFragment().apply {
            arguments = bundleOf(
                WORD_ARG to word
            )
        }
    }
}