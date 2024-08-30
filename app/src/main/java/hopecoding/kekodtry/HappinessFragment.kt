package hopecoding.kekodtry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.decode.GifDecoder
import coil.load
import hopecoding.kekodtry.databinding.FragmentHappinessBinding

class HappinessFragment : Fragment() {

    private var _binding: FragmentHappinessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using binding
        _binding = FragmentHappinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Use the binding to access the ImageView

        binding.imageView.load(R.drawable.gojo) {
            decoderFactory { result, options, _ -> GifDecoder(result.source, options) }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
