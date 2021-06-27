package id.ic.pelitabangsa.dendiyp.dyprmadi_parsingjson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.afifrully_parsingjson.R
import id.ic.pelitabangsa.dendiyp.dyprmadi_parsingjson.model.DataItem
import java.text.FieldPosition

class UserAdapter (private val users: MutableList<DataItem>) :
        RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View=
                LayoutInflater.from(parent.context).inflate(.layout.list_item_user,parent,false)
        return ListViewHolder(
                view
        )
    }

    fun addUser(newUser: DataItem){
        users.add(newUser)
        notifyItemInserted(users.lastIndex)
    }

    fun clear (){
        users.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserAdapter.ListViewHolder, position: Int) {
        val user = users[position]

        Glide.with(holder.itemView.context)
                .load(user.Avatar)
                .apply(RequestOptions().override(80, 80).placeholder(R.drawable.robotics))
                .transform(CircleCrop())
                .into(holder.ivAvatar)

        holder.tvUserName.text = "${user.FirstName}${user.LastName}"
        holder.tvEmail.text = user.Email
    }
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvUserName: TextView = itemView.findViewById(R.id.itemName)
        var tvEmail: TextView = itemView.findViewById(R.id.itemEmail)
        var ivAvatar: ImageView = itemView.findViewById(R.id.itemAvatar)
    }

}
