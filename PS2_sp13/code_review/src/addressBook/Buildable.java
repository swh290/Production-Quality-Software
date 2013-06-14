package addressBook;

/**
 * Buildable.java
 * 
 * Signature for the implementation of the builder pattern.
 * {@code build} should return an instance of T that corresponds to
 * the state of the builder.  A builder may be the only one to get
 * an instance of the class being built.
 * 
 * @author Ashwath
 *
 * @param <T>
 */
public interface Buildable<T> 
{
	public T build();
}

