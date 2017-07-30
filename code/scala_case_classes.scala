// Databricks notebook source
// MAGIC %md
// MAGIC 
// MAGIC User-Defined Types:
// MAGIC 
// MAGIC User defined types are a common feature in modern programming languages and have
// MAGIC been for decades. Scala provides three constructs for creating user-defined types: classes,
// MAGIC traits, and singleton objects.
// MAGIC 
// MAGIC The user-defined types typically take the form of being collections
// MAGIC of other types. This makes them fundamentally similar to just using a tuple. Where they
// MAGIC prove to be more than just a tuple is the control they give you in determining what can be
// MAGIC done with the types.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Case Classes:
// MAGIC 
// MAGIC There can be more to the declaration of a case class, but for now we will limit ourselves
// MAGIC to this syntax that begins with the keywords “case class”. After that is the name you
// MAGIC want to give the type. This could be any valid Scala name, but it is customary to begin
// MAGIC type names with uppercase letters and use camel naming so all subsequent words also begin
// MAGIC with uppercase letters. After that is a list of name/type pairs in parentheses. The format
// MAGIC of these is just like the arguments to a function. The elements of this list give the names
// MAGIC and types of the values stored in this new type.

// COMMAND ----------

case class Point3D(x:Double,y:Double,z:Double)

// COMMAND ----------

case class Student(name:String,assignments:List[Double],tests:List[Double],quizzes:List[Double])

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Making Objects:
// MAGIC 
// MAGIC After you have declared a type, you need to be able to create objects of that type. With
// MAGIC a case class, you can do this with an expression that has the name of the class followed
// MAGIC by an argument list, just like a function call. The two classes listed above could be created
// MAGIC and stored in variables with the following lines of code.

// COMMAND ----------

val p = Point3D(1,2,3)

// COMMAND ----------

val s = Student("Mark",Nil,Nil,List(89))

// COMMAND ----------

// MAGIC %md
// MAGIC You could insert the word **new** and a space after the equals signs so that these lines look
// MAGIC like the following.

// COMMAND ----------

val p = new Point3D(1,2,3)
val s = new Student("Mark",Nil,Nil,List(89))

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Accessing Elements:
// MAGIC 
// MAGIC In order to be able to use these objects, we must be able to access the different elements
// MAGIC in them. This is very simple to do, just use the dot notation to access the elements.

// COMMAND ----------

p.x

// COMMAND ----------

s.name

// COMMAND ----------

// MAGIC %md
// MAGIC The dot notation in Scala simply means that you are using something from inside of an
// MAGIC object. It could be a method or a value that is stored in the object. For now we will only
// MAGIC be concerning ourselves with the values that we store in our case classes.
// MAGIC 
// MAGIC We could put this to use by writing a function to find the distance between two Point3Ds.
// MAGIC It might look something like this.

// COMMAND ----------

def distance(p1:Point3D,p2:Point3D):Double = {
  val dx=p1.x-p2.x
  val dy=p1.y-p2.y
  val dz=p1.z-p2.z
  math.sqrt(dx*dx+dy*dy+dz*dz)
}

// COMMAND ----------

def classAverage(s:Student):Double = {
  val assnAve=if(s.assignments.isEmpty) 0.0
    else s.assignments.sum/s.assignments.length
  val quizAve=if(s.quizzes.length<2) 0.0
    else (s.quizzes.sum-s.quizzes.min)/(s.quizzes.length-1)
  val testAve=if(s.tests.isEmpty) 0.0
    else s.tests.sum/s.tests.length
  0.5*assnAve+0.3*testAve+0.2*quizAve
}

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC One of the things to note about case classes is that the elements in them are vals.
// MAGIC As such, you can not change what they refer to. If you try to make such a change you get
// MAGIC something like the following.

// COMMAND ----------

p.x = 45

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Whether you can change anything in an object created from a case class depends on
// MAGIC whether the things in it are mutable or not. In our two examples, all of the contents are
// MAGIC immutable. As a result, the case class as a whole is immutable. Once you create a Point3D
// MAGIC or a Student, the object you create can not change its value in any way. However, if one
// MAGIC or more of the fields in the case class were an Array, then the values in the Array would
// MAGIC be mutable. You would not be able to change the size of the Array without making a new
// MAGIC object, but you could change the values stored in it.

// COMMAND ----------

// MAGIC %md
// MAGIC Named and Default Arguments (Advanced)
// MAGIC A few options were added to Scala in version 2.8 in regards to function arguments
// MAGIC and calling functions. Normally, Scala figures out which of the arguments passed into
// MAGIC a function is associated with which formal parameter by their order. Consider this
// MAGIC function.

// COMMAND ----------

def evalQuadratic(a:Double,b:Double,c:Double,x:Double):Double = {
  val x2=x*x
  a*x2+b*x+c
}

// COMMAND ----------

evalQuadratic(2,3,4,5)

// COMMAND ----------

//named arguments
evalQuadratic(a=2,b=3,c=4,x=5)

// COMMAND ----------

evalQuadratic(x=5,a=2,b=3,c=4)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC For some functions there are some arguments that will have a particular value a lot
// MAGIC of the time. In that situation, it is nice to make it so that people calling the function
// MAGIC do not have to provide them and they will use a default value. When you declare
// MAGIC the function simply follow the type with an equals sign and the value you want to have
// MAGIC for the default. If the caller is happy with the default value, then that argument can be
// MAGIC left out. Default arguments at the end of the list can be simply omitted. If they are in
// MAGIC the middle then you will have to use named arguments to specify any arguments after
// MAGIC them in the list. Consider a function to add a grade to a Student.

// COMMAND ----------

def addGrade(name:String,grade:Int = 0):Student = ...

//Here the default grade is a zero. So this function can be called in two ways.

addGrade("Jane",95)
addGrade("Joe")

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC The copy Method
// MAGIC 
// MAGIC The fact that you can not mutate the values in a case class means that it would be
// MAGIC helpful to have a way to make new case class objects that are only slightly changed from
// MAGIC others. To see this, consider what happens when you want to add a new grade to a Student.
// MAGIC The grades are in Lists, and it is easy to add to a List. The problem is, that does not
// MAGIC mutate what is in the original List, it just gives us a new List that includes the new values
// MAGIC as well as what was already there.
// MAGIC 
// MAGIC To help get around this problem, case classes come with a copy method. The arguments to copy have the same names as the fields in the class. Using named
// MAGIC arguments, you only provide the ones you want to change. Anything you leave out will be
// MAGIC copied straight over to the new object. So using the Student object we gave the name s
// MAGIC above, we could use copy to do the following.

// COMMAND ----------

val ns = s.copy(tests = 99::s.tests)

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC This gives us back a new Student who is the same as the one we had in s, only it has a
// MAGIC test grade of 99 in addition to the quiz grade of 89 it had originally.
// MAGIC 
// MAGIC You can specify as many or as few of the fields in the case class as you want. Whatever
// MAGIC fields you give the names of will be changed to the value that you specify. If you leave the
// MAGIC parentheses empty, you will simply get a copy of the object you have originally.

// COMMAND ----------

ns

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Case Class Patterns
// MAGIC 
// MAGIC Another capability that comes with case classes is that you can use them in patterns.
// MAGIC This can be used as a simple way to pull values out of an instance or to select between
// MAGIC objects in a match. As an example of pulling out values, consider the following code using
// MAGIC Point3D.

// COMMAND ----------

for(Point3D(x,y,z) <- points) {
  // Do stuff with x, y, and z.
}

// COMMAND ----------

// MAGIC %md
// MAGIC This is a for loop that runs through a collection of points. Instead of calling each point
// MAGIC with a name like point, this pulls out the values in the point and gives them the names x,
// MAGIC y, and z. That can make things shorter and more clear in the body of the loop.
// MAGIC 
// MAGIC As an example of limiting what is considered, we can use another for loop that goes
// MAGIC through a course full of students.

// COMMAND ----------

for(Student(name,_,List(t1,t2,t3),_) <- courseStudents) {
  // Processing on the students with three test grades.
}

// COMMAND ----------

// MAGIC %md
// MAGIC This does something with patterns that we have not seen before, it nests them. You can nest
// MAGIC patterns in any way that you want. This is part of what makes them extremely powerful.
// MAGIC In this case, the assignment and quiz grades have been ignored and the loop is limited to
// MAGIC only considering students with exactly three test grades. Those grades are given the names
// MAGIC t1 , t2, and t3. That could also have been specified with the pattern t1::t2::t3::Nil.
// MAGIC Students who have more or fewer test grades will be skipped over by this loop.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC Mutable Classes:
// MAGIC 
// MAGIC There are some applications for which you do not
// MAGIC need the full power classes can provide, but you would like to have fields in the class that
// MAGIC are mutable. Remember that every field in a case class is like a val so the case class is
// MAGIC generally immutable. The only way the value of something in a case class can change is
// MAGIC if one of the fields references a mutable value like an Array.
// MAGIC 
// MAGIC When you want to make objects with mutable fields, you need to leave off the case
// MAGIC keyword and just define a class. To be able to use the fields, given what you currently
// MAGIC know, each one will need to be preceded by either val or var. If you put val before a field
// MAGIC its value will not be allowed to change. If you use var, the field will be allowed to change.
// MAGIC So if you have objects that truly need to be altered because they change very rapidly and modified copies are not efficient, you can make a class using var for each field that needs
// MAGIC to change.
// MAGIC 
// MAGIC Note that by leaving off the case keyword, you do more than just allow for var fields.
// MAGIC You also lose the copy method, the ability to leave off new when making a new instance
// MAGIC of the class, and the ability to do pattern matching. In general, there are many benefits to
// MAGIC immutability so you should not take this approach without good reason.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC • One way of making user-defined types is with case classes. We will use these to
// MAGIC group values together giving them useful, easy-to-read names.
// MAGIC 
// MAGIC – To create a case class follow those keywords with an argument list like that for
// MAGIC a function with names and types separated by commas. Names for types typically
// MAGIC start with a capital letter.
// MAGIC 
// MAGIC – You create an instance of a case class give the name of the type followed by
// MAGIC an argument list of the values it should store.
// MAGIC 
// MAGIC – When you want to access the members of a case class, use the dot notation we
// MAGIC have been using for other objects.
// MAGIC 
// MAGIC – The members of a case class are all vals. As a result, instances of case classes
// MAGIC tend to be immutable. The only way that will not be true is of a member is itself
// MAGIC mutable.
// MAGIC 
// MAGIC – To make new instances of case classes that are slightly different from old ones,
// MAGIC use the copy method. This method is called with named arguments for any
// MAGIC members that you want to have changed in the copy.
// MAGIC 
// MAGIC – Another useful capability of case classes is that they can be used as patterns.

// COMMAND ----------

// MAGIC %md
// MAGIC 
// MAGIC • If you need the ability to mutate values, you can make a normal class leaving off the
// MAGIC case keyword and specifying whether the member is a val or a var.
// MAGIC 
// MAGIC – Instances of these types will need to be created with new.
// MAGIC 
// MAGIC – There is no copy method automatically defined.
// MAGIC 
// MAGIC – These types will not implicitly work as patterns.

// COMMAND ----------


