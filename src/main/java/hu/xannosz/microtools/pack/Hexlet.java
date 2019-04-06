package hu.xannosz.microtools.pack;

public class Hexlet<T1, T2, T3, T4, T5, T6> {

	private T1 first;
	private T2 second;
	private T3 third;
	private T4 fourth;
	private T5 fifth;
	private T6 sixth;

	public Hexlet(T1 first, T2 second, T3 third, T4 fourth, T5 fifth, T6 sixth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
		this.sixth = sixth;
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

	public T6 getSixth() {
		return sixth;
	}

	public void setSixth(T6 sixth) {
		this.sixth = sixth;
	}
}
