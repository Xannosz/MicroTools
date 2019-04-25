package hu.xannosz.microtools.pack;

public class Quintlet<T1, T2, T3, T4, T5> {

	private T1 first;
	private T2 second;
	private T3 third;
	private T4 fourth;
	private T5 fifth;

	public Quintlet(T1 first, T2 second, T3 third, T4 fourth, T5 fifth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
	}

	public T1 getFirst() {
		return first;
	}

	public void setFirst(T1 first) {
		this.first = first;
	}

	public T2 getSecond() {
		return second;
	}

	public void setSecond(T2 second) {
		this.second = second;
	}

	public T3 getThird() {
		return third;
	}

	public void setThird(T3 third) {
		this.third = third;
	}

	public T4 getFourth() {
		return fourth;
	}

	public void setFourth(T4 fourth) {
		this.fourth = fourth;
	}

	public T5 getFifth() {
		return fifth;
	}

	public void setFifth(T5 fifth) {
		this.fifth = fifth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fifth == null) ? 0 : fifth.hashCode());
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((fourth == null) ? 0 : fourth.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		result = prime * result + ((third == null) ? 0 : third.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quintlet other = (Quintlet) obj;
		if (fifth == null) {
			if (other.fifth != null)
				return false;
		} else if (!fifth.equals(other.fifth))
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (fourth == null) {
			if (other.fourth != null)
				return false;
		} else if (!fourth.equals(other.fourth))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		if (third == null) {
			if (other.third != null)
				return false;
		} else if (!third.equals(other.third))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[first=" + first + ", second=" + second + ", third=" + third + ", fourth=" + fourth
				+ ", fifth=" + fifth + "]";
	}
}
