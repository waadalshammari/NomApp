package com.example.finalcapstone_nomapp.main.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.main.view.FavoriteRecipesViewModel
import com.example.finalcapstone_nomapp.model.FavoriteModel
import com.example.finalcapstone_nomapp.model.Result
import kotlin.math.log

private const val TAG = "FavoriteRecipeAdapter"

// context for Glide
class FavoriteRecipeAdapter(val context: Context, val viewModel: FavoriteRecipesViewModel ,
) :

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
        holder.addNote.setText(item.note)
        //================================================================//
        holder.itemView.setOnClickListener {
           viewModel.selectedRecipeMutabileLiveData.postValue(item)
            Log.d(TAG, "item$item")
            holder.itemView.findNavController().navigate(R.id.action_FavoriteFragment_to_detailsFavoriteFragment)
        }
        holder.addNoteButton.setOnClickListener {
            val text = holder.addNote.text.toString()
            item.note = text
            viewModel.editFavoriteRecipe(item)
            // الموشر يروح حق الايدت تكست
            holder.addNote.isFocusable = false
        }
        //===================================================================//
         Glide.with(context).load(item.image).into(holder.favoriteRecipeImageView)
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
   // fun for swipe delete >> favorite fragment
    fun deleteItem  (index : Int){
        val item1 = differ.currentList[index]
        var list = mutableListOf<FavoriteModel>()
        list.addAll(differ.currentList)
        list.removeAt(index)
        differ.submitList(list.toList())
        notifyDataSetChanged()
        viewModel.deleteFavoriteRecipe(item1)

    }

    class FavoriteRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val favoriteRecipeImageView: ImageView = itemView.findViewById(R.id.favorite_imageView)
        val favoriteRecipeTitleTextView: TextView = itemView.findViewById(R.id.favorite_title_textView)
        val favoriteRecipeDescriptionTextview: TextView = itemView.findViewById(R.id.favorite_description_textView)
        val likeHeartTextView: TextView = itemView.findViewById(R.id.favorite_heart_textView)
        val favoriteTimeTextView: TextView = itemView.findViewById(R.id.favorite_time_textView)
        val favoriteVeganImageView: ImageView = itemView.findViewById(R.id.favorite_vegan_imageView)
        val favoriteVeganTextView: TextView = itemView.findViewById(R.id.favorite_vegan_textView)
        val addNote : EditText = itemView.findViewById(R.id.add_yournote_EditText)
        val addNoteButton : ImageView = itemView.findViewById(R.id.save_Note_ImageView)

    }

}
