package com.example.finalcapstone_nomapp.main.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.finalcapstone_nomapp.databinding.FragmentRecipesBinding
import com.example.finalcapstone_nomapp.main.adapters.RecipesAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.finalcapstone_nomapp.R
import com.example.finalcapstone_nomapp.main.identity.LoginActivity
import com.example.finalcapstone_nomapp.main.identity.sharedEditor
import com.example.finalcapstone_nomapp.main.identity.sharedPref
import com.google.android.material.dialog.MaterialAlertDialogBuilder

import com.google.android.material.internal.ViewUtils.dpToPx
import com.google.firebase.auth.FirebaseAuth

class RecipesFragment : Fragment() {

    private lateinit var binding : FragmentRecipesBinding

    private lateinit var recipesAdapter : RecipesAdapter

    private val recipesViewModel : RecipesViewModel by activityViewModels()
    private lateinit var logoutItem: MenuItem



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater,container,false)
        sharedPref = requireActivity().getSharedPreferences(SHARED_PREF,Context.MODE_PRIVATE)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        var recyclerView = binding.recyclerView

        recipesAdapter = RecipesAdapter(recipesViewModel)
        recyclerView.adapter = recipesAdapter



        observers()
        recipesViewModel.callRecipes()



    }

    @SuppressLint("CommitPrefEdits")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.logout ->{
               // dialog when logout
               MaterialAlertDialogBuilder(
                   requireActivity(),
                   android.R.style.ThemeOverlay_Material_Dialog_Alert
               )
                   // press no then dialog will disappears
                   .setMessage("Are you sure to logout ? ")
                   .setNegativeButton("No"){ _,_  ->
                   }
                   .setPositiveButton("Yes"){ _,_ ->
                       sharedEditor = sharedPref.edit()
                       sharedEditor.putString(USERID,"")
                       sharedEditor.putBoolean(STATE,false)
                       sharedEditor.commit()
                       FirebaseAuth.getInstance().signOut()
                       logoutItem.isVisible = false
                       val intent = Intent(requireActivity(), LoginActivity::class.java)
                       startActivity(intent)
                       requireActivity().finish()
                   }.show()
           }
       }
     return  super.onOptionsItemSelected(item)

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.logout,menu)

        logoutItem = menu.findItem(R.id.logout)

        val userId = sharedPref.getString(USERID,"")
        if (userId!!.isBlank()){
            logoutItem.isVisible = false
        }
    }
    fun observers(){
        recipesViewModel.recipesLiveData.observe(viewLifecycleOwner,{
            recipesAdapter.submitList(it)

            // to stop the shimmer when the response is success
            binding.shimmerViewContainer.startShimmer()
//         binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.isVisible = false
           // binding.shimmerViewContainer.animate().alpha(1f)

        })

        recipesViewModel.recipesErrorLiveData.observe(viewLifecycleOwner, {
            it?.let {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()

            }

        })


    }



}