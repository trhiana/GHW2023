# @author trhiana
# Program to sort a list of integers

def sortList(arr):
    for i in range(len(arr)):
        for j in range(i + 1, len(arr)):
            if arr[i] > arr[j]:
                arr[i], arr[j] = arr[j], arr[i]

    return arr


print(sortList([50, 31, 21, 28, 72]))
print(sortList([41, 73, 93, 68, 43]))
print(sortList([45, 78, 5, 17, 97]))
print(sortList([71, 69, 61, 88, 75]))
print(sortList([99, 44, 55, 9, 10]))
