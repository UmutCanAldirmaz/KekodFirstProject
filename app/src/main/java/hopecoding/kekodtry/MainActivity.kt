package hopecoding.kekodtry

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import hopecoding.kekodtry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ego switch varsayılan olarak açık
        binding.switchEgo.isChecked = true
        handleSwitchChange(binding.switchEgo.isChecked)

        // Ego switch değişikliklerini dinle
        binding.switchEgo.setOnCheckedChangeListener { _, isChecked ->
            handleSwitchChange(isChecked)
        }
    }

    private fun handleSwitchChange(isEgoChecked: Boolean) {
        // Ego açıkken diğer switch'ler kapalı ve tıklanamaz olmalı
        val isOtherSwitchEnabled = !isEgoChecked

        // Diğer switch'leri etkinleştirme/devre dışı bırakma
        binding.switch1.isEnabled = isOtherSwitchEnabled
        binding.switch2.isEnabled = isOtherSwitchEnabled
        binding.switch3.isEnabled = isOtherSwitchEnabled
        binding.switch4.isEnabled = isOtherSwitchEnabled
        binding.switch5.isEnabled = isOtherSwitchEnabled

        // Ego açıkken diğer switch'leri kapat
        if (isEgoChecked) {
            binding.switch1.isChecked = false
            binding.switch2.isChecked = false
            binding.switch3.isChecked = false
            binding.switch4.isChecked = false
            binding.switch5.isChecked = false
        }
    }
}