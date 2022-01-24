![image](https://user-images.githubusercontent.com/91456619/140023025-d22c50c0-322a-4cc0-9912-3ee42f16054e.png)
# Final Capstone
Tuwaiq Academy Second Project.

NOM Android Application

## LOGO and OVERVIEW:
![Yellow and Black Fun Modern Restaurant Food Logo (1)](https://user-images.githubusercontent.com/91456619/150314884-7b972bd5-7b5a-45fa-a04e-263e84472399.png)


This project represents an android application NOM, which helps the user display different food recipes.

## Technologies used:
This application was built using the following technologies:
### For Designing the logo of the app:
* Canva
### For Designing the pages of the app"
* Figma 
* Canva

### For Programming the app:
* Android Architecture Components:,LiveData,ViewModel and Data binding.
* RecyclerViews & Adapters.
* Required Libraries
* Figma
* spoonacular API

## Wireframes and User stories:
![Wireframe]![Frame 1](https://user-images.githubusercontent.com/91456619/150309920-2aa96d7d-2bd3-4be2-a666-ac6d7be81f1c.png)

link to Figma [Figma Wireframe](https://www.figma.com/file/Q91sullzZ7KMApxlt370zE/NomApp?node-id=54%3A4)


- As a user,I want to see different recipes so that I can choose one.

- As a user, I would like to be able to add a recipes so that I can cook it later.

- As a user I want to see the details of each recipes so that I can see additional information about it  .

- As a user I want to have the recipes url so that I can see more information and details about the recipes.

- As a user I want to deltet any recipe so that I can make sure that I cooked this recipe or I dont need it any more.

- As a user I want to edit any recipe I add it before so that I can remmember my note when I use the app.


-------------------------------------------------------------------------
 ## List of the depencenceies used in the project:

 ```

 
   *  for motion layout:
 ```kotlin
    dependencies {
    implementation 'androidx.constraintlayout:constraintlayout:2.1.2'
    implementation 'com.google.firebase:firebase-auth-ktx:21.0.1'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    }
``` 

   * Navigation Component:
```kotlin
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    }
```
    
   * Recyclerview:
```kotlin
    dependencies {
   implementation 'androidx.recyclerview:recyclerview:1.2.1'
    }
```
   * Shimmer
```kotlin
    dependencies { 
   implementation 'com.facebook.shimmer:shimmer:0.5.0'
   }
```

```
   * Retrofit
```kotlin
    dependencies { 
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.okhttp3:okhttp:4.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
   }
```

   * viewModel
```kotlin
    dependencies { 
     implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0"
   }
```

   * livedata
```kotlin
    dependencies { 
     implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.4.0"
   }
```


   * Coroutines
```kotlin
    dependencies { 
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2'
   }
```

   * picasso
```kotlin
    dependencies { 
   implementation 'com.squareup.picasso:picasso:2.71828'
   }
```

   * for swipe delete
```kotlin
    dependencies { 
   implementation 'it.xabaras.android:recyclerview-swipedecorator:1.2.3'
   }
```

 * glide
```kotlin
    dependencies { 
   implementation 'com.github.bumptech.glide:glide:4.12.0'
    kapt 'com.github.bumptech.glide:compiler:4.12.0'
   }
```

-----------------------------------------------------------------



planning : 


On the beginning searched for different To do list apps to have full understanding about what I will work on and to have an idea about the design after that I designed my app using Figma then transformed the design on Android Studio. wrote down what my app need and what features I want to put on it, eventually start coding and working on my app.

development process :

  
*Searched: for different apps and what is the basic features in every app

*wrote : wrote down the features I wont for my apps and searched in different website for the code to understand what I will do when I start my coding.

*designed : designed my app in Figma then In Android Studio.

*wrote the code : created packages for database,adapter,viewmodel,fragments and splash .

*run the app : tried to run the app and solved the errors.


 Problem solving  strategy:
decide the nature of the error (if it's syntax, runtime or logical) and
Searched in different website like developer android,youtube,stackoverflow and medium asked programmers who have knowledge or Mr. Mohamed and Mr.Saad

## Unsolved Problems which would be fixed in future iterations:

* send notification to notifiy the user that it's time to cook on a specific time based on what the user write in the note.
* filter 
* add comments
 ____________________________________________________________________________________________________________________________________________________________


## My favorite functions work:

* fun for swipe Delete 

to delete by swipping to right or left

 ```kotlin
 
 // in class
                                                                                    //Defining the Swipe Directions
abstract class SwipeToDeleteCallback(context: Context) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

     val backGroundColor = ContextCompat.getColor(context, R.color.red)
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }
   //To add our icon and background we just need to override onChildDraw(). This will draw our icon
   // and background in the correct
   // position as our RecyclerView item is
   // swiped across the screen.
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {

        RecyclerViewSwipeDecorator.Builder(
            c,
            recyclerView,
            viewHolder,
            dX,
            dY,
            actionState,
            isCurrentlyActive
        )
            .addBackgroundColor(backGroundColor)
            .addActionIcon(R.drawable.ic_baseline_delete_24)
            .addSwipeLeftLabel("DELETE")
            .create()
            .decorate()
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}
  /// in adapter 
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
    // in favorite fragment 
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
    
```

