# 대표값 2

number_array = []

for _ in range(5):
    number_array.append(int(input()))

print(sum(number_array)//5)
print(sorted(number_array)[2])
