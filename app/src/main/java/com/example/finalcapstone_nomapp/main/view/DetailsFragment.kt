package com.example.finalcapstone_nomapp.main.view

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.FragmentDetailsBinding
import com.example.finalcapstone_nomapp.model.Result
import com.squareup.picasso.Picasso
import kotlin.NumberFormatException


class DetailsFragment : Fragment() {

      private lateinit var binding : FragmentDetailsBinding
      private val recipesViewModel : RecipesViewModel by activityViewModels()
    private val favoriteRecipesViewModel : FavoriteRecipesViewModel by activityViewModels()
    lateinit var favoriteItem : Result


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val result = Result(likes, true, true, true, idDetails, image, ready, "", summary,
//        title, vegan, true, true)
        observers()

        binding.addImageView.setOnClickListener(){

         favoriteRecipesViewModel.addFavoriteRecipe(favoriteItem,"")

            findNavController().navigate(R.id.action_detailsFragment_to_FavoriteFragment)

            if (binding.addImageView.isPressed){
               binding.addImageView.setImageResource(R.drawable.addimageviewblack)
            }
        }
        binding.recipeDetailsImageView2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(favoriteItem.sourceUrl))
            startActivity(intent)
        }
    }

    @SuppressLint("ResourceAsColor")
    fun observers(){



        recipesViewModel.selectedRecipeMutabileLiveData.observe(viewLifecycleOwner,{

            favoriteItem = it

            //================================================================//
            Picasso.get().load(it.image).into(binding.recipeDetailsImageView2)

        //================================================================//
            binding.detailTitleTextView.text = it.title
            binding.detailLikesTextView.text = it.aggregateLikes.toString()
            binding.detailsTimeTextView.text = it.readyInMinutes.toString()

        //=================================================================//
//            binding.detailVegetarianTextView.text = it.vegetarian.toString()
//            binding.detailVeganTextView.text = it.vegan.toString()
//            binding.detailDairyfreeTextView.text = it.dairyFree.toString()
//            binding.detailGlutenfreeTextView.text = it.glutenFree.toString()
//            binding.detailHealthyTextView.text = it.veryHealthy.toString()
//            binding.detailCheapTextView.text = it.cheap.toString()
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