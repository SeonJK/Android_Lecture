package com.example.tjswh.oct24

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.example.tjswh.oct24.R.id.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var isRunning = false
    private var timerTask: Timer? = null
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener{
            isRunning = !isRunning

            if(isRunning){
                start()
            }else{
                pause()
            }
        }

        resetFab.setOnClickListener{
            reset()
        }

        button.setOnClickListener{
            lapWrite()
        }
    }

    fun start() {
        fab.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                secText.text = "$sec"
                mSecText.text = "$milli"
            }
        }
    }

    fun pause(){
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)

        timerTask?.cancel()                                                 //NULL이 아닐 때만 동작
    }

    fun reset(){
        // 모든 변수 초기화
        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        secText.text = "0"
        mSecText.text = "00"

        // 모든 랩타임을 제거
        lapLayout.removeAllViews()
        lap = 1
    }

    fun lapWrite(){
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

        // 맨 위에 랩타임 추가
        lapLayout.addView(textView, 0)
        lap++
    }
}
