package com.example.arithmeticquizchamuditha

// importing required packages
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

import android.view.View


class New_Game_C : AppCompatActivity() {

    /**
     * to store the final sum of expression 1 and 2. this will be using to compare answers
     */
    var sumOf1:Int = 0
    var sumOf2:Int = 0

    var expression1 =ArrayList<String>()        // ArrayList to store output arrayList from expressionGen for expression 1
    var expression2 = ArrayList<String>()       // ArrayList to store output arrayList from expressionGen for expression 2


    lateinit var myTimer: CountDownTimer        // creating countDownTimer object to create the timer
    var time_default_milisecs = 0L              // default time set to 0 mili secs

    var totalQuestions:Int = 0          // total number of questions answered
    var correct_answers:Int = 0         // number of correct answers

    var exformula1:String =""           // to store generated expression 1
    var exformula2:String =""           // to store generated expression 2

    var timerSelector:Int=0             // timer selector when screen rotated. else timer starting function will reset the value to 50s when rotated

    var count:Int=0                     // to count correct answers. by checking this can add 10s to timer.



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game_c)

        /**
         * txt1,txt2 expression displaying
         */
        val txt1 = findViewById<TextView>(R.id.textView2)
        val txt2 = findViewById<TextView>(R.id.textView3)

        val greatBtn = findViewById<Button>(R.id.bt3)           // greater button
        val equalBtn = findViewById<Button>(R.id.bt4)           // equal button
        val lessBtn = findViewById<Button>(R.id.bt5)            // less button

        /**
         * Generating expression 1 and 2
         */
        expression1 = expressionGen()
        expression2 = expressionGen()

        /**
         * stroing sums of expressions
         */
        sumOf1 = expression1.get(1).toInt()
        sumOf2 = expression2.get(1).toInt()

        /**
         * storing expression string
         */
        exformula1=expression1.get(0)
        exformula2=expression2.get(0)

        /**
         * this if is to check whether there is savedInstanceState. if available it will restore savedInstanceState
         */
        if(savedInstanceState!=null){
            count=savedInstanceState.getInt("count")
            totalQuestions = savedInstanceState.getInt("total")
            correct_answers = savedInstanceState.getInt("correct")
            sumOf1 = savedInstanceState.getInt("exp1Sum")
            sumOf2 =savedInstanceState.getInt("exp2Sum")
            exformula1 = savedInstanceState.getString("formula1").toString()
            exformula2 = savedInstanceState.getString("formula2").toString()
            time_default_milisecs = savedInstanceState.getLong("time")
            timerSelector=1

        }

        /**
         * setting expressions to textViews
         */
        txt1.text=exformula1
        txt2.text=exformula2


        timer_start(timerSelector)      // Starting the timer according to selector

        /**
         * Greater button on clicklistener
         */
        greatBtn.setOnClickListener {
            /**
             * calling comparison function to compare sums of expressions
             */
            comparison(1)
            expression1=expressionGen()
            expression2 = expressionGen()

            sumOf1 = expression1.get(1).toInt()
            sumOf2 = expression2.get(1).toInt()

            exformula1=expression1.get(0)
            exformula2=expression2.get(0)

            txt1.text=exformula1
            txt2.text=exformula2

            /**
             * checks the count of correct answers. it it's 5 then it will recreate timer with added 10secs
             */
            if(count==5){
                count=0
                myTimer.cancel()            // cancelling the current timer
                time_default_milisecs += 10000L
                startTimer(time_default_milisecs)       // starting new timer object with new time
            }

        }

        /**
         * equal button on click listener
         */
        equalBtn.setOnClickListener {
            comparison(2)
            expression1=expressionGen()
            expression2 = expressionGen()

            sumOf1 = expression1.get(1).toInt()
            sumOf2 = expression2.get(1).toInt()

            exformula1=expression1.get(0)
            exformula2=expression2.get(0)

            txt1.text=exformula1
            txt2.text=exformula2

            if(count==5){
                count=0
                myTimer.cancel()
                time_default_milisecs += 10000L
                startTimer(time_default_milisecs)
            }
        }

        lessBtn.setOnClickListener {
            comparison(3)
            expression1=expressionGen()
            expression2 = expressionGen()

            sumOf1 = expression1.get(1).toInt()
            sumOf2 = expression2.get(1).toInt()

            exformula1=expression1.get(0)
            exformula2=expression2.get(0)

            txt1.text=exformula1
            txt2.text=exformula2

            if(count==5){
                count=0
                myTimer.cancel()
                time_default_milisecs += 10000L
                startTimer(time_default_milisecs)
            }
        }


    }



    /**
     * --------------------------------------Expression Generate------------------------------------
     */

    fun expressionGen():ArrayList<String> {
        val operators = arrayOf("+", "-", "x", "/")
        val expressions: ArrayList<String> = ArrayList()
        var tempSum = 0

        /**
         * ---------------------Number of expressions-----------------------
         */
        val rand = Random()
        val numOfExpressions = rand.nextInt(2) + 2 // max 3 expressions in this case
        /**
         * -----------------------------------------------------------------
         */


        /**
         * ---------------Expression generation------------------
         * adding expressions to expressions ArrayList
         */
        var selector_val = rand.nextInt(2) // Expression generate method selector

        /**
         * this for loop runs the length of numOfExpressions times. (terms 0 to term count)
         */
        for (i in 0 until numOfExpressions) {

            var exp1 = ""
            /**
             * Expression generate method 1 (With brackets)
             * validating inputs
             * creating one term with bracket
             */
            if (selector_val == 1) {
                val x = rand.nextInt(19) + 1
                var y = rand.nextInt(19) + 1
                val randOpr1 = rand.nextInt(4)
                val opr1 = operators[randOpr1]

                if (opr1 == "+") {
                    tempSum = x + y
                } else if (opr1 == "-") {
                    tempSum = x - y
                } else if (opr1 == "x") {
                    while (true) {
                        tempSum = x * y
                        y = if (tempSum > 100) {
                            rand.nextInt(19) + 1
                        } else {
                            break
                        }
                    }
                } else if (opr1 == "/") {
                    while (true) {
                        tempSum = x / y
                        y = if (x % y != 0) {
                            rand.nextInt(19) + 1
                        } else {
                            break
                        }
                    }
                }

                exp1 = "($x$opr1$y)"        // created term string
                expressions.add(exp1)
                selector_val = 2        // changing the method selector to add only one term with brackets

            } else {
                /**
                 * Expression generate without brackets
                 */
                val x = rand.nextInt(19) + 1
                exp1 = Integer.toString(x)
                expressions.add(exp1)

                // storing first element of expression array as temporary sum
                // if first element is string with two terms it will generate exception
                try {
                    run { tempSum = expressions[0].toInt() }
                } catch (ignored: Exception) {
                }
            }


        }

        println("Expression Array : $expressions")
        println()

        /**
         * --------Final Expression string build---------
         */
        var finalExpression = ""

        var method = 0

        // if expression ArrayList size is 2 or less method is always 2
        if (expressions.size <= 2) {
            method = 2
        }

        // method 1 for add another layer of brackets
        if (expressions.size >= 3) {
            method = 1
        }

        println("Method : $method")
        println("-----------------")

        /**
         * method 1
         * Adding another layer of brackets
         * adding single values at the end
         */
        if (method == 1) {
            // expression count that goes to outside bracket. it's minimum 2 and max is (array.size - 1). max is 2 for this case. here always 2
            val expToBracket = rand.nextInt(expressions.size - 2) + 2

            // outside bracket starts
            finalExpression = "$finalExpression("

            /**
             * adding expression to outside bracket. expToBracket is the limit. here it's always 2
             */
            for (i in 0 until expToBracket) {
                val randOpr1 = rand.nextInt(4)
                val opr1 = operators[randOpr1]
                finalExpression = finalExpression + expressions[i]
                if (i < expToBracket - 1) {
                    if (opr1 == "+") {
                        while (true) {
                            if (tempSum + expressions[i + 1].toInt() > 100) {
                                val newVal = rand.nextInt(19) + 1
                                expressions[i + 1] = Integer.toString(newVal)
                            } else {
                                tempSum = tempSum + expressions[i + 1].toInt()
                                break
                            }
                        }
                    } else if (opr1 == "-") {
                        tempSum = tempSum - expressions[i + 1].toInt()
                    } else if (opr1 == "x") {
                        while (true) {
                            if (tempSum * expressions[i + 1].toInt() > 100) {
                                val newVal = rand.nextInt(19) + 1
                                expressions[i + 1] = Integer.toString(newVal)
                            } else {
                                tempSum = tempSum * expressions[i + 1].toInt()
                                break
                            }
                        }
                    } else if (opr1 == "/") {
                        while (true) {
                            if (tempSum % expressions[i + 1].toInt() != 0) {
                                val newVal = rand.nextInt(19) + 1
                                expressions[i + 1] = Integer.toString(newVal)
                            } else {
                                tempSum = tempSum / expressions[i + 1].toInt()
                                break
                            }
                        }
                    }
                }


                // checking before adding operator at the end.
                if (i < expToBracket - 1) {
                    finalExpression = finalExpression + opr1
                }
            }

            /**
             * adding operator at the end of bracket close
             */
            val randOprOut = rand.nextInt(4)
            val oprOut = operators[randOprOut]
            finalExpression = "$finalExpression)$oprOut"


            /**
             * adding left values in expressions ArrayList
             * loop start value is expToBracket explained above. expToBracket=2 , expression.size=3
             */
            for (i in expToBracket until expressions.size) {
                if (oprOut == "+") {
                    while (true) {
                        if (tempSum + expressions[i].toInt() > 100) {
                            val newVal = rand.nextInt(19) + 1
                            expressions[i] = Integer.toString(newVal)
                        } else {
                            tempSum = tempSum + expressions[i].toInt()
                            break
                        }
                    }
                } else if (oprOut == "-") {
                    tempSum = tempSum - expressions[i].toInt()
                } else if (oprOut == "x") {
                    while (true) {
                        if (tempSum * expressions[i].toInt() > 100) {
                            val newVal = rand.nextInt(19) + 1
                            expressions[i] = Integer.toString(newVal)
                        } else {
                            tempSum = tempSum * expressions[i].toInt()
                            break
                        }
                    }
                } else if (oprOut == "/") {
                    while (true) {
                        if (tempSum % expressions[i].toInt() != 0) {
                            val newVal = rand.nextInt(19) + 1
                            expressions[i] = Integer.toString(newVal)
                        } else {
                            tempSum = tempSum / expressions[i].toInt()
                            break
                        }
                    }
                }

                // adding term to expression string
                finalExpression = finalExpression + expressions[i]


                if (i < expressions.size - 1) {
                    val randOpr1 = rand.nextInt(4)
                    val opr1 = operators[randOpr1]
                    finalExpression = finalExpression + opr1
                }
            }
        }

        //--------------------------------------------------------------------------------------
        /**
         * method 2
         * adding all the expressions in ArrayList to the string without adding anymore brackets
         */
        if (method == 2) {
            // expression size = 2
            for (i in 0 until expressions.size) {
                finalExpression = finalExpression + expressions[i]
                val randOpr1 = rand.nextInt(4)
                val opr1 = operators[randOpr1]
                if (i < expressions.size - 1) {
                    if (opr1 == "+") {
                        while (true) {
                            if (tempSum + expressions[i + 1].toInt() > 100) {
                                val newVal = rand.nextInt(19) + 1
                                expressions[i + 1] = Integer.toString(newVal)
                            } else {
                                tempSum = tempSum + expressions[i + 1].toInt()

                                break
                            }
                        }
                    } else if (opr1 == "-") {
                        tempSum = tempSum - expressions[i + 1].toInt()

                    } else if (opr1 == "x") {
                        while (true) {
                            if (tempSum * expressions[i + 1].toInt() > 100) {
                                val newVal = rand.nextInt(19) + 1
                                expressions[i + 1] = Integer.toString(newVal)
                            } else {
                                tempSum = tempSum * expressions[i + 1].toInt()

                                break
                            }
                        }
                    } else if (opr1 == "/") {
                        while (true) {
                            if (tempSum % expressions[i + 1].toInt() != 0) {
                                val newVal = rand.nextInt(19) + 1
                                expressions[i + 1] = Integer.toString(newVal)
                            } else {
                                tempSum = tempSum / expressions[i + 1].toInt()

                                break
                            }
                        }
                    }
                }

                if (i < expressions.size - 1) {
                    finalExpression = finalExpression + opr1
                }

            }
        }

        println(finalExpression)
        println(tempSum)

        // converting to String to store in String ArrayList
        var stringSum:String = tempSum.toString()
        var out_puts = ArrayList<String>()

        // adding to out_puts arrayList
        out_puts.add(finalExpression)
        out_puts.add(stringSum)

        // returning arrayList
        return out_puts
    }


    /**
     * -------------------------------------------Comparison----------------------------------------
     */

    fun comparison(btn:Int) {
        val txt3 = findViewById<TextView>(R.id.textView4)

        if(btn==1){
            if(sumOf1>sumOf2){
                txt3.text="Correct"
                correct_answers+=1
                totalQuestions+=1
                txt3.setTextColor(Color.GREEN)
                count+=1                                         // adding count if answer is correct
            }else{
                txt3.text="Wrong"
                totalQuestions+=1
                txt3.setTextColor(Color.RED)
            }
        }

        if(btn==2){
            if(sumOf1==sumOf2){
                txt3.text="Correct"
                correct_answers+=1
                totalQuestions+=1
                txt3.setTextColor(Color.GREEN)
                count+=1
            }else{
                txt3.text="Wrong"
                totalQuestions+=1
                txt3.setTextColor(Color.RED)
            }
        }

        if(btn==3){
            if(sumOf1<sumOf2){
                txt3.text="Correct"
                correct_answers+=1
                totalQuestions+=1
                txt3.setTextColor(Color.GREEN)
                count+=1
            }else{
                txt3.text="Wrong"
                totalQuestions+=1
                txt3.setTextColor(Color.RED)
            }
        }


    }

    /**
     * -------------------------------------------Timer---------------------------------------------
     */

    fun timer_start(timer_selector:Int){

        if(timer_selector==0){
            val time  = "5"
            time_default_milisecs = time.toLong() *10000L       // default time 50secs
            startTimer(time_default_milisecs)
        }else{
            startTimer(time_default_milisecs)                   // timer without default 50sec. when rotating device
        }



    }

    private fun startTimer(time_in_seconds: Long) {
        val greatBtn = findViewById<Button>(R.id.bt3)
        val equalBtn = findViewById<Button>(R.id.bt4)
        val lessBtn = findViewById<Button>(R.id.bt5)

        val txtView1 = findViewById<TextView>(R.id.textView2)
        val txtView2 = findViewById<TextView>(R.id.textView3)
        val txtView3 = findViewById<TextView>(R.id.textView4)

        val txtView4 = findViewById<TextView>(R.id.textView5)

        // creating countDownTimer object
        myTimer = object : CountDownTimer(time_in_seconds, 1000) {

            /**
             * once timer runs out some layout views will become invisible and show the score
             */
            override fun onFinish() {
                println("Done")

                greatBtn.visibility=View.INVISIBLE
                equalBtn.visibility=View.INVISIBLE
                lessBtn.visibility=View.INVISIBLE

                txtView1.visibility=View.INVISIBLE
                txtView2.visibility=View.INVISIBLE
                txtView3.visibility=View.INVISIBLE

                txtView4.visibility=View.VISIBLE
                txtView4.text="Score = $correct_answers/$totalQuestions"

            }

            /**
             * updating text on each tick
             */
            override fun onTick(p0: Long) {
                time_default_milisecs = p0
                timerTextViewUpdate()          // calling method to update the value in text view
            }
        }

        // starting the timer
        myTimer.start()
    }

    // function to update text view used to display timer
    private fun timerTextViewUpdate() {
        val minute = (time_default_milisecs / 1000) / 60    // converting mili seconds to mins
        val seconds = (time_default_milisecs / 1000) % 60   // taking the remaining as seconds

        // setting the timer in text view
        val timer = findViewById<TextView>(R.id.timer)
        timer.text = "$minute:$seconds"
    }


    /**
     * to save instance state
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("total",totalQuestions)
        outState.putInt("correct",correct_answers)
        outState.putInt("exp1Sum",sumOf1)
        outState.putInt("exp2Sum",sumOf2)
        outState.putLong("time",time_default_milisecs)
        outState.putString("formula1",exformula1)
        outState.putString("formula2",exformula2)
        outState.putInt("count",count)

    }

    /**
     * to restore instance state
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.getInt("total",totalQuestions)
        savedInstanceState.getInt("correct",correct_answers)
        savedInstanceState.getInt("count",count)
        savedInstanceState.getInt("exp1Sum",sumOf1)
        savedInstanceState.getInt("exp2Sum",sumOf2)
        savedInstanceState.getString("formula1",exformula1)
        savedInstanceState.getString("formula2",exformula2)
        savedInstanceState.getLong("time",time_default_milisecs)

    }

}

// References: CountDownTimer
// https://medium.com/@olajhidey/working-with-countdown-timer-in-android-studio-using-kotlin-39fd7826e205

//ML Chamuditha - w1809779