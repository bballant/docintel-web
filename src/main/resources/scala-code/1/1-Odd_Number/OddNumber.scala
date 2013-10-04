object OddNumber {
  def main(args: Array[String]) {
    val intArray = Array(1, 1, 2, 3, 2, 3, 4, 5, 5, 4, 4)
    val result = intArray.reduce{ (a, b) => a ^ b }
    println("input is %s".format(intArray.mkString(", ")))
    println("only %d appears an odd number of times".format(result))
  }
}
