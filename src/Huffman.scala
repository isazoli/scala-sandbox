/**
  * Created by isazoli on 3/8/2016.
  */
object Huffman {

  def huffman: List[(String, Int)] => List[(String, String)] = symbolsWithOccurrences =>
    buildTree(symbolsWithOccurrences.map(Leaf.tupled).sortBy(_.occurrences))
      .headOption
      .map(
        encode(_, "")
          .map { case (Leaf(label, _), code) => label -> code }
          .sortBy(_._1)
      ).getOrElse(List.empty)

  def buildTree: List[Node] => List[Node] = {
    case emptyList if emptyList.isEmpty => emptyList
    case head :: Nil => List(head)
    case head :: next :: tail => buildTree(BinaryNode(head, next) :: tail sortBy (_.occurrences))
  }

  def encode: (Node, String) => List[(Node, String)] = {
    case (BinaryNode(left, right), prefix) => encode(left, prefix + "0") ++ encode(right, prefix + "1")
    case (leaf@Leaf(_, _), code) => List(leaf -> code)
  }

  trait Node {
    def label: String
    def occurrences: Int
  }

  case class Leaf(label: String, occurrences: Int) extends Node

  case class BinaryNode(left: Node, right: Node) extends Node {
    val label = left.label + right.label
    val occurrences = left.occurrences + right.occurrences
    override def toString = s"BinaryNode=$label -> $occurrences"
  }
}

