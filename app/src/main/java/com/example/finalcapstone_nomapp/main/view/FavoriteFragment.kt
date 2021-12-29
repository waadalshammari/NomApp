package com.example.finalcapstone_nomapp.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.FragmentFavoriteBinding
import com.example.finalcapstone_nomapp.main.adapters.FavoriteRecipeAdapter


class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var favoriteAdapter : FavoriteRecipeAdapter
    private val favoriteViewModel : FavoriteRecipesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = FavoriteRecipeAdapter(favoriteViewModel)
        binding.favoriteRecyclerView.adapter = favoriteAdapter



        observers()


        favoriteViewModel.callFavoriteRecipes()


    }


    fun observers(){
        favoriteViewModel.favoriteRecipesLiveData.observe(viewLifecycleOwner,{

                favoriteAdapter.submitList(it)

        })

        favoriteViewModel.favoriteRecipesErrorLiveData.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }


}