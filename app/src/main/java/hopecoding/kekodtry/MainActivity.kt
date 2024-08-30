package hopecoding.kekodtry

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import hopecoding.kekodtry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Başlangıçta Ego switch açık
        binding.switchEgo.isChecked = true
        updateSwitchesState(binding.switchEgo.isChecked)

        // Ego switch değişikliklerini dinleme
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            updateSwitchesState(isChecked)
            if (!isChecked) {
                // Ego switch kapalıysa BottomNavigationView'ı göster
                binding.bottomNavigationBar.visibility = View.VISIBLE
            } else {
                // Ego switch açıkken BottomNavigationView'ı gizle
                binding.bottomNavigationBar.visibility = View.GONE
            }
        }

        // Diğer Switch'ler için dinamik ikon ve metin ekleme
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
            switch.isEnabled = !isEgoSwitchOn
        }

        // Ego switch'inin kendisini her durumda etkin tut
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
                handleSwitchChange(id, isChecked, binding.bottomNavigationBar, R.drawable.android_black, "${switch.text}")
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
            // Switch açıldığında ilgili icon ve text ekleniyor
            bottomNavigationView.menu.add(0, id, 0, title).setIcon(iconResId)
        } else {
            // Switch kapandığında ilgili item kaldırılıyor
            bottomNavigationView.menu.removeItem(id)
        }
    }
}
