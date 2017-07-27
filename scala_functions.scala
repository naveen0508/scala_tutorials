// Databricks notebook source
// MAGIC %md
// MAGIC 
// MAGIC Functions:
// MAGIC 
// MAGIC One of the key foundational elements of good
// MAGIC problem solving is problem decomposition. The idea of problem decomposition is that good
// MAGIC solutions of large, complex problems are built out of solutions to smaller, more manageable
// MAGIC problems. This is especially important in programming because the problems can be arbitrarily
// MAGIC complex and, at the base, we have to break them down to the level where we can
// MAGIC communicate them to the computer, which is fundamentally quite simplistic.
// MAGIC 
// MAGIC One of the key elements of problem decomposition in programming is the function. A
// MAGIC function is a collection of statements to solve a specific problem that we can call as needed.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC There are a number of ways to make functions in Scala. We will begin with the standard
// MAGIC definition format.
// MAGIC 
// MAGIC We use the keyword def to define a function.
// MAGIC 
// MAGIC We use the name
// MAGIC square here because it describes what the function does. The name is written after the def
// MAGIC and after that we have a set of parentheses that contain a name and a type separated by
// MAGIC a colon.
// MAGIC 
// MAGIC After the parentheses is another colon and the type that this function gives us back, or
// MAGIC returns. In Scala we refer to this as the result type.

// COMMAND ----------

def square(x:Double):Double = x*x

// COMMAND ----------

//using the defined function
square(5)

// COMMAND ----------

def cal(x:Int,y:Int):Int = x+y*y

// COMMAND ----------

cal(2,5) //2+5*5

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC In this example we used simple literals for the arguments to the function, but the arguments
// MAGIC can be any expression with the proper type. For example, we could do the following:

// COMMAND ----------

val a = 5

// COMMAND ----------

cal(a+5, square(2).toInt)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Here we show that the arguments to cal can include a reference to a val or a call to the
// MAGIC square function. The call to square needs to be followed by a conversion to an Int because
// MAGIC cal is declared to take type Int and the result of square is Double. If you leave that out,
// MAGIC you will get an error like this:

// COMMAND ----------

cal(a+5, square(2))

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC With functions, the types on the parameters are
// MAGIC required. The result type is not required as it can often be inferred, but it is considered
// MAGIC better style to specify it.1 The reason this is preferred is that if you tell Scala a type and
// MAGIC make a mistake in the function that causes it to result in a different type, Scala can tell you
// MAGIC earlier that something has gone wrong.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC These functions were limited in scope. We can write longer functions. To do this, we
// MAGIC need to employ code blocks using curly braces. Going back to the English analogy, a block
// MAGIC of code probably resembles a paragraph. This is a weak analogy because a block is not only
// MAGIC composed of statements, it is a statement itself and can be an expression. When used as
// MAGIC an expression, the value you get from it is the value of the last expression in the block.

// COMMAND ----------

def secToTimeString(totalSeconds:Int):String = {
  val displaySeconds = totalSeconds%60
  val totalMinutes = totalSeconds/60
  val displayMinutes = totalMinutes%60
  val displayHours = totalMinutes/60
  val sec = displaySeconds.toString
  val min = displayMinutes.toString
  displayHours+":"+("0"*(2-min.length))+min+":"+("0"*(2-sec.length))+sec
}

// COMMAND ----------

secToTimeString(1234565)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC This function takes a single Int of the number of seconds. It results in a String. After the
// MAGIC equal sign is an open curly brace that opens a block of code. This block is closed at the end
// MAGIC of the function after an expression that gives us the String we want to return.
// MAGIC 
// MAGIC **Return keyword can also be mentioned for returning the value.**

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC This code also does something else that we have never seen before, it declares variables
// MAGIC inside of a function. When we do this the variables are called local variables and they
// MAGIC can only be accessed inside of the function. If, after typing in this function, you try to use
// MAGIC sec, or any of the other local variables, outside the function, you will get an error message.

// COMMAND ----------

sec

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC The part of the code over which a name can be accessed is called the scope of that name.
// MAGIC When you declare a variable or a function in a script outside any block of code, it has a
// MAGIC scope that goes through the rest of the script. If you declare it in a block of code, it has a
// MAGIC scope that goes from the point of the declaration to the end of the block it is declared in.
// MAGIC A block is denoted with curly braces, so you can use the name until you get to the right
// MAGIC curly brace that closes off the pair the name is declared in. As a general rule, you want to
// MAGIC minimize the scope of variables so they can only be accessed where they are needed. This
// MAGIC reduces the amount of code you have to look through if something goes wrong with that
// MAGIC variable.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC These examples utilize the mathematical concept of a function as something that takes
// MAGIC input values and maps them to outputs values. In programming it is possible for functions
// MAGIC to do things other than give back a value. We have already seen an example of such a
// MAGIC function. Our first program called the function println. You might notice that if you use
// MAGIC println in the REPL, a value is printed, but there is not a result value shown. This is
// MAGIC because println gives us back a value of type Unit.
// MAGIC When you write a function that results in Unit, you can use the normal syntax and put
// MAGIC :Unit after the parameter list. **An alternate, shorter syntax is to leave out the equals sign,
// MAGIC and just have a block of code. The following is a simple example of this.**

// COMMAND ----------

def printMultiple(s:String, howMany:Int){
  println(s*howMany)
}

// COMMAND ----------

printMultiple("hello", 5)

// COMMAND ----------

// MAGIC %md
// MAGIC Example:
// MAGIC 
// MAGIC The course average is calculated by combining the
// MAGIC average of a number of different grades. For example, you might have tests, assignments,
// MAGIC and quizzes. Each of these might contribute a different fraction of the total grade. The
// MAGIC tests might be worth 40%, assignments worth 40%, and quizzes worth 20%. You will also
// MAGIC have a different number of each of these grades. For example, you might have 2 tests, 3
// MAGIC assignments, and 4 quizzes. In many courses, part of the course average is computed by
// MAGIC dropping a lowest grade and taking the average of what remains. In this case, let us assume
// MAGIC that the lowest quiz grade is dropped.
// MAGIC What we want to do is write a function that takes all the grades, and returns the average
// MAGIC for the course. To start with, it might look something like this:

// COMMAND ----------

def courseAverage(test1:Double,test2:Double,assn1:Double,
  assn2:Double,assn3:Double,quiz1:Double,quiz2:Double,
  quiz3:Double,quiz4:Double):Double = {
  val testAve=(test1+test2)/2
  val assnAve=(assn1+assn2+assn3)/3
  val minQuiz=quiz1 min quiz2 min quiz3 min quiz4
  val quizAve=(quiz1+quiz2+quiz3+quiz4-minQuiz)/3
  testAve*0.4+assnAve*0.4+quizAve*0.2
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC All that is left to do is to figure out the minimum quiz grade. To accomplish this, we will
// MAGIC use a method called min that is defined on the Int type. **If you have two Int values, a and
// MAGIC b, then the expression a min b will give you the smaller of the two values. This is a call to
// MAGIC the min method on Int and could be written as a.min(b). However, the operator syntax
// MAGIC where the dot and parentheses are left off is superior when we have multiple values because
// MAGIC we can put the values in a row to get the smallest of all of them.** This gives us a complete
// MAGIC version of the function which looks like the following.

// COMMAND ----------

courseAverage(90,80,95,76,84,50,70,36,89)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Problem Decomposition:
// MAGIC 
// MAGIC Programming is about problem solving. One of the major aspects
// MAGIC of problem solving is breaking hard problems into smaller pieces. There are a number of
// MAGIC reasons to do this. The most obvious reason is that big problems are hard to solve. If you
// MAGIC can break a big problem into smaller pieces, it is often easier to solve the pieces and then
// MAGIC build an answer to the full problem from the answers to the pieces. This can be repeated
// MAGIC on each subproblem until you get to a level where the problems are simple to solve.
// MAGIC 
// MAGIC This allows you to potentially reuse pieces of code. The ability to
// MAGIC reuse existing code is critical in programming.
// MAGIC 
// MAGIC A third advantage to decomposing a problem is that the resulting code can be easier
// MAGIC to understand and modify.

// COMMAND ----------

// MAGIC %md
// MAGIC Decomposing the above example:

// COMMAND ----------

def testAve(test1:Double,test2:Double):Double=(test1+test2)/2

// COMMAND ----------

def assnAve(assn1:Double,assn2:Double,assn3:Double):Double =
  (assn1+assn2+assn3)/3

// COMMAND ----------

def quizAve(quiz1:Double,quiz2:Double,quiz3:Double,quiz4:Double):Double = {
  val minQuiz=quiz1 min quiz2 min quiz3 min quiz4
  (quiz1+quiz2+quiz3+quiz4-minQuiz)/3
}

// COMMAND ----------

def fullAve(test:Double,assn:Double,quiz:Double):Double =
  test*0.4+assn*0.4+quiz*0.2

// COMMAND ----------

def courseAverage(test1:Double,test2:Double,assn1:Double,
assn2:Double,assn3:Double,quiz1:Double,quiz2:Double,
quiz3:Double,quiz4:Double):Double = {
  val test=testAve(test1,test2)
  val assn=assnAve(assn1,assn2,assn3)
  val quiz=quizAve(quiz1,quiz2,quiz3,quiz4)
  fullAve(test,assn,quiz)
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Other example was for converting a number of seconds into a properly formatted
// MAGIC time. When we originally discussed this, we broke it into two problems. First, we had to
// MAGIC figure out how many seconds, minutes, and hours a given number of seconds was equal to.
// MAGIC After that we had to format the string properly. We will maintain that separation of work
// MAGIC with functions. If we write this in a file, it might look like the following.

// COMMAND ----------

def calcHMS(totalSeconds:Int):(Int,Int,Int) = {
  val displaySeconds=totalSeconds%60
  val totalMinutes=totalSeconds/60
  val displayMinutes=totalMinutes%60
  val displayHours=totalMinutes/60
  (displayHours,displayMinutes,displaySeconds)
}

def formatHMS(numHours:Int,numMinutes:Int,numSeconds:Int):String = {
  val sec=numSeconds.toString
  val min=numMinutes.toString
  numHours+":"+("0"*(2-min.length))+min+":"+ ("0"*(2-sec.length))+sec
}

def secondsToTimeString(totalSeconds:Int):String = {
  val (h,m,s)=calcHMS(totalSeconds)
  formatHMS(h,m,s)
}

// COMMAND ----------

// MAGIC %md 
// MAGIC 
// MAGIC This approach of taking a bigger problem and breaking it down into pieces is often
// MAGIC called a top-down approach. The mental image is that you have the full problem at the top
// MAGIC and in each level below it you have smaller pieces that are combined to make a solution
// MAGIC to the level above them. This structure stops at the bottom when you get to something
// MAGIC that can be solved relatively easily. In contrast to top-down, it is also possible to approach
// MAGIC problems from the bottom-up. This approach works when you have a certain familiarity
// MAGIC with solving a particular type of problem and you know what types of pieces you will need
// MAGIC or when your top-level problem is not completely well defined and you have to try some
// MAGIC different approaches. This can happen when you are building software for a customer, as
// MAGIC the customer may have only a vague idea of what they want their program to look like. In
// MAGIC that situation, you can build pieces you know will be useful and try putting them together
// MAGIC in different ways until you find something that the customer is happy with.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC This example shows that the problem probably contains some reusable parts. There
// MAGIC are several boxes at the bottom that start with “Grab” or “Walk to”. Ideally we would find
// MAGIC a way to write a function of walking that could be passed information on where to go.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC • Make PB&J Sandwich
// MAGIC 
// MAGIC 1. Collect Ingredients
// MAGIC 
// MAGIC (a) Get Pantry Items
// MAGIC 
// MAGIC i. Walk to Pantry
// MAGIC 
// MAGIC ii. Grab Peanut Butter
// MAGIC 
// MAGIC iii. Grab Bread
// MAGIC 
// MAGIC (b) Get Fridge Items
// MAGIC 
// MAGIC i. Walk to Fridge
// MAGIC 
// MAGIC ii. Grab Jelly
// MAGIC 
// MAGIC (c) Get Other Items
// MAGIC 
// MAGIC i. Walk to Cupboard
// MAGIC 
// MAGIC ii. Grab Plate
// MAGIC 
// MAGIC iii. Grab Knife
// MAGIC 
// MAGIC 2. Assemble Sandwich
// MAGIC 
// MAGIC (a) Put Bread on Plate
// MAGIC 
// MAGIC i. Open Bread Bag
// MAGIC 
// MAGIC ii. Pull Two Slices from Bag
// MAGIC 
// MAGIC iii. Place Bread on Plate
// MAGIC 
// MAGIC iv. Close Bread Bag
// MAGIC 
// MAGIC (b) Spread Peanut Butter
// MAGIC 
// MAGIC i. Remove Lid from Peanut Butter
// MAGIC 
// MAGIC ii. Repeat Until Bread is Covered
// MAGIC 
// MAGIC A. Extract Peanut Butter on Knife
// MAGIC 
// MAGIC B. Spread Peanut Butter on Bread
// MAGIC 
// MAGIC iii. Replace Peanut Butter Lid
// MAGIC 
// MAGIC (c) Spread Jelly
// MAGIC 
// MAGIC i. Remove Lid from Jelly
// MAGIC 
// MAGIC ii. Repeat Until Bread is Covered
// MAGIC 
// MAGIC A. Extract Jelly on Knife
// MAGIC 
// MAGIC B. Spread Jelly on Bread
// MAGIC 
// MAGIC iii. Replace Jelly Lid
// MAGIC 
// MAGIC (d) Put Bread Slices Together with Peanut Butter Against Jelly
// MAGIC 
// MAGIC 3. Clean Up
// MAGIC 
// MAGIC (a) Return Pantry Items
// MAGIC 
// MAGIC i. Walk to Pantry
// MAGIC 
// MAGIC ii. Replace Peanut Butter
// MAGIC 
// MAGIC iii. Replace Bread
// MAGIC 
// MAGIC (b) Return Fridge Items
// MAGIC 
// MAGIC i. Walk to Refrigerator
// MAGIC 
// MAGIC ii. Replace Jelly
// MAGIC 
// MAGIC (c) Return Other Items
// MAGIC 
// MAGIC i. Walk to Sink
// MAGIC 
// MAGIC ii. Put Knife in Sink

// COMMAND ----------

def itemCost(item:String, size:String):Double = {
    if(item=="Drink") {
      if(size=="S") 0.99
      else if(size=="M") 1.29
      else 1.39
  } else if(item=="Side") {
    if(size=="S") 1.29
    else if(size=="M") 1.49
    else 1.59
  } else if(item=="Main") {
    if(size=="S") 1.99
    else if(size=="M") 2.59
    else 2.99
  } else {
    if(size=="S") 4.09
    else if(size=="M") 4.99
    else 5.69
  }
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC To understand, consider the simple example of wanting to know how much should be
// MAGIC paid for two items.

// COMMAND ----------

println("What is the first item?")
val item1 = "Drink" //readLine()
println("What size?")
val size1 = "M" // readLine()
println("What is the second item?")
val item2 = "Side" // readLine()
println("What size?")
val size2 = "S" // readLine()
val totalCost = itemCost(item1,size1)+itemCost(item2,size2)
println("The total cost is "+totalCost)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Function Literals:
// MAGIC 
// MAGIC The syntax for a function literal starts with a set of parentheses that have a comma
// MAGIC separated list of arguments in them followed by an arrow made from an equals sign and a
// MAGIC greater than sign with the body of the function after that. This type of arrow is often read
// MAGIC as “rocket”. So if we go back to our first two functions we might write them in this way.

// COMMAND ----------

(x:Double) => x*x

// COMMAND ----------

(x:Int, y:Int) => x+y*y

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC In theory one can use function literals for functions of any length,
// MAGIC in practice this is a style issue and you will not want to use function literals that are too
// MAGIC long. The function literals will be embedded in other functions and you do not want those
// MAGIC functions to exceed a screen size in length so the literals should not be more than a few
// MAGIC lines at most.

// COMMAND ----------

Scala has an even shorter form for some special case function literals. This form uses
underscores to represent parameters and skips the parameter list and rocket completely. It
can only be used when each parameter is used only once and in the same order they are
passed in. In this form, the function literal (x:Int,y:Int)=>x+y could be represented as
((_:Int)+(_:Int)). When Scala can figure out the types, we can use the even shorter form
of (_+_). Note that this can not be used for x2 written as x*x because we use the x twice. It
has significant limitations, but there are many situations where it is useful and convenient
to use.

// COMMAND ----------

((_:Int)+(_:Int))

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Non-Functional Functions/Procedures:
// MAGIC 
// MAGIC The title of this section might seem odd. How could a function not be functional? In
// MAGIC purely functional programming, a function takes inputs and calculates a value. The value
// MAGIC it returns depends only on the arguments and the function does not do anything but give
// MAGIC back that value. Often in programming, functions will do other things as well, or might
// MAGIC return different values for the same inputs. These types of functions are not technically
// MAGIC “functional”. When they do other things we refer to that as side effects. This terminology
// MAGIC is like that for medications. You take a medication because it has a certain desired effect.
// MAGIC However, it might have certain other effects as well called the side effects. While the side
// MAGIC effects of a medication typically are not desired, there are times when side effects of functions
// MAGIC are desired. The simplest example of this is printing. The print statement does not return
// MAGIC anything. Instead it sends information to output. That is a side effect.
// MAGIC 
// MAGIC It is possible to have functions that return a value and have side effects. However, quite
// MAGIC frequently you will find that functions with side effects do not return any data. To Scala
// MAGIC that means their return type is **Unit**. Here is a simple example of such a function.

// COMMAND ----------

def introduce(name:String):Unit = {
  print("Hi, my name is "+name+".")
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC This is common in non-functional programming. Indeed, some languages include a special
// MAGIC construct, the procedure, for this. Scala does not have a separate type of function, but it
// MAGIC does allow the shorter syntax we mentioned earlier. That makes our earlier function look
// MAGIC like this instead:

// COMMAND ----------

def introduce(name:String){
  print("Hi, my name is "+name+".")
}

// COMMAND ----------

introduce("Sam")

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Higher-Order Functions:
// MAGIC 
// MAGIC Function literals would be nothing more than a novelty in Scala or any other programming
// MAGIC language if it were not for higher-order functions. A higher-order function
// MAGIC is a function that operates on other functions. This means that either we pass other
// MAGIC functions into it, or it results in a function. Higher-order functions are a functional
// MAGIC programming concept and they can be challenging to get ones head around, but we will
// MAGIC in the coming chapters how they can be useful.

// COMMAND ----------

def compose(f:Double => Double, g:Double => Double):Double => Double = (x) => f(g(x))

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Note that we write the function types themselves using arrows. We could call this with
// MAGIC either functions we build using def or function literals. We will do it here with the
// MAGIC following two functions that have been defined using def.

// COMMAND ----------

def plus5(x:Double):Double = x+5

// COMMAND ----------

def square(x:Double):Double = x*x

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Now we want to use compose to build new functions from these two using the compose
// MAGIC function and then see our new functions working.

// COMMAND ----------

val h=compose(plus5,square)

// COMMAND ----------

val j=compose(square,plus5)

// COMMAND ----------

h(3)

// COMMAND ----------

j(3)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC The function h(x) = x2 + 5 while j(x) = (x + 5)2. You can see that when we call
// MAGIC these with an argument of 3, we get values of 14 and 64 respectively, just as would be
// MAGIC expected.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC type Declarations:
// MAGIC 
// MAGIC Scala provides a mechanism that allows you to provide alternate names for types. This
// MAGIC can provide significant power when combined with concepts like abstraction.
// MAGIC 
// MAGIC The syntax of a type declaration begins with the keyword type. This is followed by
// MAGIC the name you want to give to use. As with other names, you should pick something that is
// MAGIC meaningful to you so that when you are reading through code, having this name helps it to
// MAGIC make more sense. Unlike var, val, and def, it is the general style for type names to being
// MAGIC with capital letters. You have probably noticed that the names of all the types we have
// MAGIC encountered so far, like Int and Double, start with capital letters. This is a style choice,
// MAGIC not something that Scala enforces, but it is highly recommended that you follow it. After
// MAGIC the name is a equals sign and then the type you are giving a new name to.

// COMMAND ----------

type Vect = (Double, Double, Double)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC In the rest of your code, you could refer to the type Vect instead of having to type out
// MAGIC (Double, Double, Double). This does not change how you interact with the tuple. You
// MAGIC would still use the methods 1, 2, and 3 or a val with a tuple pattern to get the values
// MAGIC out of the Vect.
// MAGIC 
// MAGIC There can even be a value to using a type declaration to give a different name to a
// MAGIC standard type like Int like this.

// COMMAND ----------

type Counter = Int

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC If you carefully use the Counter type through your program in places that call for it, you
// MAGIC can easily change the size of the integer type that is being used. If you find that you need
// MAGIC your counters to be able to go over Int.MaxValue, you can switch to using a Long. On the
// MAGIC other hand, if your counters are always small and you have a need to save memory, you
// MAGIC could consider going down to a Short. This type of usage is not common is Scala, but it is
// MAGIC used significantly in C when writing libraries so that it is easy to modify types to fit the
// MAGIC platform you are compiling for.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Summary of Concepts
// MAGIC 
// MAGIC • Functions are used to break problems up into smaller pieces.
// MAGIC 
// MAGIC – Help solve problems.
// MAGIC 
// MAGIC – Informative names make code more understandable.
// MAGIC 
// MAGIC – Smaller functions are easier to work with.
// MAGIC 
// MAGIC • The functions in programming are similar to those from math. Information is passed
// MAGIC in through formal parameters. The value of the parameters is determined at the time
// MAGIC the function is called when arguments are passed in.
// MAGIC 
// MAGIC • Functions are declared in Scala using def. This is followed by the function name,
// MAGIC an argument list, a result type, and then an equals sign with the expression for the
// MAGIC function. Result type can be left off and inferred, but it is recommended you include
// MAGIC it anyway.
// MAGIC 
// MAGIC • Functions can also be written as literals in Scala.
// MAGIC 
// MAGIC – The rocket notation has argument list and body separated by =>.
// MAGIC 
// MAGIC – Shorter notation uses underscores for arguments. Only works for certain functions.
// MAGIC 
// MAGIC • Functions that are called only for their side effects and do not need to return a value
// MAGIC return Unit. This is done enough that there is a simplified syntax where the colon,
// MAGIC result type, and equals sign are left off and curly braces go after the argument list.
// MAGIC 
// MAGIC • If you have functions that often take or return tuples, it can be useful to use a type
// MAGIC declaration to give shorter, meaningful names to the tuples.

// COMMAND ----------


