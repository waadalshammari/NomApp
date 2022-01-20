package com.example.finalcapstone_nomapp.main.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.FragmentDetailsBinding
import com.example.finalcapstone_nomapp.databinding.FragmentDetailsFavoriteBinding
import com.example.finalcapstone_nomapp.model.FavoriteModel
import com.example.finalcapstone_nomapp.model.Result

class DetailsFavoriteFragment : Fragment() {
    private lateinit var binding : FragmentDetailsFavoriteBinding
    private val recipesViewModel : RecipesViewModel by activityViewModels()
    private val favoriteRecipesViewModel : FavoriteRecipesViewModel by activityViewModels()
    lateinit var favoriteItem : Result
    lateinit var favoriteItemDetails : FavoriteModel



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observers()
        favoriteObserver()
        favoriteRecipesViewModel.callFavoriteRecipes()

        // press the image to navigate to the recipe url
        binding.recipeDetailsImageView2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(favoriteItem.sourceUrl))
            startActivity(intent)
        }

    }



    @SuppressLint("ResourceAsColor")
    fun observers(){

        recipesViewModel.selectedRecipeMutabileLiveData.observe(viewLifecycleOwner,{
            it?.let {

                favoriteItem = it
                //================================================================//
                Glide.with(requireActivity()).load(it.image).into(binding.recipeDetailsImageView2)

                //================================================================//
                binding.detailTitleTextView.text = it.title
                binding.detailLikesTextView.text = it.aggregateLikes.toString()
                binding.detailsTimeTextView.text = it.readyInMinutes.toString()
                binding.summaryTextView.text = it.summary
                //==================================================================//

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

            }
            recipesViewModel.selectedRecipeMutabileLiveData.postValue(null)

        })
    }
    //========================================================================================//

    @SuppressLint("ResourceAsColor")
    fun favoriteObserver(){
        favoriteRecipesViewModel.selectedRecipeMutabileLiveData.observe(viewLifecycleOwner,{
            it?.let {

                favoriteItemDetails = it
                //================================================================//
                Glide.with(requireActivity()).load(it.image).into(binding.recipeDetailsImageView2)

                //================================================================//
                binding.detailTitleTextView.text = it.title
                binding.detailLikesTextView.text = it.likes.toString()
                binding.detailsTimeTextView.text = it.ready.toString()

                //=================================================================//
                binding.summaryTextView.text = it.description
                //==================================================================//

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
            }
            favoriteRecipesViewModel.selectedRecipeMutabileLiveData.postValue(null)
        })
    }
}