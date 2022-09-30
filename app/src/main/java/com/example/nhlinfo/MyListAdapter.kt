package com.example.nhlinfo

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter (private val context: Activity, private val names: Array<String>, private val locations: Array<String>, private val years: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, names) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val nameText = rowView.findViewById(R.id.txtName) as TextView
        val locationText = rowView.findViewById(R.id.txtLocation) as TextView
        val yearText = rowView.findViewById(R.id.txtYear) as TextView

        nameText.text = "Team Name: ${names[position]}"
        locationText.text = "Location Name: ${locations[position]}"+"\n  second"+"\n  third"
        yearText.text = "First Year Of Play: ${years[position]}"
        return rowView
    }
}