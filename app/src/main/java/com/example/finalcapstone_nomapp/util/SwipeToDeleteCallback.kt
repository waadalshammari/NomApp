package com.example.finalcapstone_nomapp.util

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import com.example.finalcapstone_nomapp.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

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