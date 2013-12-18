
import scala.collection.mutable._

/**
 * Created with IntelliJ IDEA.
 * User: tomaszlelek
 * Date: 11/6/13
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
object MultimapTest {
  val m_ids = new HashMap[String, Set[String]] with MultiMap[String, String]

  def addProperty( id:String, property:String) {
    m_ids.getOrElseUpdate(id, new HashSet[String]())+=property
  }

  def hasProperty( id:String, property:String) = m_ids(id).contains(property)

  def main( args:Array[String])
  {
    addProperty("id1", "prop1")
    addProperty("id1", "prop2")
    addProperty("id1", "prop3")
    addProperty("id2", "prop1")

    System.out.println (hasProperty("id1", "prop2"))
    System.out.println (hasProperty("id1", "prop4"))
    System.out.println (hasProperty("id2", "prop2"))
    System.out.println (hasProperty("id2", "prop4"))
  }
}
