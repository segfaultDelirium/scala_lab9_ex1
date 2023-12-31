import scala.math

def identity(x: Int) = x

def distanceSum(
    zipped: Iterable[(Int, Int)],
    distanceFunction: (Int, Int) => Double
): Double = {
  zipped
    .map((a, b) => distanceFunction(a, b))
    .reduce((acc, x) => acc + x)
}

def distFirstPart(
    collection1: Iterable[Int],
    collection2: Iterable[Int],
    f: Int => Int = identity
): Iterable[(Int, Int)] = {
  collection1
    .zip(collection2)
    .map((a, b) => (f(a), f(b)))
}

def dist(
    collection1: Iterable[Int],
    collection2: Iterable[Int],
    functionToApplyToEach: Int => Int = identity
): Double = {

  distanceSum(
    distFirstPart(collection1, collection2, functionToApplyToEach),
    (a: Int, b: Int) => math.pow((a - b).toDouble, 2.0)
  )
}

def dist2(
    collection1: Iterable[Int],
    collection2: Iterable[Int],
    distanceFunction: (Int, Int) => Double
): Double = {
  distanceSum(distFirstPart(collection1, collection2), distanceFunction)
}

@main def hello: Unit = {
  println("Hello world!")
  println(dist(List(1, 3), List(1, 3)))
  println(dist(List(1, 1), List(1, 3)))
  println(dist(List(1, 4), List(1, -4), math.abs))
  println(
    dist2(List(1, 4), List(1, -4), (x: Int, y: Int) => { math.abs(x - y) })
  )

}
