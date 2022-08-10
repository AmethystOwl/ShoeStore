package com.udacity.shoestore.shoedetail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.shoestore.R
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe


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
        binding.fragment = this


        shoeListViewModel.insertState.observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), "Shoe has been added", Toast.LENGTH_LONG)
                    .show()
                requireActivity().onNavigateUp()
                shoeListViewModel.onDoneObservingAddState()
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

    fun onFABClicked() {
        resetViewsState()
        binding.apply {
            when {
                shoeNameTextInputEditText.text.isNullOrEmpty() -> {
                    shoeNameTextInputLayout.error = "Shoe name must not be empty"
                    return
                }
                shoeBrandTextInputEditText.text.isNullOrEmpty() -> {
                    shoeBrandTextInputLayout.error = "Shoe brand must not be empty"
                    return
                }
                shoeSizeTextInputEditText.text.isNullOrEmpty() -> {
                    shoeSizeTextInputLayout.error = "Shoe size must not be empty"
                    return
                }
                shoeDescriptionTextInputEditText.text.isNullOrEmpty() -> {
                    shoeDescriptionTextInputLayout.error = "Shoe description must not be empty"
                    return
                }
                else -> {
                    resetViewsState()
                    val shoeName = shoeNameTextInputEditText.text.toString()
                    val shoeBrand = shoeBrandTextInputEditText.text.toString()
                    val shoeDescription = shoeDescriptionTextInputEditText.text.toString()
                    val shoeSize = shoeSizeTextInputEditText.text.toString().toDouble()
                    val shoe = Shoe(shoeName, shoeSize, shoeBrand, shoeDescription)
                    shoeListViewModel.createShoe(shoeName, shoeBrand, shoeSize, shoeDescription)
                }
            }

        }
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







