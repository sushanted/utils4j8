package sr.utils.utils4j8.containers;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FunctionContainer<T, R> implements Function<T, R> {

	private Function<T, R> function;

	public FunctionContainer(Function<T, R> function) {
		this.function = function;
	}

	public FunctionContainer<T, R> onNull(Consumer<T> consumer) {

		// TODO : can be moved to a method
		return new FunctionContainer<>(t -> {
			R r = function.apply(t);
			if (r == null) {
				consumer.accept(t);
			}
			return r;
		});

		// return new FunctionContainer<>(t -> consumeAndReturn(consumer, t,
		// function.apply(t)));
	}

	public FunctionContainer<T, R> onNullSwitchTo(R another) {

		return onNullSwitchTo(() -> another);

	}

	public FunctionContainer<T, R> onNullSwitchTo(Supplier<R> anotherSupplier) {

		return new FunctionContainer<>(//
				t -> Optional.ofNullable(function.apply(t))//
						.orElseGet(anotherSupplier)//
		);

	}

	@Override
	public R apply(T t) {
		return function.apply(t);
	}

}