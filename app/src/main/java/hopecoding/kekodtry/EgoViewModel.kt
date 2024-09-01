import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hopecoding.kekodtry.R

class EgoViewModel : ViewModel() {

    // Ego switch'in durumunu tutar
    private val _isEgoSwitchChecked = MutableLiveData<Boolean>().apply { value = true }
    val isEgoSwitchChecked: LiveData<Boolean> = _isEgoSwitchChecked

    // Diğer switch'lerin durumlarını tutar
    private val _switchStates = MutableLiveData<Map<Int, Boolean>>().apply {
        value = mapOf(
            R.id.switch1 to false,
            R.id.switch2 to false,
            R.id.switch3 to false,
            R.id.switch4 to false,
            R.id.switch5 to false
        )
    }
    val switchStates: LiveData<Map<Int, Boolean>> = _switchStates

    // Ego switch durumunu günceller
    fun onEgoSwitchChanged(isChecked: Boolean) {
        _isEgoSwitchChecked.value = isChecked
    }

    // Diğer switch durumlarını günceller
    fun onSwitchChanged(id: Int, isChecked: Boolean) {
        val updatedStates = _switchStates.value?.toMutableMap() ?: mutableMapOf()
        updatedStates[id] = isChecked
        _switchStates.value = updatedStates
    }
}
