package com.lxoms.customviewtestdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lxoms.customviewtestdemo.view.HitTheBellViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_hitthebellview.setOnClickListener {
            startActivity(Intent(this, HitTheBellViewActivity::class.java))
        }

    }
}