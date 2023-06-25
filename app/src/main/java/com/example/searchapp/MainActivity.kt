package com.example.searchapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.searchapp.presentation.ui.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		if (savedInstanceState == null) {
			// Create an instance of the HomeFragment
			val homeFragment = HomeFragment()

			// Get the FragmentManager and start a FragmentTransaction
			val fragmentManager: FragmentManager = supportFragmentManager
			val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

			// Replace the fragmentContainerView2 with the HomeFragment
			fragmentTransaction.replace(R.id.fragmentContainerView, homeFragment)

			// Commit the transaction
			fragmentTransaction.commit()
		}
	}
}