package moduleproject.exemple.com.knapsackgame

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.win_dialog.view.*
import java.util.*
import kotlin.collections.ArrayList

class PlayActivity : AppCompatActivity() {





    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        startTimer()
        rlv.setBackgroundResource(arrback[rand.nextInt(3)])
        nbrRound.text="${nbrOfRound}"
        var listOfImageId = arrayListOf(imageView_item1, imageView_item2, imageView_item3, imageView_item4, imageView_item5, imageView_item6)
        loadRandomItem(numberOfElements-1,listOfImageId,this)
        capacityOfSac = rand.nextInt(sumOfWight - minWeightObject) + minWeightObject
        showCapacity.text = "C = ${capacityOfSac}  |  V = 0 €"
        optimaleValue = knapSackValue(capacityOfSac, wieght, value, 6)
        println("" + "optimale value+ $optimaleValue ")

        printArrayWithMsg(value, "value")
        printArrayWithMsg(wieght, "weight")
//        var ss = propertieOfObject(R.drawable.water_bottle)[0]
        clickItemSelectedUnselected()
        showCapacity.setTextColor(Color.parseColor("#231699"))
        submit.setOnClickListener {
        showDialog ()
        }

    }
    @SuppressLint("ResourceAsColor")
    fun clickItemSelectedUnselected() {

        var listOfImageId = arrayListOf(imageView_item1, imageView_item2, imageView_item3, imageView_item4, imageView_item5, imageView_item6)
        for (i in 0..listOfImageId.size - 1) {
            listOfImageId[i].setOnClickListener {
                //add item into the  sac
                if (setted[i] == 0) {
                    Picasso.with(this).load(listOfElementImageSelected[i]).into(listOfImageId[i])
                    setted[i] = 1
                    userValue += value[i]
                    userCapacite += wieght[i]
                    showCapacity.text = "C = ${capacityOfSac - userCapacite}  |  V = ${userValue} €"



                } else if (setted[i] != 0) {
                    Picasso.with(this).load(listOfElementImage[i]).into(listOfImageId[i])
                    userValue -= value[i]
                    userCapacite -= wieght[i]
                    showCapacity.text = "C = ${capacityOfSac - userCapacite}  |  V = ${userValue} €"
                    setted[i] = 0
                }

            }

        }

    }

    fun startTimer() {

        timer!!.base = SystemClock.elapsedRealtime()
        timer!!.start()
        timer!!.visibility = View.VISIBLE

    }

    fun showDialog() {
        val dialog = AlertDialog.Builder(this)
        var inflator = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = inflator.inflate(R.layout.win_dialog, null)
        if (userCapacite <= capacityOfSac && userValue == optimaleValue) {
            dialogView.igbravo.setImageResource(R.drawable.bravs)
            dialogView.ighands.visibility= View.VISIBLE
        }else {
            dialogView.igbravo.setImageResource(R.drawable.perdu)
            dialogView.ighands.visibility= View.GONE
        }
        dialogView.btnQuitter.setOnClickListener {
            finish()
        }
        dialogView.btnRejouer.setOnClickListener {
            nbrOfRound++
            Toast.makeText(this, "Rest Game", Toast.LENGTH_LONG).show()
            rlv.setBackgroundResource(R.drawable.back)
            var intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("nbrOfRound",nbrOfRound)
            startActivity(intent)
            finish()
        }
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        val customDialog = dialog.create()
        customDialog.show()
    }

}
