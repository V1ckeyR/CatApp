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
import kotlinx.serialization.ExperimentalSerializationApi
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

    @ExperimentalSerializationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagesAdapter = ImagesAdapter(listOf())
        if (vm.images.value == null) vm.loadImages()

        with(binding.images) {
            adapter = imagesAdapter
            layoutManager = ImagesLayoutManager()
        }

        vm.images.observe(viewLifecycleOwner) {
            imagesAdapter.update(it.hits)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}