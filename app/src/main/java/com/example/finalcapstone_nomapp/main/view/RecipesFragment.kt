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
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.todkars.shimmer.ShimmerRecyclerView


class RecipesFragment : Fragment() {

    private lateinit var binding : FragmentRecipesBinding

    private lateinit var recipesAdapter : RecipesAdapter

//      private lateinit var   shimmer : ShimmerRecyclerView


    private val recipesViewModel : RecipesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater,container,false)
        return binding.root


     //binding.shimmerRecyclerView.showShimmer()



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesAdapter = RecipesAdapter(recipesViewModel)
        binding.shimmerRecyclerView.adapter = recipesAdapter



        observers()
        recipesViewModel.callRecipes()

    }

//    override fun onResume() {
//        super.onResume()
//        shimmer.showShimmer()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        shimmer.hideShimmer()
//    }

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