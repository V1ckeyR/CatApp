package ua.kpi.comsys.ip8418.images

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import ua.kpi.comsys.ip8418.databinding.FragmentImagesBinding
import ua.kpi.comsys.ip8418.images.data.ImagesRepository
import ua.kpi.comsys.ip8418.images.data.local.ImageDatabase
import ua.kpi.comsys.ip8418.images.data.local.ImagesLocalDataSource
import ua.kpi.comsys.ip8418.images.data.remote.ImagesRemoteDataSource
import ua.kpi.comsys.ip8418.images.data.remote.getImageApi

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


        vm.repository = ImagesRepository(
            ImagesRemoteDataSource(getImageApi()),
            ImagesLocalDataSource(ImageDatabase.getInstance(requireContext()))
        )

        if (vm.images.value == null) vm.loadImages()

        imagesAdapter = ImagesAdapter(listOf())

        with(binding.images) {
            adapter = imagesAdapter
            layoutManager = ImagesLayoutManager()
        }

        vm.images.observe(viewLifecycleOwner) {
            if (it.hits.isEmpty()) Toast.makeText(
                requireContext(),
                "Неможливо отримати дані для заданого запиту",
                Toast.LENGTH_SHORT
            ).show()

            imagesAdapter.update(it.hits)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}