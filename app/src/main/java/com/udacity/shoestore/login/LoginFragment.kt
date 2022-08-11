package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }
        binding.signUpTextView.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
        }

        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.getItem(0).isVisible = false
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}