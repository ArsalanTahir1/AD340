package com.example.ad340

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.InvocationHandler

//Now lets create a viewholder for our adapter ok :)

class DailyForecastViewHolder(view : View) : RecyclerView.ViewHolder(view)
{

    //why we created these variables in view holder class and not in adapter class cuz view holder is responsible for binding data :)

    private val tempText=view.findViewById<TextView>(R.id.tempText)
    private val descriptionText=view.findViewById<TextView>(R.id.descriptionText)


    fun bind(dailyForecast: DailyForecast)
    {
        tempText.text= String.format("%.2f",dailyForecast.temp)
        descriptionText.text=dailyForecast.description
    }

}



//Adapter :)


//we are going to pass a function which takes DailyForecast and returns unit(Aka  void)
class DailyForecastAdapter(private val clickHandler: (DailyForecast)->Unit):ListAdapter<DailyForecast,DailyForecastViewHolder>(DIFF_CONFIG) {



     companion object
     {
         //wrote this in CAPS to express that its static you know like in java :)

         // and you know that object fella is telling us about anonymous innerclass so know we access class methods without creating an object for it and for that we have to override its methods :)


         val DIFF_CONFIG= object : DiffUtil.ItemCallback<DailyForecast>()
         {
             override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean
             {
                 return oldItem===newItem //  === means to check object reference
             }

             override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast): Boolean
             {
                 return oldItem==newItem // = = means to check value :)


             }


         }
     }


    //overiding members of list dailyForecastAdapter you know our list adapter


    // it creates a viewholder you know a kind of box for data and in our case that data is dailyForecast

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForecastViewHolder {

        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.item_daily_forecast,parent, false)

        return DailyForecastViewHolder(itemView)

    }


    //And now this function just puts our data( you know daily forecast) in the boxes aka view holders

    override fun onBindViewHolder(holder: DailyForecastViewHolder, position: Int) {

        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            clickHandler(getItem(position))
        }
    }


}