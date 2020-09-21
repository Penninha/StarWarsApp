package com.fernandopenna.starwarsapp

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {
    internal inner class ListFragmentAdapter(private val items: List<ListItem>) : RecyclerView.Adapter<ListFragmentAdapter.ListViewHolder>() {
        inner class ListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            private val tvTitle = view.findViewById<TextView>(R.id.name_list)
            private val imgHolder = view.findViewById<ImageView>(R.id.image_list)
            private val root = view.findViewById<LinearLayout>(R.id.list_item_root)
            private lateinit var listItem: ListItem

            init {
                root.setOnClickListener() {
                    val intent = Intent(activity, DetailsActivity::class.java).apply {
                        putExtra("name", listItem.name)
                        putExtra("description", listItem.description)
                        putExtra("imageId", listItem.image)
                    }
                    startActivity(intent)
                }
            }

            fun bind(item: ListItem) {
                listItem = item
                tvTitle.text = item.name
                imgHolder.setImageResource(item.image)
            }
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_item, parent, false)
            return ListViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.bind(items[position])
        }
    }

    private val items = mutableListOf<ListItem>()
    private lateinit var names: Array<String>
    private lateinit var descriptions: Array<String>
    private lateinit var images: IntArray

    companion object {
        fun newInstance() = ListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val resources = context.resources
        names = resources.getStringArray(R.array.names)
        descriptions = resources.getStringArray(R.array.descriptions)

        val typedArray = resources.obtainTypedArray(R.array.images)
        val itemCount = names.size
        images = IntArray(itemCount)
        for(i in 0 until itemCount) {
            images[i] = typedArray.getResourceId(i, 0)
        }
        typedArray.recycle()

        for(i in 0 until itemCount) {
            items.add(ListItem(names[i], descriptions[i], images[i]))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_fragment, container, false);
        val rv = view.findViewById<RecyclerView>(R.id.rvItems)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = ListFragmentAdapter(items)
        return view
    }
}
