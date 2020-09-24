package sr.utils.utils4j8.containers;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateContainer<T> implements Predicate<T> {

	private final Predicate<T> predicate;

	public PredicateContainer(Predicate<T> predicate) {
		this.predicate = predicate;
	}

	public PredicateContainer<T> negate() {
		return new PredicateContainer<>(predicate.negate());
	}

	public PredicateContainer<T> onPass(Runnable runnable) {
		return onPass(any -> runnable.run());
	}

	public PredicateContainer<T> onFail(Runnable runnable) {
		return onFail(any -> runnable.run());
	}

	public PredicateContainer<T> onPass(Consumer<T> consumer) {
		return new PredicateContainer<>(//
				predicate.and(t -> consumeAndReturn(consumer, t, true))//
		);
	}

	public PredicateContainer<T> onFail(Consumer<T> consumer) {
		return new PredicateContainer<>(//
				predicate.or(t -> consumeAndReturn(consumer, t, false))//
		);
	}

	@Override
	public boolean test(T t) {
		return predicate.test(t);
	}
	
	private static <T, R> R consumeAndReturn(//
			final Consumer<T> consumer, //
			final T t, //
			final R returnValue//
	) {
		consumer.accept(t);
		return returnValue;
	}

}