package pro.fateeva.chuchasdictionarymvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import pro.fateeva.chuchasdictionarymvp.databinding.FragmentWordDetailsBinding
import pro.fateeva.chuchasdictionarymvp.room.WordEntity

class WordDetailsFragment : DialogFragment() {

    private val wordEntity: WordEntity?
        get() = requireArguments().getParcelable(WORD_ENTITY_ARG)

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
    ): View {
        _binding = FragmentWordDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var imageUrl = ""

        wordEntity?.let {
            binding.wordTextview.text = it.word
            binding.translationTextview.text = it.translation
            imageUrl = "https:" + it.imageUrl
        }

        Glide.with(this)
            .load(imageUrl)
            .centerCrop()
            .into(binding.image)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "WordDetailsFragment"
        const val WORD_ENTITY_ARG = "WORD_ENTITY_ARG"

        fun newInstance(word: WordEntity) = WordDetailsFragment().apply {
            arguments = bundleOf(
                WORD_ENTITY_ARG to word
            )
        }
    }
}