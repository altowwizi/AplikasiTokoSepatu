package com.example.sportshoes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_product.view.*

class ProductAdapter    (val context: Context) :
                        RecyclerView.Adapter<ProductAdapter.ViewHolder>(), Filterable {

    var arrayList = ArrayList<ProductModel>()
    var ProductListFilter = ArrayList<ProductModel>()

    fun setData(arrayList: ArrayList<ProductModel>) {
        this.arrayList = arrayList
        this.ProductListFilter = arrayList
        notifyDataSetChanged()
    }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindItems(model: ProductModel) {
                //id pada item_product.xml          //variabel pada productModel
                itemView.productName.text = model.nmProduct
                itemView.productDesc.text = model.dsProduct
                itemView.priceOfProduct.text = model.priceofProduct.toString()
                itemView.imgProduct.setImageResource(model.picProduct)
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_product, parent, false)
            return ProductAdapter.ViewHolder(v)
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bindItems(arrayList[position])
            holder.itemView.setOnClickListener() {
                val model = arrayList.get(position)

                var gProduct: String = model.nmProduct
                var gDesc: String = model.dsProduct
                var gPrice: Int = model.priceofProduct.toString().toInt()
                var gImg: Int = model.picProduct

                val intent = Intent(context, Order::class.java)

                intent.putExtra("pProduct", gProduct)
                intent.putExtra("pDesc", gDesc)
                intent.putExtra("pPrice", gPrice)
                intent.putExtra("pImg", gImg)

                context.startActivity(intent)

            }
        }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResult = FilterResults()
                if (charSequence == null || charSequence.length < 0) {
                    filterResult.count = ProductListFilter.size
                    filterResult.values = ProductListFilter
                } else {
                    var searchChr = charSequence.toString()
                    val productSearch = ArrayList<ProductModel>()
                    for (item in ProductListFilter) {
                        if (item.nmProduct.toLowerCase().contains(searchChr) || item.dsProduct.toLowerCase().contains(searchChr)) {
                            productSearch.add(item)
                        }
                    }
                    filterResult.count = productSearch.size
                    filterResult.values = productSearch
                }
                    return filterResult
            }
            override fun publishResults(p0: CharSequence?, filterResult: FilterResults?) {
                arrayList = filterResult!!.values as ArrayList<ProductModel>
                notifyDataSetChanged()
            }
        }
    }
}