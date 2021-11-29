package com.example.tyap6

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

	var moveE: EditText? = null

	var text: TextView? = null
	//var but1: Button? = null

	var arr: Array<Array<String>> = arrayOf()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		arr = Array(10) { Array(6) { "" } }
		addRule()

		val addB: Button = findViewById(R.id.Add)
		moveE = findViewById(R.id.Move)
		text = findViewById(R.id.text)

		addB.setOnClickListener {
			start()
		}
	}

	@SuppressLint("SetTextI18n")
	private fun start() {
		val stroka = moveE?.text.toString() + "e"
		var copy = moveE?.text.toString()
		var stek = "E"
		var stekS = "e"
		var out = ""
		var out1 = ""
		var bool = false
		var sym = false
		for (ch in stroka) {
			sym = false
			for (i in 0..5) {
				if (arr[i][1].isNotEmpty()) {
					sym = ch == arr[i][1][0]
				}
				if (sym) break
			}
			if (!sym) break
		}

		out = "$out(q, $copy, $stek, $stekS)\n"
		if (stroka.isNotEmpty()) {
			for (ch in stroka) {
				out1 = out
				for (i in 0..5) {
					if (arr[i][0].isNotEmpty())
						if (ch.toString() == arr[i][1] && stek[0] == arr[i][2][0]
						) {
							if (arr[i][4][0] == 'e') {
								stek = stek.drop(1)
								if (stek.isEmpty()) stek = "e"
							} else {
								stek += if (stek == "E") arr[i][4].drop(1)
								else arr[i][4]
							}

							if (arr[i][5][0] == 'e') {
								stekS = stekS.drop(1)
								if (stekS.isEmpty()) stekS = "e"
							} else {
								if (stekS == "e") stekS = stekS.drop(1)
								stekS += arr[i][5]
							}

							copy = copy.drop(1)
							if (copy.isEmpty()) copy = "e"

							out = "$out(q, $copy, $stek, $stekS)\n"

							break
						}
				}
				if (out == out1) {
					bool = true
					break
				}
			}
		}
		if(stek!="e") bool = true
		when {
			!sym -> text?.text = "Присутсвуют лишние символы"
			bool -> text?.text = out + "Цепочка не выпонима"
			else -> text?.text = out + "Цепочка выполнима"
		}
	}

	private fun addRule() {
		arr[0][0] = "q"
		arr[0][1] = "*"
		arr[0][2] = "E"
		arr[0][3] = "q"
		arr[0][4] = "EE*"
		arr[0][5] = "e"

		arr[1][0] = "q"
		arr[1][1] = "e"
		arr[1][2] = "+"
		arr[1][3] = "q"
		arr[1][4] = "e"
		arr[1][5] = "+"

		arr[2][0] = "q"
		arr[2][1] = "a"
		arr[2][2] = "E"
		arr[2][3] = "q"
		arr[2][4] = "e"
		arr[2][5] = "a"

		arr[3][0] = "q"
		arr[3][1] = "+"
		arr[3][2] = "E"
		arr[3][3] = "q"
		arr[3][4] = "EE+"
		arr[3][5] = "e"

		arr[4][0] = "q"
		arr[4][1] = "e"
		arr[4][2] = "*"
		arr[4][3] = "q"
		arr[4][4] = "e"
		arr[4][5] = "*"
	}
}