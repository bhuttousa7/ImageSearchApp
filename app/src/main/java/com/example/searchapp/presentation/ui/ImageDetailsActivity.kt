package com.example.searchapp.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.searchapp.R
import com.example.searchapp.data.model.ImageResponse
import com.example.searchapp.databinding.ActivityImageDetailsBinding
import com.example.searchapp.databinding.FragmentHomeBinding
import com.example.searchapp.presentation.di.ImageResponseHolder

class ImageDetailsActivity : AppCompatActivity() {
    private lateinit var imageView : ImageView
    private lateinit var likesTextView : TextView
    private lateinit var commentsTextView : TextView
    private lateinit var downloadsTextView : TextView
    private lateinit var tagsTextView : TextView
    private lateinit var binding: ActivityImageDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val selectedImageResponse = ImageResponseHolder.selectedImageResponse
        if (selectedImageResponse != null) {
            Log.d("ImageDetailsActivity", selectedImageResponse.largeImageURL)
            binding.likes.text = selectedImageResponse.likes.toString()
            binding.comments.text = selectedImageResponse.comments.toString()
            //can use comma separated to add # regex
            val modifiedString =  "#" + selectedImageResponse.tags.replace(", ", " #")
            binding.tags.text = modifiedString
            binding.username.text = selectedImageResponse.user
            binding.downloads.text = selectedImageResponse.downloads.toString()
            Glide.with(binding.userProfile)
                .load(selectedImageResponse.userImageURL)
//                .override(selectedImageResponse.imageWidth,selectedImageResponse.imageHeight)
                .fitCenter()
                .circleCrop()
                .into(binding.userProfile)
            Glide.with(binding.imageviewLarge)
                .load(selectedImageResponse.largeImageURL)
//                .override(selectedImageResponse.imageWidth,selectedImageResponse.imageHeight)
                .fitCenter()
                .into(binding.imageviewLarge)

        }

        ImageResponseHolder.selectedImageResponse = null

    }
    companion object {
        private const val EXTRA_IMAGE_URL = "extra_image_url"
        private const val EXTRA_COMMENTS = "extra_comments"
        private const val EXTRA_LIKES = "extra_likes"
        private const val EXTRA_TAGS = "extra_tags"
        private const val EXTRA_DOWNLOADS = "extra_downloads"
        private const val EXTRA_USERIMAGE = "extra_userimage"
        private const val EXTRA_USERNAME = "extra_username"


        fun start(context: Context, imageUrl: String, comments: Int, likes: Int, tags: String, downloads: Int, userImage: String, username: String) {
            val intent = Intent(context, ImageDetailsActivity::class.java).apply {
//                putExtra(EXTRA_IMAGE_URL, imageUrl)
//                putExtra(EXTRA_COMMENTS, comments)
//                putExtra(EXTRA_LIKES, likes)
//                putExtra(EXTRA_TAGS, tags)
//                putExtra(EXTRA_DOWNLOADS, downloads)
//                putExtra(EXTRA_USERIMAGE, userImage)
//                putExtra(EXTRA_USERNAME, username)
//
//                Log.i("f", imageUrl)
            }
            context.startActivity(intent)
        }
    }

}