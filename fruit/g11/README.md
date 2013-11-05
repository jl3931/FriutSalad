Distribution Strategy

Current Implementation
For each bowl passed- the estimated distribution for a bowl is calculated
This is currently calculated very dumbly. Each time a bowl is passed, the percentage of all the fruit of a certain type/ the total fruit of all the passed bowls determines the expected
percentage of each fruit in the LARGE BOWL distribution. To get the distribution of the LARGE BOWL,
this number is multipled by the amount of fruit in each bowl * players - so total fruit.

Then once the distribution of the LARGE BOWL is determined, it is divided by the number of bowls to get a general expectancy of how much of each fruit should appear in each bowl. I'm aware that this seems like double counting, feel free to fix.

Once the distribution is determined on a per bowl basis it is compared with the current bowl using Chi Square distribution assuming normal distribution.  If the Chi Square distribution is small - this is good because out estimate is probably close the the actual distribution. Therefor we calculate an expected value and then decide if our value is higher or lower. If higher, we take the bowl.

The Chi Square distribution has 11 degrees of freedom for this problem. Which is the number of variables -1. I only needed to put in one row of the table and I did this as a hash. the Chi Square decent estimate is based on the lowest expected probability we will accept from this table.

Needed Fixes:
1) Bug checking
2) New distribution strategy
3) A way of updating the distribution until it has a chi square distribution that is expected (KRISTA: I am confused as to what this means)

Krista's notes:
From what I understand here, what is happening is we are performing a CHI-SQUARED TEST. In this test, we compare the expected (initial) distribution of the serving bowl to the current bowl we have. Then, we use the chi-squared hypothesis test to see whether we accept or reject the null hypothesis, which is that the current bowl is a good fit for the expected (initial) distribution of the serving bowl.
I am a little bit worried about the fact that the chi-squared distribution assumes that the random variables are normally distributed. I am not 100% sure of what these random variables are. From what I understand, in the way they are used here they are the percentages of the different fruits in the initial distribution. I would argue that these percentages are not normally distributed--uniform might be a better choice, so we should investigate if there is an alternative where the random variables are assumed to have uniform distributions. 
This strategy also needs a backup plan in the event that we reject the null hypothesis; as the code originally was, we just will not take in this circumstance no matter what.
 
