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
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_play.*
import kotlinx.android.synthetic.main.win_dialog.view.*
import java.util.*

class PlayActivity : AppCompatActivity() {

    private var ListOfelements = arrayListOf(R.drawable.audio_cassette,
            R.drawable.baseballcap,
            R.drawable.basketball,
            R.drawable.bicycle_helmet,
            R.drawable.book,
            R.drawable.calculator,
            R.drawable.cards,
            R.drawable.flashlight,
            R.drawable.gift,
            R.drawable.key,
            R.drawable.running_shoes,
            R.drawable.skateboard,
            R.drawable.tablet,
            R.drawable.toothbrash,
            R.drawable.video_camera,
            R.drawable.water_bottle)

    private var ListOfelements_selected = arrayListOf(R.drawable.audio_cassette_selected,
            R.drawable.baseballcap_selected,
            R.drawable.basketball__selected,
            R.drawable.bicycle_helmet__selected,
            R.drawable.book__selected,
            R.drawable.calculator_selected,
            R.drawable.cards__selected,
            R.drawable.flashlight_selected,
            R.drawable.gift_selected,
            R.drawable.key_selected,
            R.drawable.running_shoes_selected,
            R.drawable.skateboard_selected,
            R.drawable.tablet_selected,
            R.drawable.toothbrash_selected,
            R.drawable.video_camera_selected,
            R.drawable.water_bottle_selected)

    private var userValue = 0
    private var numberOfElements = 6
    private var userCapacite = 0
    private var capacityOfSac = 0
    private var value = IntArray(numberOfElements)
    private var setted = IntArray(numberOfElements)
    private var wieght = IntArray(numberOfElements)
    private var listOfElementImage = IntArray(numberOfElements)
    private var listOfElementImageSelected = IntArray(numberOfElements)
    private var sumOfWight = 0
    private var optimaleValue = 0
    private var minWeightObject = Integer.MAX_VALUE

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)
        var arrback = arrayListOf(R.drawable.back,R.drawable.background_level__1,R.drawable.nature)
        startTimer()
        var rand = Random()
        rlv.setBackgroundResource(arrback[rand.nextInt(3)])
        nbrRound.text="${nbrOfRound}"
        var listOfImageId = arrayListOf(imageView_item1, imageView_item2, imageView_item3, imageView_item4, imageView_item5, imageView_item6)

//        var rand = Random()
        var i = 5
        while (i >= 0) {

            var randomi2 = rand.nextInt(ListOfelements.size)
            Picasso.with(this).load(ListOfelements[randomi2]).into(listOfImageId[i])
            value[i] = propertieOfObject(ListOfelements[randomi2])[0]
            wieght[i] = propertieOfObject(ListOfelements[randomi2])[1]
            sumOfWight += wieght[i]
            if (wieght[i] < minWeightObject) {
                minWeightObject = wieght[i]
            }
            listOfElementImage[i] = ListOfelements[randomi2]
            listOfElementImageSelected[i] = ListOfelements_selected[randomi2]
            setted[i] = 0
            ListOfelements.removeAt(randomi2)
            ListOfelements_selected.removeAt(randomi2)
            i--

        }

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

    private fun startTimer() {

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
