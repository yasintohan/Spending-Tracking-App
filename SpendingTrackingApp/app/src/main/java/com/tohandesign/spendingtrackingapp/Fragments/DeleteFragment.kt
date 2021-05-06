package com.tohandesign.spendingtrackingapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tohandesign.spendingtrackingapp.R
import kotlinx.android.synthetic.main.fragment_add.view.*


class DeleteFragment : Fragment() {

    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_delete, container, false)

        v.backIcon.setOnClickListener{
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }




        return v
    }


}