package com.example.tvprogramapp.interfaces

import android.view.View
import com.example.tvprogramapp.dto.Program

interface ItemClickListener {
    fun openItem(program: Program, view: View)
}