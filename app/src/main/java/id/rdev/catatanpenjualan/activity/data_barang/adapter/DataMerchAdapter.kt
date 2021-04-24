package id.rdev.catatanpenjualan.activity.data_merch.adapter

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import id.rdev.catatanpenjualan.R
import id.rdev.catatanpenjualan.model.Merch

class DataMerchAdapter(val merch: List<Merch?>?, val onMenuClicked: OnMenuClicked) : RecyclerView.Adapter<DataMerchAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_data_merch, parent, false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int = merch?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(merch?.get(position))

        holder.itemView.ivMenuMerch.setOnClickListener {
            val popupMenu = PopupMenu(holder.itemView.context, it)
            popupMenu.menuInflater.inflate(R.menu.menu_merch, popupMenu.menu)
            popupMenu.show()

            popupMenu.setOnMenuItemClickListener {
                onMenuClicked.click(it, merch?.get(position))
                true
            }
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(merch: Merch?) {
            itemView.tvBarcode.text = merch?.barcode
            itemView.tvNamaMerch.text = merch?.namaMerch
            itemView.tvCategory.text = merch?.kategori
        }
    }

    interface OnMenuClicked {
        fun click(menuItem: MenuItem, merch: Merch?)
    }
}