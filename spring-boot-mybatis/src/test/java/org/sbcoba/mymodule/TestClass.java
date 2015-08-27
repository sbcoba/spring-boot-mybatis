package org.sbcoba.mymodule;

public class TestClass {
	public static void main(String[] args) {
		Test test = Test.newInstance();
		Test test1 = Test.newInstance();
		Test test2 = Test.newInstance();
		System.out.println(test1 == test2);
		System.out.println(test.cal(10, 20));
	}
	static class Test {
		private static Test test;
		static synchronized Test newInstance() {
			if (test == null) {
				test = new Test();
			}
			return test;
		}
		private Test() {}
		int cal(int a, int b) {
			return a + b;
		}
	}
}
