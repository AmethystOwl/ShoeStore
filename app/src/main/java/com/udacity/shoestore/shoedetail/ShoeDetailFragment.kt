package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodel.ShoeListViewModel


class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!
    private val shoeListViewModel: ShoeListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Add New Shoe"
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = shoeListViewModel


        shoeListViewModel.insertState.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "Shoe has been added", Toast.LENGTH_LONG)
                    .show()
                requireActivity().onNavigateUp()
                shoeListViewModel.onDoneObservingAddState()
            }
        }

        shoeListViewModel.fieldsState.observe(viewLifecycleOwner) {
            it?.let {
                when (it) {
                    1 -> {
                        binding.shoeNameTextInputEditText.error = "Name cannot be empty"
                    }
                    2 -> {
                        binding.shoeBrandTextInputEditText.error = "Brand cannot be empty"
                    }
                    3 -> {
                        binding.shoeSizeTextInputEditText.error = "Size cannot be zero"
                    }
                    4 -> {
                        binding.shoeDescriptionTextInputEditText.error =
                            "Description cannot be empty"
                    }
                    else -> {
                        resetViewsState()
                    }

                }
            }
        }
        setHasOptionsMenu(true)
    }

    private fun resetViewsState() {
        binding.shoeDescriptionTextInputLayout.error = null
        binding.shoeSizeTextInputLayout.error = null
        binding.shoeBrandTextInputLayout.error = null
        binding.shoeNameTextInputLayout.error = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_shoe_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cancelMenuItem -> {
                requireActivity().onNavigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }
}







