import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.data.IQSkillResponse.IQSkillResponseItem
import kotlinx.android.synthetic.main.item_iq.view.*

class IQAdapter : RecyclerView.Adapter<IQAdapter.IQViewHolder>() {

    inner class IQViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class DiffImpl : DiffUtil.ItemCallback<IQSkillResponseItem>() {
        override fun areItemsTheSame(
            oldItem: IQSkillResponseItem,
            newItem: IQSkillResponseItem
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: IQSkillResponseItem,
            newItem: IQSkillResponseItem
        ): Boolean {
            return oldItem == newItem
        }


    }

    private val diffCallBack = DiffImpl()


    // List of com.a.papersamwish.pojo.Data place holder / the object which can access from outside the adapter to add/update data
    val asyncListDiffer = AsyncListDiffer(this, diffCallBack)


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): IQAdapter.IQViewHolder = IQViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_iq, parent, false)
    )


    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    override fun onBindViewHolder(holder: IQAdapter.IQViewHolder, position: Int) {

        asyncListDiffer.currentList.let {
            val iqSkill = it[position]

            holder.itemView.apply {
                item_iq_name.text = iqSkill.name
                Glide.with(context).load(iqSkill.badgeUrl).into(item_iq_iv)
                item_iq_details.text = "${iqSkill.score} Skill IQ score , ${iqSkill.country}"

            }


        }
    }


}