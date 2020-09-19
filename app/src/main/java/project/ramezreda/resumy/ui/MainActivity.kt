package project.ramezreda.resumy.ui

import android.app.Application
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.ActivityMainBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.ui.viewModels.BasicInfoViewModel
import project.ramezreda.resumy.utils.ImageConverter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var basicInfoViewModel: BasicInfoViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDagger()
        val binding = initBinding()

        val toolbar: Toolbar = binding.appBarMain.toolbar
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_basic_info,
                R.id.nav_summary,
                R.id.nav_skills,
                R.id.nav_experience,
                R.id.nav_education
            ), drawerLayout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        initObservers(binding)

    }

    private fun initBinding(): ActivityMainBinding {
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = basicInfoViewModel

        return binding
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .applicationContextModule(
                ApplicationContextModule(
                    applicationContext as Application,
                    this
                )
            )
            .notificationsModule(NotificationsModule(this))
            .build()
            .inject(this)
    }

    private fun initObservers(binding: ActivityMainBinding) {
        basicInfoViewModel.basicInfo?.observe(this, Observer {
            if(it.isEmpty()) {
                return@Observer
            }
            binding.navView.nav_full_name.setText(it?.first()?.fullName)
            binding.navView.nav_email.setText(it?.first()?.email)

            val image = ImageConverter.byteArrayToBitmap(it?.first()?.picture)
            image?.let { bitmap ->
                binding.navView.imageView.setImageBitmap(bitmap)
            }
        })
    }
}