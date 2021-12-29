package com.example.finalcapstone_nomapp.main.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.main.view.FavoriteRecipesViewModel
import com.example.finalcapstone_nomapp.model.FavoriteModel
import com.squareup.picasso.Picasso

class FavoriteRecipeAdapter(var viewModel: FavoriteRecipesViewModel) :
    RecyclerView.Adapter<FavoriteRecipeAdapter.FavoriteRecipeViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteModel>() {

        override fun areItemsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteModel, newItem: FavoriteModel): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteRecipeAdapter.FavoriteRecipeViewHolder {

        return FavoriteRecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.favorite_itemlayout,
                parent,
                false
            )
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {

        val item = differ.currentList[position]

        //================================================================//

        holder.favoriteRecipeTitleTextView.text = item.title
        holder.favoriteRecipeDescriptionTextview.text = item.description
        holder.favoriteTimeTextView.text = "${item.ready}"
        holder.likeHeartTextView.text = "${item.likes}"

        holder.itemView.setOnClickListener {
            viewModel.likes = item.likes.toString()
            viewModel.image = item.image
            viewModel.ready = item.ready
            viewModel.description = item.description
            viewModel.title = item.title
            viewModel.vegan = item.vegan

            holder.itemView.findNavController().navigate(R.id.action_FavoriteFragment_to_detailsFragment)
        }

//        //===================================================================//

        Picasso.get().load(item.image).into(holder.favoriteRecipeImageView)

        //==================================================================//


        if (item.vegan) {
            holder.favoriteVeganImageView.setImageResource(R.drawable.vegan)
            holder.favoriteVeganTextView.setTextColor(R.color.green)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<FavoriteModel>) {
        differ.submitList(list)
    }

    class FavoriteRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val favoriteRecipeImageView: ImageView = itemView.findViewById(R.id.favorite_imageView)
        val favoriteRecipeTitleTextView: TextView =
            itemView.findViewById(R.id.favorite_title_textView)
        val favoriteRecipeDescriptionTextview: TextView =
            itemView.findViewById(R.id.favorite_description_textView)
        val likeHeartTextView: TextView = itemView.findViewById(R.id.favorite_heart_textView)
        val favoriteTimeTextView: TextView = itemView.findViewById(R.id.favorite_time_textView)
        val favoriteVeganImageView: ImageView = itemView.findViewById(R.id.favorite_vegan_imageView)
        val favoriteVeganTextView: TextView = itemView.findViewById(R.id.favorite_vegan_textView)

    }

}
