package com.example.finalcapstone_nomapp.main.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.finalcapstone_nomapp.databinding.FragmentRecipesBinding
import com.example.finalcapstone_nomapp.main.adapters.RecipesAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator

import com.google.android.material.internal.ViewUtils.dpToPx





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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recyclerView = binding.recyclerView

        recipesAdapter = RecipesAdapter(recipesViewModel)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.setLayoutManager(mLayoutManager)

        recipesAdapter = RecipesAdapter(RecipesViewModel())
        recyclerView.adapter = recipesAdapter



        observers()
        recipesViewModel.callRecipes()


        Handler(Looper.getMainLooper()).postDelayed({

        },3000)
        observers()
    }



    fun observers(){
        recipesViewModel.recipesLiveData.observe(viewLifecycleOwner,{
            recipesAdapter.submitList(it)

            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE


        })

        recipesViewModel.recipesErrorLiveData.observe(viewLifecycleOwner, {
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()

            }

        })


    }

}