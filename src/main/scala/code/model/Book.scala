package code.model

import net.liftweb.mapper._

class Book extends LongKeyedMapper[Book] with IdPK {
  def getSingleton = Book

  object title extends MappedString(this, 255)
  object blurb extends MappedText(this)
  object publishedOn extends MappedDateTime(this)

  object publisher extends MappedLongForeignKey(this, Publisher)
}

object Book extends Book with LongKeyedMetaMapper[Book]