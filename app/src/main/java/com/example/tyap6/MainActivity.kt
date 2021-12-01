package com.example.tyap6

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

	var et1: EditText? = null
	var et2: EditText? = null
	var et3: EditText? = null
	var et4: EditText? = null
	var et5: EditText? = null
	var et6: EditText? = null

	var moveE: EditText? = null

	var text: TextView? = null
	var text1: TextView? = null
	//var but1: Button? = null

	var chasiki = 0
	var index = 0

	var arr: Array<Array<String>> = arrayOf()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		arr = Array(100) { Array(6) { "" } }

		val addB: Button = findViewById(R.id.Add)
		val nextB: Button = findViewById(R.id.NextButton)

		et1 = findViewById(R.id.et1)
		et2 = findViewById(R.id.et2)
		et3 = findViewById(R.id.et3)
		et4 = findViewById(R.id.et4)
		et5 = findViewById(R.id.et5)
		et6 = findViewById(R.id.et6)

		moveE = findViewById(R.id.Move)
		text = findViewById(R.id.text)
		text1 = findViewById(R.id.text1)

		addB.setOnClickListener {
			when(chasiki){
				0-> addRule()
				1->	start()
			}
			et1?.setText("")
			et2?.setText("")
			et3?.setText("")
			et4?.setText("")
			et5?.setText("")
			et6?.setText("")
		}
		nextB.setOnClickListener {
			et1?.isVisible = false
			et2?.isVisible = false
			et3?.isVisible = false
			et4?.isVisible = false
			et5?.isVisible = false
			et6?.isVisible = false
			nextB.isVisible = false
			moveE?.isVisible = true
			chasiki = 1
		}
	}

	@SuppressLint("SetTextI18n")
	private fun start() {
		var aa = ""
		for(i in 0..index){
			aa += arr[i][0] +" "+ arr[i][1] +" "+ arr[i][2] +" "+ arr[i][3] +" "+ arr[i][4] +" "+ arr[i][5] + '\n'
		}
		val stroka = moveE?.text.toString() + "e"
		var copy = moveE?.text.toString()
		var stek = "E"
		var stekS = "e"
		var sost = arr[0][0]
		var out = ""
		var out1 = ""
		var bool = false
		var sym = false
		for (ch in stroka) {
			sym = false
			for (i in 0..index) {
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
				for (i in 0..index) {
					if (arr[i][0].isNotEmpty())
						if (sost == arr[i][0] && ch.toString() == arr[i][1] && stek[0] == arr[i][2][0]
						) {
							sost = arr[i][3]
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
		text1?.text = aa
	}

	private fun addRule() {
		arr[index][0] = et1?.text.toString()
		arr[index][1] = et2?.text.toString()
		arr[index][2] = et3?.text.toString()
		arr[index][3] = et4?.text.toString()
		arr[index][4] = et5?.text.toString()
		arr[index][5] = et6?.text.toString()

//		arr[0][0] = "q"
//		arr[0][1] = "*"
//		arr[0][2] = "E"
//		arr[0][3] = "q"
//		arr[0][4] = "EE*"
//		arr[0][5] = "e"
//
//		arr[1][0] = "q"
//		arr[1][1] = "e"
//		arr[1][2] = "+"
//		arr[1][3] = "q"
//		arr[1][4] = "e"
//		arr[1][5] = "+"
//
//		arr[2][0] = "q"
//		arr[2][1] = "a"
//		arr[2][2] = "E"
//		arr[2][3] = "q"
//		arr[2][4] = "e"
//		arr[2][5] = "a"
//
//		arr[3][0] = "q"
//		arr[3][1] = "+"
//		arr[3][2] = "E"
//		arr[3][3] = "q"
//		arr[3][4] = "EE+"
//		arr[3][5] = "e"
//
//		arr[4][0] = "q"
//		arr[4][1] = "e"
//		arr[4][2] = "*"
//		arr[4][3] = "q"
//		arr[4][4] = "e"
//		arr[4][5] = "*"
		index++
	}
}