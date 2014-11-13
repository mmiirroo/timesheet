class MyFactorial {
	public:
	typedef CallBack<void,int> MyCallBack_t;
	
	private:
	MyCallBack_t& cbDeletage;
	
	public:
		explicit MyFactorial(MyCallBack_t& cb): cbDeletage(cb){}
		int factorial(const int number){
			const int result = (number>=1)?1:number*factorial(number-1);
			cbDeletage(result);
			return result;
		}
}