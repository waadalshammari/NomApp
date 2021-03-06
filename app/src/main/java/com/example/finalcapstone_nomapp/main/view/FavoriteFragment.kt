package com.example.finalcapstone_nomapp.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.databinding.FragmentFavoriteBinding
import com.example.finalcapstone_nomapp.main.adapters.FavoriteRecipeAdapter
import com.example.finalcapstone_nomapp.util.SwipeToDeleteCallback
import com.google.firebase.auth.FirebaseAuth


class FavoriteFragment : Fragment() {

    private lateinit var binding : FragmentFavoriteBinding
    private lateinit var favoriteAdapter : FavoriteRecipeAdapter
    private val favoriteViewModel : FavoriteRecipesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        binding.favoriteNoDataImageView
        binding.favoriteNoDataTextView

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriteAdapter = FavoriteRecipeAdapter(requireActivity(),favoriteViewModel )
        binding.favoriteRecyclerView.adapter = favoriteAdapter


        // for swipe delete >> Called when a ViewHolder is swiped by the user.
       val swipeDelete = object : SwipeToDeleteCallback(this.requireContext()){
           override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
               favoriteAdapter.deleteItem(viewHolder.adapterPosition)
           }
       }
        // swipe to delete behaviors.
        // is used to just swipe the rows. It doesn’t delete them itself
        // We’ll need to delete it ourself using the RecyclerView Adapter.
        val touchHelper = ItemTouchHelper(swipeDelete)
        touchHelper.attachToRecyclerView(binding.favoriteRecyclerView)

        observers()
        favoriteViewModel.callFavoriteRecipes()
    }
    fun observers(){
        favoriteViewModel.favoriteRecipesLiveData.observe(viewLifecycleOwner,{
                favoriteAdapter.submitList(it)

           val filterList = it.filter {
               it.userid == FirebaseAuth.getInstance().uid
           }
            favoriteAdapter.submitList(filterList)

          //==============================================================//

            if (it.isNotEmpty()) {
                binding.favoriteNoDataImageView.isVisible = false
                binding.favoriteNoDataTextView.isVisible = false
            } else{
                (it.isEmpty())
                binding.favoriteNoDataImageView.isVisible = true
                binding.favoriteNoDataTextView.isVisible = true
            }

        })

        favoriteViewModel.favoriteRecipesErrorLiveData.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        })
        favoriteViewModel.deleteFavoriteLiveData.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        })

        favoriteViewModel.editFavoriteLiveData.observe(viewLifecycleOwner,{
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
            }
        })
    }
}