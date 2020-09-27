package hu.xannosz.microtools.dimensions;

import hu.xannosz.microtools.function.TriConsumer;
import hu.xannosz.microtools.function.TriFunction;
import hu.xannosz.microtools.pack.Douplet;

import java.util.*;
import java.util.function.BiFunction;

public class CoordinateSystem2D<X, Y, T> {
    private final Map<Douplet<X, Y>, T> system = new HashMap<>();

    public T get(X x, Y y) {
        return system.get(new Douplet<>(x, y));
    }

    public void put(X x, Y y, T item) {
        system.put(new Douplet<>(x, y), item);
    }

    public void remove(X x, Y y) {
        system.remove(new Douplet<>(x, y));
    }

    public int size() {
        return system.size();
    }

    public boolean isEmpty() {
        return system.isEmpty();
    }

    public boolean containsKey(X x, Y y) {
        return system.containsKey(new Douplet<>(x, y));
    }

    public boolean containsValue(T item) {
        return system.containsValue(item);
    }

    public void putAll(CoordinateSystem2D<? extends X, ? extends Y, ? extends T> c) {
        for (Map.Entry<? extends Douplet<? extends X, ? extends Y>, ? extends T> entry : c.entrySet()) {
            put(entry.getKey().getFirst(), entry.getKey().getSecond(), entry.getValue());
        }
    }

    public void clear() {
        system.clear();
    }

    public Set<X> getXSet() {
        Set<X> x = new HashSet<>();
        for (Douplet<X, Y> d : system.keySet()) {
            x.add(d.getFirst());
        }
        return x;
    }

    public Set<Y> getYSet() {
        Set<Y> y = new HashSet<>();
        for (Douplet<X, Y> d : system.keySet()) {
            y.add(d.getSecond());
        }
        return y;
    }

    public Collection<T> values() {
        return system.values();
    }

    public Set<Map.Entry<Douplet<X, Y>, T>> entrySet() {
        return system.entrySet();
    }

    @SuppressWarnings({"RawUseOfParameterizedType", "rawtypes"})
    public boolean equals(Object o) {
        if (o instanceof CoordinateSystem2D) {
            return system.equals(((CoordinateSystem2D) o).system);
        }
        return false;
    }

    public int hashCode() {
        return system.hashCode();
    }

    public T getOrDefault(X x, Y y, T defaultValue) {
        return system.getOrDefault(new Douplet<>(x, y), defaultValue);
    }

    public void forEach(TriConsumer<? super X, ? super Y, ? super T> action) {
        system.forEach((d, t) -> action.accept(d.getFirst(), d.getSecond(), t));
    }

    public void replaceAll(TriFunction<? super X, ? super Y, ? super T, ? extends T> function) {
        system.replaceAll((d, t) -> function.apply(d.getFirst(), d.getSecond(), t));
    }

    public T putIfAbsent(X x, Y y, T value) {
        return system.putIfAbsent(new Douplet<>(x, y), value);
    }

    public T replace(X x, Y y, T value) {
        return system.replace(new Douplet<>(x, y), value);
    }

    public T computeIfAbsent(X x, Y y,
                             BiFunction<? super X, ? super Y, ? extends T> mappingFunction) {
        return system.computeIfAbsent(new Douplet<>(x, y),
                (d) -> mappingFunction.apply(d.getFirst(), d.getSecond()));
    }

    public T computeIfPresent(X x, Y y,
                              TriFunction<? super X, ? super Y, ? super T, ? extends T> remappingFunction) {
        return system.computeIfPresent(new Douplet<>(x, y),
                (d, t) -> remappingFunction.apply(d.getFirst(), d.getSecond(), t));
    }

    public T compute(X x, Y y,
                     TriFunction<? super X, ? super Y, ? super T, ? extends T> remappingFunction) {
        return system.compute(new Douplet<>(x, y),
                (d, t) -> remappingFunction.apply(d.getFirst(), d.getSecond(), t));
    }

    public T merge(X x, Y y, T value,
                   BiFunction<? super T, ? super T, ? extends T> remappingFunction) {
        return system.merge(new Douplet<>(x, y), value, remappingFunction);
    }
}
