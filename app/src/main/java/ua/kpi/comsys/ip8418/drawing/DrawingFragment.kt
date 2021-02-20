package ua.kpi.comsys.ip8418.drawing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import ua.kpi.comsys.ip8418.R
import ua.kpi.comsys.ip8418.databinding.FragmentDrawingBinding

class DrawingFragment : Fragment() {
    private var _binding: FragmentDrawingBinding? = null
    private val binding: FragmentDrawingBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            materialButtonToggleGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
                when (checkedId) {
                    R.id.graphic_btn -> if (isChecked) {
                        graphic.isVisible = true
                        diagram.isVisible = false
                    }
                    R.id.diagram_btn -> if (isChecked) {
                        graphic.isVisible = false
                        diagram.isVisible = true
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}