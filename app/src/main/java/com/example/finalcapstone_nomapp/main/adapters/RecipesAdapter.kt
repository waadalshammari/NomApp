package com.example.finalcapstone_nomapp.main.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.main.view.RecipesViewModel
import com.example.finalcapstone_nomapp.model.FoodRecipe
import com.example.finalcapstone_nomapp.model.Result
import com.squareup.picasso.Picasso


class RecipesAdapter() :
    RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {
       // في الادابتر نحط model اللي نبي نستخدمها
        // put result the list from FoodRecipes so we can use what inside it
    // نحط  result لاننا نبي نستخدم اللي داخلها
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {

        val item = differ.currentList[position]
        // data from API for them >> الداتا اللي راح تجي من الاي بي تجي هنا وتستقبلها
        // احط الاشياء اللي ابيها تستقبل الداتا
        holder.titleTextView.text = item.title
        holder.descriptionTextView.text = item.summary
        holder.timeTextView.text = "${item.readyInMinutes}"
        holder.heartTextView.text = "${item.aggregateLikes}"


        //================================================================//
        Picasso.get().load(item.image).into(holder.recipesImageView)
        //=================================================================//
        // vegetarian boolean >> if condition
        if(item.vegetarian){
            holder.veganImageView.setImageResource(R.drawable.vegan)
            holder.veganTextView.setTextColor(R.color.green)
        }



    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    fun submitList(list: List<Result>){
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
