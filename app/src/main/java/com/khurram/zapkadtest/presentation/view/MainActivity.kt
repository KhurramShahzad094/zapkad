package com.khurram.zapkadtest.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.khurram.zapkadtest.databinding.ActivityMainBinding
import com.khurram.zapkadtest.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Example of a call to a native method
//        this.showToast(stringFromJNI())
}


    /**
     * A native method that is implemented by the 'cmake' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

}