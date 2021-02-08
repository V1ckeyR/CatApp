package ua.kpi.comsys.ip8418

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commitNow()

        val tab_home = findViewById<LinearLayout>(R.id.tab_home)
        val tab_home_text = findViewById<TextView>(R.id.tab_home_text)
        val tab_home_img = findViewById<ImageView>(R.id.tab_home_img)
        val tab_author = findViewById<LinearLayout>(R.id.tab_author)
        val tab_author_text = findViewById<TextView>(R.id.tab_author_text)
        val tab_author_img = findViewById<ImageView>(R.id.tab_author_img)

        tab_home.setOnClickListener {
            changeFragment(HomeFragment())
            tab_home_text.setTextColor(Color.BLUE)
            tab_author_text.setTextColor(Color.BLACK)
            tab_home_img.setColorFilter(Color.BLUE)
            tab_author_img.setColorFilter(Color.BLACK)
        }

        tab_author.setOnClickListener {
            changeFragment(AuthorFragment())
            tab_home_text.setTextColor(Color.BLACK)
            tab_author_text.setTextColor(Color.BLUE)
            tab_home_img.setColorFilter(Color.BLACK)
            tab_author_img.setColorFilter(Color.BLUE)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}