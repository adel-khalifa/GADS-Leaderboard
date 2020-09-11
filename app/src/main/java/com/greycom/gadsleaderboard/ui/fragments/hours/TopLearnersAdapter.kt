
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.data.HoursResponse.HoursResponseItem
import kotlinx.android.synthetic.main.item_hours.view.*

class TopLearnersAdapter : RecyclerView.Adapter<TopLearnersAdapter.TopLearnersViewHolder>() {

    inner class TopLearnersViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class DiffImpl : DiffUtil.ItemCallback<HoursResponseItem>() {
        override fun areItemsTheSame(
            oldItem: HoursResponseItem,
            newItem: HoursResponseItem
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: HoursResponseItem,
            newItem: HoursResponseItem
        ): Boolean {
            return oldItem == newItem
        }


    }

    private val diffCallBack = DiffImpl()


    // List of com.a.papersamwish.pojo.Data place holder / the object which can access from outside the adapter to add/update data
    val asyncListDiffer = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TopLearnersAdapter.TopLearnersViewHolder = TopLearnersViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hours, parent, false)
    )


    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: TopLearnersAdapter.TopLearnersViewHolder, position: Int) {

        asyncListDiffer.currentList.let {
            val hoursItem = it[position]

            holder.itemView.apply {
                item_top_name.text = hoursItem.name
                Glide.with(context).load(hoursItem.badgeUrl).into(item_top_iv)
                item_top_details.text = "${hoursItem.hours} learning hours , ${hoursItem.country}"

            }


        }
    }


}