package com.example.myapplication.presentation.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private val mBinding by viewBinding(FragmentMenuBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners(view)
    }

    private fun initListeners(view: View) {
        mBinding.btnSearchScreen.setOnClickListener {
            view.findNavController().navigate(R.id.action_menuFragment_to_searchFragment)
        }
        mBinding.btnFavoriteScreen.setOnClickListener {
            view.findNavController().navigate(R.id.action_menuFragment_to_favoriteFragment)
        }
    }
}