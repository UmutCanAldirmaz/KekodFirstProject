package hopecoding.kekodtry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.decode.GifDecoder
import coil.load
import hopecoding.kekodtry.databinding.FragmentKindnessBinding


class KindnessFragment : Fragment() {

    private var _binding: FragmentKindnessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentKindnessBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use the binding to access the ImageView

        binding.imageView.load(R.drawable.kindness) {
            decoderFactory { result, options, _ -> GifDecoder(result.source, options) }

        }

    }

}