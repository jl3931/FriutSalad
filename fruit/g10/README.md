Distribution Strategy

Current Implementation
For each bowl passed- the estimated distribution for a bowl is calculated
This is currently calculated very dumbly. Each time a bowl is passed, the percentage of
all the fruit of a certain type/ the total fruit of all the passed bowls determines the expected
percentage of each fruit in the LARGE BOWL distribution. To get the distribution of the LARGE BOWL,
this number is multipled by the amount of fruit in each bowl * players - so total fruit.

Then once the distribution of the LARGE BOWL is determined, it is divided by the number of bowls to get a general expectancy of how much of each fruit should appear in each bowl. I'm aware that this seems like double counting, feel free to fix.

Once the distribution is determined on a per bowl basis it is compared with the current bowl using Chi Square distribution assuming normal distribution.  If the Chi Square distribution is small - this is good because out estimate is probably close the the actual distribution. Therefor we calculate an expected value and then decide if our value is higher or lower. If higher, we take the bowl.

The Chi Square distribution has 11 degrees of freedom for this problem. Which is the number of variables -1. I only needed to put in one row of the table and I did this as a hash. the Chi Square decent estimate is based on the lowest expected probability we will accept from this table.

Needed Fixes:
1) Bug checking
2) New distribution strategy
3) A way of updating the distribution until it has a chi square distribution that is expected