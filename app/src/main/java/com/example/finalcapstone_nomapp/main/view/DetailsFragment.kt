package com.example.finalcapstone_nomapp.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
      private lateinit var binding : FragmentDetailsBinding
      private val recipesViewModel : RecipesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()
    }

    @SuppressLint("ResourceAsColor")
    fun observers(){
        recipesViewModel.selectedRecipeMutabileLiveData.observe(viewLifecycleOwner,{
        //================================================================//
            Picasso.get().load(it.image).into(binding.recipeDetailsImageView2)

        //================================================================//
            binding.detailLikesTextView.text = it.aggregateLikes.toString()
            binding.detailsTimeTextView.text = it.readyInMinutes.toString()
        //=================================================================//
            binding.detailVegetarianTextView.text = it.vegetarian.toString()
            binding.detailVeganTextView.text = it.vegan.toString()
            binding.detailDairyfreeTextView.text = it.dairyFree.toString()
            binding.detailGlutenfreeTextView.text = it.glutenFree.toString()
            binding.detailHealthyTextView.text = it.veryHealthy.toString()
            binding.detailCheapTextView.text = it.cheap.toString()
            binding.summaryTextView.text = it.summary

            when {
                it.vegetarian -> {
                    binding.detailVegetarianImageView.setImageResource(R.drawable.check)
                    binding.detailVeganTextView.setTextColor(R.color.green)
                }
                it.vegan -> {

                    binding.detailVeganImageView.setImageResource(R.drawable.check)
                    binding.detailVeganTextView.setTextColor(R.color.green)
                }
                it.dairyFree -> {
                    binding.detailDairyfreeImageView.setImageResource(R.drawable.check)
                    binding.detailDairyfreeTextView.setTextColor(R.color.green)

                }
                it.glutenFree -> {
                    binding.detailGlutenfreeImageView.setImageResource(R.drawable.check)
                    binding.detailGlutenfreeTextView.setTextColor(R.color.green)

                }
                it.veryHealthy -> {
                    binding.detailHealthyImageView.setImageResource(R.drawable.check)
                    binding.detailGlutenfreeTextView.setTextColor(R.color.green)

                }
                it.cheap -> {
                    binding.detailCheapImageView.setImageResource(R.drawable.check)
                    binding.detailCheapTextView.setTextColor(R.color.green)
                }
            }




        })
    }

}