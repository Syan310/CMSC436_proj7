// Shaoyu Yan  UID: 117452815
// Wenzhuo Niu UID: 118004426
package com.example.project7

import SAXHandler
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.xml.sax.InputSource
import javax.xml.parsers.SAXParserFactory

class MainActivity : AppCompatActivity() {
    private lateinit var gameView: GameView
    private val balloons = Balloons()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val parser = SAXParserFactory.newInstance().newSAXParser()
            val saxHandler = SAXHandler()
            val inputStream = resources.openRawResource(R.raw.balloons3)
            parser.parse(InputSource(inputStream), saxHandler)

            // Set parsed data to GameView
            gameView = GameView(this, saxHandler.getParsedData())
            setContentView(gameView)

            // Log balloon data
            saxHandler.getParsedData().getAllBalloons().forEach { balloon ->
                Log.d("MainActivity", "Balloon (x=${balloon.x}, y=${balloon.y}, radius=${balloon.radius})")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
