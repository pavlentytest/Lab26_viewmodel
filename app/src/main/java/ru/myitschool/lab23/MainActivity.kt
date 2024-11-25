package ru.myitschool.lab23

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myitschool.lab23.databinding.ActivityMainBinding
import java.util.Random


class MyViewModel : ViewModel() {
    val rand_value = MutableLiveData<Double>()
    fun setValue(d: Double) {
        rand_value.value = d
    }
}

class MainActivity : AppCompatActivity() {

    lateinit var myviewModel: MyViewModel
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        myviewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        binding.content.viewmodel = myviewModel
        binding.content.lifecycleOwner = this
        binding.content.getRandomNum.setOnClickListener {
            val m = binding.content.meanVal.text.toString().toDouble()
            val v = binding.content.varianceValue.text.toString().toDouble()
            myviewModel.setValue(
                Math.exp(Math.sqrt(v) * Random().nextGaussian() + m)
            )
        }
       // myviewModel.rand_value.observe(this) {
       //     findViewById<TextView>(R.id.random_number_result).text = it.toString()
      //  }

    }
}
