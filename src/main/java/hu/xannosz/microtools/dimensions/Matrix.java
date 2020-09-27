package hu.xannosz.microtools.dimensions;

import java.util.ArrayList;
import java.util.List;

import hu.xannosz.microtools.pack.Douplet;

public class Matrix<T> {
	private List<List<T>> system = new ArrayList<>();
	private Douplet<Integer, Integer> size;

	public Matrix(int x, int y) {
		size = new Douplet<Integer, Integer>(x, y);
		for (int i = 0; i < x; i++) {
			system.add(new ArrayList<>());
			for (int e = 0; e < y; e++) {
				system.get(i).add(null);
			}
		}
	}

	public T getItem(int x, int y) {
		if (x + 1 > size.getFirst() || y + 1 > size.getSecond()) {
			return null;
		}
		return system.get(x).get(y);
	}

	public void addItem(int x, int y, T item) {
		if (x + 1 > size.getFirst() || y + 1 > size.getSecond()) {
			return;
		}
		system.get(x).set(y, item);
	}

	public void deleteItem(int x, int y) {
		addItem(x, y, null);
	}

	public Douplet<Integer, Integer> getSize() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < size.getFirst(); i++) {
			for (int e = 0; e <size.getSecond(); e++) {
				result.append("|");
				result.append(getItem(i, e));
			}
			result.append("|\n");
		}
		return result.toString();
	}
}
