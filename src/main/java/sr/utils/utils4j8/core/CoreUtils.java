package sr.utils.utils4j8.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public class CoreUtils {

	public static <E> Stream<E> safeStream(final Collection<E> collection) {
		return Optional.ofNullable(collection)//
				.map(Collection::stream)//
				.orElseGet(Stream::empty);
	}

	public static <E> Stream<E> safeStream(final E[] array) {
		return Optional.ofNullable(array)//
				.map(Arrays::stream)//
				.orElseGet(Stream::empty);
	}
}
