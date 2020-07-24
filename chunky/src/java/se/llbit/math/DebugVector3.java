package se.llbit.math;

import se.llbit.json.JsonObject;

public class DebugVector3 extends Vector3 {

  /**
   * Creates a new vector (0, 0, 0).
   */
  public DebugVector3() {
    super(1, 0, 0);
  }

  /**
   * Creates a new vector (i, j, k).
   */
  public DebugVector3(double i, double j, double k) {
    super(i, j, k);
    assertValid();
  }

  /**
   * Create a new vector equal to the given vector.
   */
  public DebugVector3(Vector3 o) {
    super(o);
    assertValid();
  }

  @Override
  public void set(Vector3 o) {
    super.set(o);
  }

  @Override
  public void set(double d, double e, double f) {
    super.set(d, e, f);
    assertValid();
  }

  @Override
  public void sub(Vector3 a, Vector3 b) {
    super.sub(a, b);
    assertValid();
  }

  @Override
  public void cross(Vector3 a, Vector3 b) {
    super.cross(a, b);
    assertValid();
  }

  @Override
  public void scaleAdd(double s, Vector3 d, Vector3 o) {
    super.scaleAdd(s, d, o);
    assertValid();
  }

  @Override
  public void scaleAdd(double s, Vector3 d) {
    super.scaleAdd(s, d);
    assertValid();
  }

  @Override
  public void scale(double s) {
    super.scale(s);
    assertValid();
  }

  @Override
  public void add(Vector3 a, Vector3 b) {
    super.add(a, b);
    assertValid();
  }

  public void add(Vector3 a, Vector3 b, Vector3 c) {
    super.add(a, b);
    super.add(c);
    assertValid();
  }

  @Override
  public void add(Vector3 a) {
    super.add(a);
    assertValid();
  }

  @Override
  public void add(Vector3i a) {
    super.add(a);
    assertValid();
  }

  @Override
  public void add(double a, double b, double c) {
    super.add(a, b, c);
    assertValid();
  }

  @Override
  public void sub(Vector3 a) {
    super.sub(a);
    assertValid();
  }

  @Override
  public void sub(double a, double b, double c) {
    super.sub(a, b, c);
    assertValid();
  }

  @Override
  public void sub(Vector3i a) {
    super.sub(a);
    assertValid();
  }

  @Override
  public void set(Vector3i a) {
    super.set(a);
    assertValid();
  }

  @Override
  public void fromJson(JsonObject object) {
    super.fromJson(object);
    assertValid();
  }

  protected void assertValid() {
    if (Double.isNaN(x)) {
      throw new IllegalStateException("x is NaN");
    }
    if (Double.isNaN(y)) {
      throw new IllegalStateException("y is NaN");
    }
    if (Double.isNaN(z)) {
      throw new IllegalStateException("z is NaN");
    }
  }

  public static class NonZero extends DebugVector3 {

    /**
     * Creates a new vector (0, 0, 0).
     */
    public NonZero() {
      super(1, 0, 0);
    }

    /**
     * Creates a new vector (i, j, k).
     */
    public NonZero(double i, double j, double k) {
      super(i, j, k);
    }

    /**
     * Create a new vector equal to the given vector.
     */
    public NonZero(Vector3 o) {
      super(o);
    }

    @Override
    protected void assertValid() {
      super.assertValid();
      if (lengthSquared() < Ray.EPSILON) {
        throw new IllegalStateException("Vector length is 0");
      }
    }
  }
}
