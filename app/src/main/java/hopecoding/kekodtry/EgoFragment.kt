package hopecoding.kekodtry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.decode.GifDecoder
import coil.load
import com.google.android.material.bottomnavigation.BottomNavigationView
import hopecoding.kekodtry.databinding.FragmentEgoBinding

class EgoFragment : Fragment() {

    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEgoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activityBinding = (activity as MainActivity).binding
        val bottomNavigationBar = activityBinding.bottomNavigation

        binding.optionView.load(R.drawable.eren) {
            decoderFactory { result, options, _ -> GifDecoder(result.source, options) }

        }

        // Başlangıçta Ego switch açık
        binding.switchEgo.isChecked = true
        updateSwitchesState(binding.switchEgo.isChecked)

        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            updateSwitchesState(isChecked)

            if (isChecked) {
                bottomNavigationBar.visibility = View.GONE
                bottomNavigationBar.menu.removeItem(R.id.switchEgo)
            } else {
                bottomNavigationBar.visibility = View.VISIBLE
                if (bottomNavigationBar.menu.findItem(R.id.switchEgo) == null) {
                    bottomNavigationBar.menu.add(0, R.id.switchEgo, 0, "Ego Switch")
                        .setIcon(R.drawable.android_black)
                }
            }
        }

        setupSwitchListeners()
    }

    private fun updateSwitchesState(isEgoSwitchOn: Boolean) {
        val switches = listOf(
            binding.switch1,
            binding.switch2,
            binding.switch3,
            binding.switch4,
            binding.switch5
        )

        switches.forEach { switch ->
            // Ego switch açıkken diğer switch'ler kapalı ve tıklanamaz
            if (isEgoSwitchOn) {
                switch.isEnabled = false
                switch.isChecked = false
            } else {
                switch.isEnabled = true
            }
        }
    }

    private fun setupSwitchListeners() {
        val switches = listOf(
            binding.switch1 to R.id.switch1,
            binding.switch2 to R.id.switch2,
            binding.switch3 to R.id.switch3,
            binding.switch4 to R.id.switch4,
            binding.switch5 to R.id.switch5
        )

        switches.forEach { (switch, id) ->
            switch.setOnCheckedChangeListener { _, isChecked ->
                handleSwitchChange(
                    id,
                    isChecked,
                    bottomNavigationView = (activity as MainActivity).binding.bottomNavigation,
                    R.drawable.android_black,
                    "${switch.text}"
                )
            }
        }
    }

    private fun handleSwitchChange(
        id: Int,
        isChecked: Boolean,
        bottomNavigationView: BottomNavigationView,
        iconResId: Int,
        title: String
    ) {
        if (isChecked) {
            if (bottomNavigationView.menu.size() < 5) {
                bottomNavigationView.menu.add(0, id, 0, title).setIcon(iconResId)
            }
        } else {
            bottomNavigationView.menu.removeItem(id)
        }

        if(!binding.switchEgo.isChecked){
            val switches = listOf(
                binding.switch1,
                binding.switch2,
                binding.switch3,
                binding.switch4,
                binding.switch5
            )

            // Switch'leri tıklanamaz hale getir (veya etkinleştir) menü boyutuna göre
            switches.forEach { switch ->
                switch.isEnabled = bottomNavigationView.menu.size() < 5 || switch.isChecked
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
