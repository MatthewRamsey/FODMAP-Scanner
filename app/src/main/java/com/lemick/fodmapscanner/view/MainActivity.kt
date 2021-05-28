package com.lemick.fodmapscanner.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.fasterxml.jackson.core.type.TypeReference
import com.google.android.material.snackbar.Snackbar
import com.lemick.fodmapscanner.R
import com.lemick.fodmapscanner.business.ProductSummaryManager
import com.lemick.fodmapscanner.databinding.ActivityMainBinding
import com.lemick.fodmapscanner.model.api.ApiDependencyProvider
import com.lemick.fodmapscanner.model.fodmap.FodmapEntry
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
    }

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var fodmapsRef : List<FodmapEntry>;
    private var permissionGranted = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        checkCameraPermissions()
        loadFodmaps()
    }

    private fun loadFodmaps() {
        val inputStream: InputStream = resources.openRawResource(R.raw.fodmap_list);
        val fodmapListJson : String = inputStream.bufferedReader().use { it.readText() }
        val typeRef = object : TypeReference<List<FodmapEntry>>() {}
        fodmapsRef = ApiDependencyProvider.objectMapper.readValue(fodmapListJson, typeRef);
    }

    private fun checkCameraPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_CODE);
            } else {
                permissionGranted = true;
            }
        } else {
            permissionGranted = true;
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


}