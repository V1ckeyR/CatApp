package ua.kpi.comsys.ip8418

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ua.kpi.comsys.ip8418.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            pager.adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount(): Int = 2

                override fun createFragment(position: Int) = when (position) {
                    0 -> DrawingFragment()
                    1 -> AuthorFragment()
                    else -> error("Not supported")
                }
            }

            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    navigation.selectedItemId = when (position) {
                        0 -> R.id.menu_home
                        1 -> R.id.menu_author
                        else -> error("No page here.")
                    }
                }
            })

            navigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> pager.currentItem = 0
                    R.id.menu_author -> pager.currentItem = 1
                }
                true
            }
        }
    }
}