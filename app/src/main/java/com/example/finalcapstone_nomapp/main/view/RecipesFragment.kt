package com.example.finalcapstone_nomapp.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.FragmentRecipesBinding
import com.example.finalcapstone_nomapp.main.adapters.RecipesAdapter


class RecipesFragment : Fragment() {

    private lateinit var binding : FragmentRecipesBinding

    private lateinit var recipesAdapter : RecipesAdapter

    private val recipesViewModel : RecipesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater,container,false)
        return binding.root

     //showShimmerEffect()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesAdapter = RecipesAdapter()
        binding.recyclerView.adapter = recipesAdapter

       // showShimmerEffect()

        observers()
        recipesViewModel.callRecipes()

    }

    fun observers(){
        recipesViewModel.recipesLiveData.observe(viewLifecycleOwner,{
            recipesAdapter.submitList(it)

        })

        recipesViewModel.recipesErrorLiveData.observe(viewLifecycleOwner, {
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()

            }

        })

    }
//
//    private fun showShimmerEffect(){
//        binding.recyclerView.showShimmer()
//    }
//    private fun hideShimmerEffect(){
//        binding.recyclerView.hideShimmer()
//    }


}