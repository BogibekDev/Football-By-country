package com.Footba11ByCountry.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.Footba11ByCountry.R
import com.Footba11ByCountry.model.Player

class PlayerAdapter(private var list: ArrayList<Player>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onVisible: ((Boolean) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayerHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val player = list[position]
        if (holder is PlayerHolder) {
            holder.apply {
                tvPlayer.text = player.name
                etPlayer.setText(player.etName)
                if (position == 0) etPlayer.isEnabled = false
                if (player.correct != null) {
                    if (player.correct!!) {
                        etPlayer.setBackgroundColor(Color.parseColor("#3105FF00"))
                        etPlayer.isEnabled = false
                    } else {
                        etPlayer.setBackgroundColor(Color.parseColor("#31FF0000"))
                    }
                }
                etPlayer.addTextChangedListener {
                    list[position].etName = it.toString()
                    checkList()
                }
            }
        }
    }

    private fun checkList() {
        var filled = true
        for (i in 1 until list.size) {
            if (list[i].etName == "") {
                filled = false
                break
            }
        }
        if (filled) onVisible?.invoke(true)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class PlayerHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvPlayer = view.findViewById<TextView>(R.id.tvPlayer)
        var etPlayer = view.findViewById<EditText>(R.id.etPlayer)


    }

    fun returnList(): ArrayList<Player> {
        return list
    }

    fun changeTrue(i: Int) {
        list[i].correct = true
        notifyItemChanged(i)
    }

    fun changeWrong(i: Int) {
        list[i].correct = false
        notifyItemChanged(i)
    }
}