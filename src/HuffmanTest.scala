import Huffman.huffman
import org.junit.Assert.assertEquals
import org.junit.Test

/**
  * Created by isazoli on 3/8/2016.
  */
class HuffmanTest {
  @Test
  def buildTreeTest(): Unit = {
    val x: List[(String, Int)] = List(
      "M" -> 1,
      "I" -> 5,
      "S" -> 4,
      "P" -> 2,
      "R" -> 2,
      "V" -> 1,
      "E" -> 1,
      " " -> 1
    )

    //val s: List[Node] = Huffman.buildTree(x.map(Leaf.tupled).sortBy(_.counter))
    //println(s)
    //val e = Huffman.encode(s.head, "")
    println(Huffman.huffman(x))

    println(
      Huffman.huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
    )
  }

  @Test
  def testHappyPath(): Unit = {
    val result = huffman(List(("a", 45), ("b", 13), ("c", 12), ("d", 16), ("e", 9), ("f", 5)))
    assertEquals(
      List(("a", "0"), ("b", "101"), ("c", "100"), ("d", "111"), ("e", "1101"), ("f", "1100")),
      result
    )
  }

  @Test
  def testSameCounts(): Unit = {
    val result = huffman(List(("a", 1), ("b", 1), ("c", 1)))
    assertEquals(
      List(("a", "10"), ("b", "11"), ("c", "0")),
      result
    )
  }

  @Test
  def testIncreasingCounts(): Unit = {
    val result = huffman(List(("a", 1), ("b", 2), ("c", 3), ("d", 4), ("e", 5), ("f", 6)))
    assertEquals(
      List(("a", "1110"), ("b", "1111"), ("c", "110"), ("d", "00"), ("e", "01"), ("f", "10")),
      result
    )
  }
}

