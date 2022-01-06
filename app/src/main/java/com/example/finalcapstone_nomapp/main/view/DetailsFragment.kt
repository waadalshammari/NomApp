package com.example.finalcapstone_nomapp.main.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.FragmentDetailsBinding
import com.example.finalcapstone_nomapp.model.Result
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {
      private lateinit var binding : FragmentDetailsBinding
      private val recipesViewModel : RecipesViewModel by activityViewModels()
    private val favoriteRecipesViewModel : FavoriteRecipesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // حطيت فاريبل بسبب ان لما استدعي الفن الخاصه بالادد عند السيت اون كلك ليسنر
        // راح يخليني اكتب قيمه لكل الداتا اللي راح تجي وهدا غلط هدي الطريقه راح تخليني
        // فقط استدعي المودل ويجيب لي الداتا اللي موجوده
        val result = Result(
            recipesViewModel.likes.toInt(),
            true,
            true,
            true,
            recipesViewModel.id.toInt(),
            recipesViewModel.image,
            recipesViewModel.ready,
            recipesViewModel.description,
            recipesViewModel.title,
            recipesViewModel.vegan,
            true,
            true
        )


        observers()

        binding.addImageView.setOnClickListener(){
            observers()
            favoriteRecipesViewModel.addFavoriteRecipe(result,"")
            findNavController().navigate(R.id.action_detailsFragment_to_FavoriteFragment)

            if (binding.addImageView.isPressed){
               binding.addImageView.setImageResource(R.drawable.addimageviewblack)
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    fun observers(){
        recipesViewModel.selectedRecipeMutabileLiveData.observe(viewLifecycleOwner,{
        //================================================================//
            Picasso.get().load(it.image).into(binding.recipeDetailsImageView2)

        //================================================================//
            binding.detailTitleTextView.text = it.title
            binding.detailLikesTextView.text = it.aggregateLikes.toString()
            binding.detailsTimeTextView.text = it.readyInMinutes.toString()

        //=================================================================//
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




        })
    }

}