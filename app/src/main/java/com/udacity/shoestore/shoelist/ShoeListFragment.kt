package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeRowItemBinding

class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding get() = _binding!!
    private val shoeListViewModel: ShoeListViewModel by activityViewModels()
    private lateinit var shoeItemBinding: ShoeRowItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoeListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Shoe List"
        binding.lifecycleOwner = viewLifecycleOwner
        shoeListViewModel.shoesList.observe(viewLifecycleOwner) { shoesList ->
            shoesList.forEach { shoe ->
                shoeItemBinding = ShoeRowItemBinding.inflate(layoutInflater, null, false)
                shoeItemBinding.shoe = shoe
                shoeItemBinding.itemImageView.setImageDrawable(
                    ResourcesCompat.getDrawable(resources, R.drawable.shoes_sample, null)
                )
                shoeItemBinding.root.layoutParams =
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(15, 15, 15, 15)
                    }
                binding.listLinearLayout.addView(shoeItemBinding.root)
            }
        }
        binding.addNewItemFAB.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToAddShoeFragment())
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}