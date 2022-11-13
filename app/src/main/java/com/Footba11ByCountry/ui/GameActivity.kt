package com.Footba11ByCountry.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.Footba11ByCountry.R
import com.Footba11ByCountry.adapter.PlayerAdapter
import com.Footba11ByCountry.databinding.ActivityGameBinding
import com.Footba11ByCountry.model.Player
import com.Footba11ByCountry.utils.Extentions.click

class GameActivity : AppCompatActivity() {

    private val binding by lazy { ActivityGameBinding.inflate(layoutInflater) }
    private lateinit var players: ArrayList<Player>
    private var width = 0
    private lateinit var unCheckedList: ArrayList<Player>
    private lateinit var answerList: ArrayList<Player>
    private lateinit var adapter: PlayerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.status_bar)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
//        height = displayMetrics.heightPixels
        width = displayMetrics.widthPixels
        generatePlayers()
        generateAnswers()
        initViews()
    }


    private fun initViews() {
        adapter = PlayerAdapter(players)
        binding.apply {
            recyclerView.adapter = adapter
            bCheck.click {
                unCheckedList = adapter.returnList()
                checkPlayers()
            }
            ivBack.click {
                finish()
            }
        }
    }

    private fun checkPlayers() {
        for (i in 1 until unCheckedList.size) {
            if (unCheckedList[i].etName.trim()
                    .equals(answerList[i].etName.trim(), ignoreCase = true)
            ) {
                adapter.changeTrue(i)
            } else {
                adapter.changeWrong(i)
            }
        }
        var count = 0
        unCheckedList.forEach {
            if (it.correct != null) {
                if (it.correct!!) count++
            }
        }

        if (count == 27) showResult()
    }


    private fun generatePlayers() {
        players = ArrayList()
        players.add(Player("Player", "Country"))
        players.add(Player("Wayne Rooney", ""))
        players.add(Player("Lionel Messi", ""))
        players.add(Player("Pelé", ""))
        players.add(Player("Kylian Mbappé", ""))
        players.add(Player("Gerd Müller", ""))
        players.add(Player("Zlatan Ibrahimovic", ""))
        players.add(Player("Edinson Cavani", ""))
        players.add(Player("Gareth Bale", ""))
        players.add(Player("Roberto Baggio", ""))
        players.add(Player("Johan Cruyff", ""))
        players.add(Player("George Bes", ""))
        players.add(Player("David Villa", ""))
        players.add(Player("Christian Pulisic", ""))
        players.add(Player("Didier Drogba", ""))
        players.add(Player("Mohamed Salah", ""))
        players.add(Player("Robbie Keane", ""))
        players.add(Player("Cristiano Ronaldo", ""))
        players.add(Player("Andriy Shevchenko", ""))
        players.add(Player("Pavel Nedvěd", ""))
        players.add(Player("George Weah", ""))
        players.add(Player("Luka Modrić", ""))
        players.add(Player("Giorgos Karagounis", ""))
        players.add(Player("James Rodríguez", ""))
        players.add(Player("Kenny Dalglish", ""))
        players.add(Player("Samuel Eto'o", ""))
        players.add(Player("Sadio Mané", ""))
        players.add(Player("Arturo Vidal", ""))
    }

    private fun generateAnswers() {
        answerList = ArrayList()
        answerList.add(Player("", ""))
        answerList.add(Player("", "England"))
        answerList.add(Player("", "Argentina"))
        answerList.add(Player("", "Brazil"))
        answerList.add(Player("", "France"))
        answerList.add(Player("", "Germany"))
        answerList.add(Player("", "Sweden"))
        answerList.add(Player("", "Uruguay"))
        answerList.add(Player("", "Wales"))
        answerList.add(Player("", "Italy"))
        answerList.add(Player("", "Netherlands"))
        answerList.add(Player("", "Northern Ireland"))
        answerList.add(Player("", "Spain"))
        answerList.add(Player("", "United States"))
        answerList.add(Player("", "Ivory Coast"))
        answerList.add(Player("", "Egypt"))
        answerList.add(Player("", "Ireland"))
        answerList.add(Player("", "Portugal"))
        answerList.add(Player("", "Ukraine"))
        answerList.add(Player("", "Czech Republic"))
        answerList.add(Player("", "Liberia"))
        answerList.add(Player("", "Croatia"))
        answerList.add(Player("", "Greece"))
        answerList.add(Player("", "Colombia"))
        answerList.add(Player("", "Scotland"))
        answerList.add(Player("", "Cameroon"))
        answerList.add(Player("", "Senegal"))
        answerList.add(Player("", "Chile"))
    }

    private fun showResult() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setLayout(
            (width * 0.85).toInt(), ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
    }


}

