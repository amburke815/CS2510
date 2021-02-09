import java.awt.Color;

import tester.Tester;

// Represents an Alexander Calder-inspired mobile art piece
interface IMobile {
  // _IMobile_ INTERFACE METHODS
  int totalWeight();
}

/////////////////////////////////////////////////////////////////////////
// represents a simple mobile with 1 'fork'
// this type of mobile can hang at the end of a larger mobile
class Simple implements IMobile {
  
  int length; // the vertical length of the attached string
  int weight; // in kg, pounds are stupid 
  Color color;
  
  // the constructor
  Simple(int length, int weight, Color color) {
    this.length = length;
    this.weight = weight;
    this.color = color;
  }
  
  /* TEMPLATE:
   * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   * FIELDS:
   * this.length ... int
   * this.weight ... int
   * this.color ... Color (from java.awt.*) 
   * 
   * METHODS:
   * this.totalWeight() ... int
   * 
   * METHODS OF FIELDS:
   * 
   * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   */
  
  // computes the total weight of this Mobile
  public int totalWeight() {
    return this.weight;
  }
  
  
}

/////////////////////////////////////////////////////////////////////////
// represents a more complex mobile with 2 'forks'
class Complex {
  
  int length; // the vertical length of the attached string
  int leftside; // the length of this mobile's left strut
  int rightside; // the length of this string's right strut
  IMobile left;
  IMobile right;
  
  // the constructor
  Complex(int length, int leftside, int rightside, IMobile left, IMobile right) {
    this.length = length;
    this.leftside = leftside;
    this.rightside = rightside;
    this.left = left;
    this.right = right;
  }
  
  /* TEMPLATE
   * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   * FIELDS:
   * this.length ... int
   * this.leftside ... int
   * this.rightside ... int
   * this.left ... IMobile
   * this.right ... IMobile
   * 
   * METHODS:
   * this.totalWeight() ... int
   * 
   * METHODS OF FIELDS:
   * 
   * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   */
  
  // computes the total weight of this Mobile
  public int totalWeight() {
    // sum the total weight of this Mobile's left and right 'sub-Mobiles'
    return this.left.totalWeight() + this.right.totalWeight();
  }
}


/////////////////////////////////////////////////////////////////////////
// examples of Mobiles
class ExamplesMobiles {
  
  IMobile exampleSimple = new Simple(2, 36, Color.BLUE); // a simple mobile
  
  // the instance simpleij represents the Simple Mobile at the 
  // i Simple Mobiles down from the northwest Simple Mobile and
  // j Simple Mobiles right of the northwest Simple Mobile
  IMobile simple23 = new Simple(1, 60, Color.GREEN); 
  IMobile simple22 = new Simple(2, 36, Color.RED);
  IMobile simple11 = new Simple(1, 12, Color.RED);
  IMobile simple00 = new Simple(1, 36, Color.BLUE);
  
  // combining them...
  // the instance complexa represents the Complex Mobile a Complex Mobiles
  // down from the uppermost Complex Mobile
  IMobile complex2 = new Complex(2, 5, 3, this.simple22, this.simple23);
  IMobile complex1 = new Complex(2, 8, 8, this.simple11, this.complex2);
  IMobile complex0 = new Complex(1,9, 3, this.simple00, this.complex1);
  
  // So, finally:
  IMobile exampleComplex = complex0;
  
  // an even more complex example:
  IMobile example3 = new Complex(8, 2, 13, this.complex1, this.complex0);
  
  // TEST:
  //// METHOD: totalWeight()
  //// INTERFACE: IMobile
  boolean testTotalWeight(Tester t) {
    return t.checkExpect(this.exampleComplex.totalWeight(), 144)
        && t.checkExpect(this.exampleSimple.totalWeight(), 20);
  }
}
