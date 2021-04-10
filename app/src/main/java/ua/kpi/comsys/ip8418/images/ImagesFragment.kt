package ua.kpi.comsys.ip8418.images

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ua.kpi.comsys.ip8418.databinding.FragmentImagesBinding

class ImagesFragment : Fragment() {
    private var _binding: FragmentImagesBinding? = null
    private val binding: FragmentImagesBinding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val vm by lazy { ViewModelProvider(requireActivity()).get(ImagesViewModel::class.java) }
    private lateinit var imagesAdapter: ImagesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagesAdapter = ImagesAdapter(vm.images)

        with(binding.images) {
            adapter = imagesAdapter
            layoutManager = ImagesLayoutManager()
        }

        binding.addImage.setOnClickListener { getImage() }

    }

    companion object {
        private const val GET_IMAGE = 1
    }

    private fun getImage() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }

        startActivityForResult(Intent.createChooser(intent, "Select Image"), GET_IMAGE)
    }

    private fun addImage(uri: Uri) {
        vm.images.add(uri)
        imagesAdapter.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode != GET_IMAGE || resultCode != Activity.RESULT_OK) return
        data?.data?.let(this::addImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}