// 요세푸스 문제
// queue in Baekjoon

#include <stdio.h>
#include <queue>

using namespace std;

int main(void)
{
    int n, k;
    queue<int> integer_queue;

    scanf("%d %d", &n, &k);
    for (int i = 1; i <= n; i++)
    {
        integer_queue.push(i);
    }

    printf("<");
    while (integer_queue.size() > 1)
    {
        for (int i = 0; i < k - 1; i++)
        {
            integer_queue.push(integer_queue.front());
            integer_queue.pop();
        }

        printf("%d, ", integer_queue.front());
        integer_queue.pop();
    }
    printf("%d>", integer_queue.front());

    return 0;
}
