package moduleproject.exemple.com.knapsackgame

/**
 * Created by Hamza on 27/11/2017.
 */


/* A Naive recursive implementation of 0-1 Knapsack problem */
// A utility function that returns maximum of two integers

var nbrOfRound = 1
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// Returns the maximum value that can be put in a knapsack of capacity W
fun knapSackValue(W: Int, wt: IntArray, valeur: IntArray, n: Int): Int {
    // Base Case
    if (n == 0 || W == 0)
        return 0

    // If weight of the nth item is more than Knapsack capacity W, then
    // this item cannot be included in the optimal solution
    return if (wt[n - 1] > W)
        knapSackValue(W, wt, valeur, n - 1)
    else
        max(valeur[n - 1] + knapSackValue(W - wt[n - 1], wt, valeur, n - 1), knapSackValue(W, wt, valeur, n - 1))
    // Return the maximum of two cases:
    // (1) nth item included
    // (2) not included
}


fun propertieOfObject (ivSelect : Int) : IntArray{

    var value: Int = 0
    var weight = 0

    var arr = IntArray(2)
    when (ivSelect){
        R.drawable.audio_cassette, R.drawable.audio_cassette_selected -> {
            value = 7
            weight =3
        }
        R.drawable.baseballcap, R.drawable.baseballcap_selected -> {
            value = 34
            weight =5
        }
        R.drawable.basketball, R.drawable.basketball__selected -> {
            value = 15
            weight =7
        }
        R.drawable.bicycle_helmet, R.drawable.bicycle_helmet__selected -> {
            value = 25
            weight =11
        }
        R.drawable.book, R.drawable.book__selected -> {
            value = 36
            weight =10
        }
        R.drawable.calculator, R.drawable.calculator_selected -> {
            value = 19
            weight =6
        }
        R.drawable.cards, R.drawable.cards__selected -> {
            value = 8
            weight =3
        }
        R.drawable.flashlight, R.drawable.flashlight_selected-> {
            value = 5
            weight =4
        }
        R.drawable.gift, R.drawable.gift_selected -> {
            value = 6
            weight =4
        }
        R.drawable.key, R.drawable.key_selected -> {
            value = 3
            weight =1
        }
        R.drawable.running_shoes, R.drawable.running_shoes_selected -> {
            value = 28
            weight =5
        }
        R.drawable.skateboard, R.drawable.skateboard_selected -> {
            value = 10
            weight =7
        }
        R.drawable.tablet, R.drawable.tablet_selected -> {
            value = 45
            weight =19
        }
        R.drawable.toothbrash, R.drawable.toothbrash_selected -> {
            value = 3
            weight =2
        }
        R.drawable.video_camera, R.drawable.video_camera_selected -> {
            value = 55
            weight =17
        }
        R.drawable.water_bottle, R.drawable.water_bottle_selected -> {
            value = 12
            weight =8
        }
    }
    arr[0]= value
    arr[1]=weight
    return arr
}

fun printArrayWithMsg(arrayContent: IntArray, arrayName: String) {
    for (i in 0..arrayContent.size - 1) {
        println("$arrayName[$i] = ${arrayContent[i]}")
    }
}
