package ua.kpi.comsys.ip8418

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ua.kpi.comsys.ip8418.databinding.ActivityMainBinding
import ua.kpi.comsys.ip8418.drawing.DrawingFragment
import ua.kpi.comsys.ip8418.images.ImagesFragment
import ua.kpi.comsys.ip8418.movies.Container
import ua.kpi.comsys.ip8418.movies.MoviesFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var onBack: (() -> Unit)? = null

    fun setOnBack(f: () -> Unit) {
        onBack = f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            pager.adapter = object : FragmentStateAdapter(this@MainActivity) {
                override fun getItemCount(): Int = 4

                override fun createFragment(position: Int) = when (position) {
                    0 -> ImagesFragment()
                    1 -> Container()
                    2 -> DrawingFragment()
                    3 -> AuthorFragment()
                    else -> error("Not supported")
                }
            }

            pager.isUserInputEnabled = false

            pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    navigation.selectedItemId = when (position) {
                        0 -> R.id.menu_images
                        1 -> R.id.menu_movies
                        2 -> R.id.menu_drawing
                        3 -> R.id.menu_author
                        else -> error("No page here.")
                    }
                }
            })

            navigation.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_images -> pager.currentItem = 0
                    R.id.menu_movies -> pager.currentItem = 1
                    R.id.menu_drawing -> pager.currentItem = 2
                    R.id.menu_author -> pager.currentItem = 3
                }
                true
            }
        }
    }

    override fun onBackPressed() {
        if (onBack == null) {
            super.onBackPressed()
        } else {
            onBack?.invoke()
            onBack = null
        }
    }
}