package frc.robot.standard;

import edu.wpi.first.math.Nat;
import frc.robot.standard.N823;
import frc.robot.standard.N1646;


/**
 * A natural number expressed as a java class.
 * The counterpart to {@link Num} that should be used as a concrete value.
 *
 * @param <T> The {@link Num} this represents.
 */
@SuppressWarnings({"MethodName", "unused"})
public interface MyNat<T extends Num> {
  /**
   * The number this interface represents.
   *
   * @return The number backing this value.
   */
  int getNum();

  static Nat<N823> N823() {
    return N823.instance;
  }

  static Nat<N1646> N1646() {
    return N1646.instance;
  }

}