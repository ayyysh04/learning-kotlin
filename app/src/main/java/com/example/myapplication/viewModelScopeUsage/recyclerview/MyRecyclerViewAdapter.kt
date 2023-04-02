package com.example.myapplication.viewModelScopeUsage.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

//good practice to create data list and functions in main and pass it to recycler Adapter
class MyRecyclerViewAdapter(
    private val fruitsList: List<Fruit>,
    private val clickListener: (Fruit) -> Unit
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        This line of code obtains a LayoutInflater object from the parent ViewGroup's context
        //LayoutInflater is a class in Android that is used to instantiate a layout XML file into its corresponding View objects. It reads an XML file, and then creates and returns a View object that represents the layout.
        //In other words, the LayoutInflater is used to convert an XML file containing a layout into a corresponding View object that can be displayed on the screen.
        val listItemView = layoutInflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(listItemView)

    }

    //used to display data
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = fruitsList[position]
        holder.bind(fruit, clickListener)
    }

    override fun getItemCount(): Int {
        return fruitsList.size
    }

}

//to hold individual item
class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(fruit: Fruit, clickListener: (Fruit) -> Unit) {

        //recommded: setting of data should be done in viewHolder not in on
        val myTextView = view.findViewById<TextView>(R.id.tvName)
        myTextView.text = fruit.name

//        ways to set click listeners in recyclerView
//        1st way
//        view.setOnClickListener {
//            Toast.makeText(view.context, "Supplier is : ${fruit.supplier}", Toast.LENGTH_SHORT)
//                .show()
//        }

//        2nd way
        view.setOnClickListener {
            clickListener(fruit)
        }
    }
}
