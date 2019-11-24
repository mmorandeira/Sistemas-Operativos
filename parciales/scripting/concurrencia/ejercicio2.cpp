#include <iostream>
#include <thread>
#include <string>
#include <sstream>

using namespace std;
thread_local stringstream msg;
//static thread_local int y=3;
thread_local int y=3;

void XX(string threadid){
	int z;
	if (y<1) {
		return;
	}
	else
	{
		this_thread::sleep_for(std::chrono::milliseconds(10));
		msg <<"Thread "<<threadid<<" Valor de y : "<<y<<"\n"
		<<"Thread "<<threadid<<" Distancia de y a z : "<<&y-&z<<"\n"
		<<"Thread "<<threadid<<" Direccion de y : "<<&y<<"\n";
		cout << msg.str();
		y--;
		XX(threadid);
	}
}
int main (){
	thread th0(XX,"0");
	thread th1(XX,"1");
	th0.join();
	th1.join();
	return 0;
}
