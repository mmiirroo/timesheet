package org.afonseca;

public class CallBackExample {
	public native void getFactorial(final int input);
	
	// this method will be call from c++
	public void callback(final int result){
		System.out.println("Result:"+result);
	}
	
	public static void main(String[] args){
		System.loadLibrary("CallBackCPP");
		CallBackExample test = new CallBackExample();
		test.getFactorial(5);
	}
}