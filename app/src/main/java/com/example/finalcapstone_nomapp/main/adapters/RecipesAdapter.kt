package com.example.finalcapstone_nomapp.main.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.model.FoodRecipe
// right or i need to put val viewmodel : RecipesViewModel
class RecipesAdapter(private val list: List<FoodRecipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FoodRecipe>(){
        override fun areItemsTheSame(oldItem: FoodRecipe, newItem: FoodRecipe): Boolean {
            // ask mohamed
            return oldItem.results == newItem.results
        }

        override fun areContentsTheSame(oldItem: FoodRecipe, newItem: FoodRecipe): Boolean {
            return oldItem == newItem
        }


    }
    private val differ = AsyncListDiffer(this,DIFF_CALLBACK)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipesAdapter.RecipesViewHolder {

        return RecipesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.recipes_itemlayout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {

        val item = differ.currentList[position]

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list: List<FoodRecipe>){
        differ.submitList(list)
    }


    class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val recipesImageView: ImageView = itemView.findViewById(R.id.recipe_imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.title_textView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.description_textView)
        val heartImageView: ImageView = itemView.findViewById(R.id.heart_imageView)
        val heartTextView: TextView = itemView.findViewById(R.id.heart_textView)
        val timeImageView: ImageView = itemView.findViewById(R.id.time_imageView)
        val timeTextView: TextView = itemView.findViewById(R.id.time_textView)
        val veganImageView: ImageView = itemView.findViewById(R.id.vigan_imageView)
        val veganTextView : TextView = itemView.findViewById(R.id.vigan_textView)

    }

}
