package com.chalco.jose.laboratoriocalifcado03

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chalco.jose.laboratoriocalifcado03.databinding.ItemTeacherBinding

class TeacherAdapter(var list: List<TeacherResponse>) : RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemTeacherBinding.bind(view)

        fun bind(teacher: TeacherResponse) {
            binding.txtName.text = teacher.name
            binding.txtLastName.text = teacher.last_name

            Glide
                .with(itemView)
                .load(teacher.imageUrl)
                .into(binding.imgTeacher)

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${teacher.phone}")
                }
                itemView.context.startActivity(intent)
            }

            binding.root.setOnLongClickListener {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${teacher.email}")
                }
                itemView.context.startActivity(intent)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemTeacher = list[position]
        holder.bind(itemTeacher)
    }

    override fun getItemCount(): Int = list.size
}