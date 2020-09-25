package sr.utils.utils4j8;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import sr.utils.utils4j8.containers.CollectionContainer;
import sr.utils.utils4j8.containers.FunctionContainer;
import sr.utils.utils4j8.containers.PredicateContainer;
import sr.utils.utils4j8.core.CoreUtils;

// TODO : verbose documentation of each method
public final class Utils4j8 {

	/**
	 * Get a function which cast an object into another type, with a prior
	 * isInstance check
	 * 
	 * 
	 * @param <F>   type of the object input to the function
	 * @param <T>   target class type
	 * @param clazz
	 * @return casting function which returns <br>
	 *         casted object if it is an instance of provided clazz <br>
	 *         null if object is not instance of provided clazz
	 * 
	 * 
	 */
	public static <F, T> Function<F, T> safeCast(Class<T> clazz) {
		return value -> Optional.ofNullable(value)//
				.filter(clazz::isInstance)//
				.map(clazz::cast)//
				.orElse(null);
	}

	// map/filter utilities

	/**
	 * 
	 * Get a function which retrieves an element in a list at provided index value
	 * 
	 * @param <E>   Type of the list input to the function
	 * @param index
	 * @return a function which takes input as list and returns <br>
	 *         element at provided 'index', <br>
	 *         null if the 'index' >= list.size
	 */
	public static <E> Function<? super List<E>, ? extends E> valueAt(final int index) {
		return list -> index < list.size() ? list.get(index) : null;
	}


	/**
	 * 
	 * Get a predicate which tests if a provided key is present in the input map
	 * 
	 * @param <K> Type of map key
	 * @param <V> Type of map value
	 * @param key
	 * @return a predicate which takes input as map and returns if the provided key
	 *         is present in the map
	 * 
	 */
	public static <K, V> Predicate<? super Map<K, V>> contains(final K key) {
		return map -> map.containsKey(key);
	}

	public static <K, V> Function<? super Map<K, V>, ? extends V> valueOf(final K key) {
		return map -> map.get(key);
	}

	// Predicates utilities

	public static <T> Predicate<T> not(final Predicate<T> predicate) {
		return predicate.negate();
	}

	public static <P> PredicateContainer<P> predicate(Predicate<P> predicate) {
		return new PredicateContainer<P>(predicate);
	}

	public static <P> PredicateContainer<P> onPass(//
			final Predicate<P> predicate, //
			final Runnable runnable//
	) {
		return predicate(predicate)//
				.onPass(runnable);
	}

	public static <P> PredicateContainer<P> onFail(//
			final Predicate<P> predicate, //
			final Runnable runnable//
	) {
		return predicate(predicate)//
				.onFail(runnable);
	}

	// Function utilities

	public static <T, R> FunctionContainer<T, R> function(Function<T, R> function) {
		return new FunctionContainer<T, R>(function);
	}

	public static <T, R> FunctionContainer<T, R> onNull(//
			final Function<T, R> function, //
			final Consumer<T> onNullConsumer//
	) {
		return new FunctionContainer<T, R>(function)//
				.onNull(onNullConsumer);
	}

	public static <T, R> FunctionContainer<T, R> onNullSwitchTo(//
			final Function<T, R> function, //
			final R another//
	) {
		return new FunctionContainer<T, R>(function)//
				.onNullSwitchTo(another);
	}

	public static <T, R> FunctionContainer<T, R> onNullSwitchTo(//
			final Function<T, R> function, //
			final Supplier<R> anotherSupplier//
	) {
		return new FunctionContainer<T, R>(function)//
				.onNullSwitchTo(anotherSupplier);
	}

	// Collection utilities

	public static <E> CollectionContainer<E> collection(Collection<E> collection) {
		return new CollectionContainer<>(collection);
	}

	public static <E> Stream<E> safeStream(final Collection<E> collection) {
		return CoreUtils.safeStream(collection);
	}

	public static <E> Stream<E> safeStream(final E[] array) {
		return CoreUtils.safeStream(array);
	}

	public static <P, E> Map<P, E> mapBy(//
			final Collection<E> collection, //
			final Function<E, P> mapper//
	) {
		return collection(collection)//
				.mapBy(mapper);
	}

	public static <E, S> HashSet<S> getHashSet(//
			final Collection<E> collection, //
			final Function<E, S> mapper//
	) {
		return collection(collection)//
				.getHashSet(mapper);
	}

	public static <E, S> HashSet<S> getFlatSet(//
			final Collection<E> collection, //
			final Function<E, Collection<S>> flatMapper//
	) {
		return collection(collection)//
				.getFlatSet(flatMapper);
	}

	// TODO : iterating over map to get different structures
	// TODO : asserter
	// TODO : distinct by field
	// TODO : tuples

}
