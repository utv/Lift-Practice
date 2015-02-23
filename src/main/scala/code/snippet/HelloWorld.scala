package code 
package snippet

import net.liftweb.mapper.By

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._
import code.model._

class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date
  val orielly = ("Orielly", "Not bad one")

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> date.map(_.toString)

  def savePublisher(pName: String, pDescription: String): Publisher =
    Publisher.create.name(pName).description(pDescription).saveMe()
  
  def initDb = {
    Publisher.findAll().map(_.delete_!)
    Book.findAll().map(_.delete_!)

    val myPublisher: Publisher = savePublisher(orielly._1, orielly._2)
    Book.create.publisher(myPublisher).title("Lift Beginner").blurb("Lift rocks!").save()
    Book.create.publisher(myPublisher).title("X men").blurb("Regret until you die").save()
    Book.create.publisher(myPublisher).title("Her").blurb("Too much AI").save()
  }

  def renderPublishers = {
    initDb
    val aPublisher = Publisher.find { By(Publisher.name, orielly._1) }
    val aBook = Book.findAll { By(Book.publisher, aPublisher) }

    "tbody tr *" #>
      aBook.map {
        book => ".title *" #> book.title & ".blurb *" #> book.blurb
      }
  }
  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}

