package com.yelloco.movieapp.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.yelloco.movieapp.R
import com.yelloco.movieapp.network.ORIGINAL_PROFILE_BASE_URL
import kotlinx.android.synthetic.main.fragment_original_photo.*
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


class OriginalPhotoFragment : Fragment() {
    private var imagePath: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_original_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments

        if (arguments != null)
            imagePath = arguments.getString("IMAGE_PATH", "")

        Glide.with(requireActivity())
            .load(ORIGINAL_PROFILE_BASE_URL + imagePath)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(original_photo_image_view)

        save_photo_in_gallery.setOnClickListener {
            downloadImage(ORIGINAL_PROFILE_BASE_URL + imagePath)
        }
    }

    private fun downloadImage(imageURL: String) {
        if (verifyPermissions() == false) {
            return
        }
        val dirPath: String = Environment.getExternalStorageDirectory().getAbsolutePath()
            .toString() + "/" + getString(R.string.app_name) + "/"
        val dir = File(dirPath)
        val fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1)
        Glide.with(this)
            .load(imageURL)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    @NonNull resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    val bitmap = (resource as BitmapDrawable).bitmap
                    Toast.makeText(requireContext(), "Saving Image...", Toast.LENGTH_SHORT).show()
                    saveImage(bitmap, dir, fileName)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
                override fun onLoadFailed(@Nullable errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Toast.makeText(
                        requireContext(),
                        "Failed to Download Image! Please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    private fun saveImage(bitmap: Bitmap?, storageDir: File, imageFileName: String) {
        var successDirCreated = true
        if (!storageDir.exists()) {
            successDirCreated = storageDir.mkdir()
        }
        if (successDirCreated) {
            val imageFile: File = File(storageDir, imageFileName)
            val savedImagePath = imageFile.absolutePath
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
                Toast.makeText(requireContext(), "Image Saved!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Error while saving image!", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }
        } else {
            Toast.makeText(requireContext(), "Failed to make folder!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyPermissions(): Boolean? {

        val permissionExternalMemory =
            ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        if (permissionExternalMemory != PackageManager.PERMISSION_GRANTED) {
            val STORAGE_PERMISSIONS = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(requireActivity(), STORAGE_PERMISSIONS, 1)
            return false
        }
        return true
    }


}