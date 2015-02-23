package code.model

import net.liftweb.mapper._


class Publisher extends LongKeyedMapper[Publisher] with IdPK {

  def getSingleton = Publisher

  object name extends MappedString(this, 255)
  object description extends MappedText(this)
}

object Publisher extends Publisher with LongKeyedMetaMapper[Publisher]

