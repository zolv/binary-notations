# Binary notations in different bases

This is an implementation of algorithm to search/check numbers which follows sequence mentioned in one of [Numberphile's video](https://www.youtube.com/watch?v=LNS1fabDkeA) titled *"Why 82,000 is an extraordinary number"*.

The sequence is also mentioned on [The On-Line Encyclopedia of Integer Sequences (OEIS)](http://oeis.org) as sequence [A258107](https://oeis.org/A258107)

## Description of searching algorithm

One way to search for next integer in mentioned sequencei to check all integers one by one.
If we want to find next number which matches condition we start searching in base-5. If we want to find next integer in sequence, we start searching in base-6.

As we can already see, checking all numbers is useless. Implemented algorithm speeds up this process, let's see why:

Let's analyse mentioned element in the sequence `82000(10)` step by step:

Value:
```
 base-10: 2 no
  base-5: 2 no
  base-4: 2 no
  base-3: 2 no
```
It does not have binary representation in base-5 = `2(5)`. It means we can skip checking some next numbers `3(5)` and `4(5)`. We know exactly which number is next candidate which has a binary representation in base-5, it's `10(5) = 5(10)`.

So we start again with next value 5(10):
Value:
```
 base-10: 5
  base-5: 10
  base-4: 11
  base-3: 12
```
We see that in base-5 and base-4 `5(10)` has binary representation, which is ok.
But in base-3 representation is `12(3)` which does not fit.
Again, we know exactly what is next candidate to check - it's `100(3) = 9(10)`. It means we can skip checking `20(3)`, `21(3)` and `22(3)`.

So we start again with next value `9(10)`:

Value:
```
  base-10: 9
  base-5: 14
  base-4: 21
  base-3: 100
```

`9(10)` in base-5 does not have binary representation. Again we know exactly what is next candidate to check - it's `100(5) = 25(10)`. It means we can skip now 15 numbers!

We repeat this process till we find a number which has representation in all base systems which interest us.

For 82000, values to check are:
```
2(10)
5(10)
9(10)
25(10)
64(10)
125(10)
256(10)
625(10)
1024(10)
3125(10)
4096(10)
15625(10)
16384(10)
16400(10) - we could skip only 5 numbers
19683(10)
78125(10) - we could skip 58441 numbers
81920(10)
82000(10)
```

Please notice: instead of checking all 82000 (actually 81996) numbers, we need to check only 18.

In Numberphile's video, it is mentioned They checked all numbers with 2000 digits long.
Using this algorithm, just in some hours, I checked all numbers (by possibility of skipping) up to 52792 decimal digits.

## Notes:

[Numberphile Youtube channel](https://www.youtube.com/channel/UCoxcjq-8xIDTYp3uz647V5A)

[Inspiration video](https://www.youtube.com/watch?v=LNS1fabDkeA)

[A258107 sequence](https://oeis.org/A258107)

Skipping from 19683(10) to 78125(10) in example above can be explained by:
```
19683(10) = 1112213(5)
```
next possible candidate in base 5 to check is: `10000000(5) = 78125(10)`
