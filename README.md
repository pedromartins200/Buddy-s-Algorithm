# Buddy-s-Algorithm
An implementation of the buddy algorithm in Java

View [Buddy memory allocation](https://en.wikipedia.org/wiki/Buddy_memory_allocation).

# Example Usage

**Output**

The output represents the memory organization, each block being represented in a line where the first element indicates the block utilisation and the second element the block dimension. The block utilisation is indicated by an identifier or by 'f' if the block is free.

**Input**

The input is a sequence of ines where the first line contains an integer that defines the dimension of the memory block, and the following lines indicate the requests to the memory management system.
A reservation request starts with an identifier and an integer with the required dimension. A release request begins with an identifier followed by 0 (zero)


**Worked example**

Suppose input is:
64
a1 5

So we need to find the best suitable spot to put (a1 5)

1- Divide 64 into 32 32

32
32

32 is not the best spot, divide 32 into 16 16

16
16
32

16 is still not a good spot. Divide into 8 8

8
8
16
32

Now 8 is the best spot for (a1 5) since, the next division would yield 4, and 4 < 5. So we get
a1 8
f 8
f 16
f 32


Try this input on the program


