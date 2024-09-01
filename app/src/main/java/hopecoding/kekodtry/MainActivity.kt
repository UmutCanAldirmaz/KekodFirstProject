package hopecoding.kekodtry

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import hopecoding.kekodtry.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavigation
        NavigationUI.setupWithNavController(bottomNavigationView, navController)


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.switch1 -> {
                    navController.navigate(R.id.happinessFragment)
                    true
                }
                R.id.switch2 -> {
                    navController.navigate(R.id.respectFragment)
                    true
                }
                R.id.switch3 -> {
                    navController.navigate(R.id.kindnessFragment)
                    true
                }
                R.id.switch4 -> {
                    navController.navigate(R.id.loveFragment)
                    true
                }
                R.id.switch5 -> {
                    navController.navigate(R.id.lonelyFragment)
                    true
                }
                R.id.switchEgo -> {
                    navController.navigate(R.id.egoFragment)
                    true
                }
                else -> false
            }
        }
        
    }
}
