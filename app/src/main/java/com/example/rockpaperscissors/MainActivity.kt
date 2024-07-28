package com.example.rockpaperscissors

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rockpaperscissors.ui.theme.RockPaperScissorsTheme
import kotlin.random.Random
/*ALGORITHM
*
* Player chooses one option
* At the same time , CPU generates one option randomly
* Then player choice and cpu choice is being compared and result is obtained[from game logic]
* From that result , score is being updated
*
* */
class MainActivity : ComponentActivity() {

    private lateinit var playerChoiceText: TextView //lateinit variables are for non nullable variables
    private lateinit var cpuChoiceText : TextView
    private lateinit var playerScoreText : TextView
    private lateinit var cpuScoreText : TextView

    private var playerScore = 0
    private var cpuScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerChoiceText = findViewById(R.id.playerChoice)
        cpuChoiceText = findViewById(R.id.cpuChoice)
        playerScoreText = findViewById(R.id.playerScore)
        cpuScoreText = findViewById(R.id.cpuScore)

        val rockBtn : Button = findViewById(R.id.rockBtn)
        val paperBtn : Button = findViewById(R.id.paperBtn)
        val scissorsBtn : Button = findViewById(R.id.scissorsBtn)

        rockBtn.setOnClickListener{playRound("Rock")}
        paperBtn.setOnClickListener{playRound("Paper")}
        scissorsBtn.setOnClickListener { playRound("Scissors") }
            }

    private fun playRound(playerChoice : String){
        //Generate CPU results
        val cpuChoice = getCpuChoice()
        playerChoiceText.text = "$playerChoice"
        cpuChoiceText.text = "$cpuChoice"

        val result = getResult(playerChoice,cpuChoice)
        when(result){
            "Win" ->{
                playerScore++
                playerScoreText.text = "Player Score : $playerScore"
            }
            "Lose" ->{
                cpuScore++
                cpuScoreText.text = "CPU Score : $cpuScore"
            }
        }

    }

    private fun getCpuChoice():String{
        val choices = listOf("Rock","Paper","Scissors")
        return choices[Random.nextInt(choices.size)]
    }

    private fun getResult(playerChoice: String,cpuChoice: String):String{
        return if(playerChoice == cpuChoice){
            "Draw"
        }else if ((playerChoice == "Rock" && cpuChoice == "Scissors")||
                (playerChoice == "Paper" && cpuChoice == "Rock")||
                (playerChoice == "Scissors" && cpuChoice == "Paper")){
                    "Win"
                }else{
                    "Lose"
        }
    }
}

