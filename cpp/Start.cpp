#include <bits/stdc++.h>
using namespace std;
// 快读
inline int read()
{
	int x = 0, neg = 1;
	char op = getchar();
	while (!isdigit(op))
	{
		if (op == '-')
			neg = -1;
		op = getchar();
	}
	while (isdigit(op))
	{
		x = (x << 1) + (x << 3) + op - '0';
		op = getchar();
	}
	return neg * x;
}
// 快写
inline void print(int x)
{
	if (x < 0)
	{
		putchar('-');
		x = -x;
	}
	if (x >= 10)
		print(x / 10);
	putchar(x % 10 + '0');
}
// 主函数
int main()
{
	// int a = read();
	// print(a);
	int a = read(), b = read(), c = read(), d = read();

	return 0;
}