package sr.utils.utils4j8.containers;

import static sr.utils.utils4j8.core.CoreUtils.safeStream;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectionContainer<E> {

		private Collection<E> collection;

		public CollectionContainer(Collection<E> collection) {
			this.collection = collection;
		}

		public <P> Map<P, E> mapBy(//
				final Function<E, P> mapper//
		) {
			return mapBy(mapper, p -> p);
		}

		public <K, V> Map<K, V> mapBy(//
				final Function<E, K> keyMapper, //
				final Function<E, V> valueMapper//
		) {
			return safeStream(collection)//
					.collect(//
							Collectors.toMap(//
									keyMapper, //
									valueMapper, //
									(first, second) -> second//
							)//
					);
		}

		public <S> HashSet<S> getHashSet(//
				final Function<E, S> mapper//
		) {
			return safeStream(collection)//
					.map(mapper)//
					.collect(//
							Collectors.toCollection(HashSet::new)//
					);
		}

		public <S> HashSet<S> getFlatSet(//
				final Function<E, Collection<S>> flatMapper//
		) {
			return safeStream(collection)//
					.map(flatMapper)//
					.filter(Objects::nonNull)//
					.flatMap(Collection::stream)//
					.collect(//
							Collectors.toCollection(HashSet::new)//
					);
		}
	}