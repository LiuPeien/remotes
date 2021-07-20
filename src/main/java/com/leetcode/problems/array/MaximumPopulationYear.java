package com.leetcode.problems.array;

/*
The population of some year x is the number of people alive during that year. The ith person is counted in year x's population if x is in the inclusive range [birthi, deathi - 1]. Note that the person is not counted in the year that they die.
Return the earliest year with the maximum population.

Example 1:

Input: logs = [[1993,1999],[2000,2010]]
Output: 1993
Explanation: The maximum population is 1, and 1993 is the earliest year with this population.
Example 2:

Input: logs = [[1950,1961],[1960,1971],[1970,1981]]
Output: 1960
Explanation:
The maximum population is 2, and it had happened in years 1960 and 1970.
The earlier year between them is 1960.

Constraints:

1 <= logs.length <= 100
1950 <= birthi < deathi <= 2050
 */

public class MaximumPopulationYear {
    public static int maximumPopulation(int[][] logs) {
        int[] population = new int[101];
        for (int[] log: logs) {
            population[log[0]-1950] += 1;
            population[log[1]-1950] -= 1;
        }

        int maximum_population = population[0];
        int earliest_year = 0;

        for (int i = 1; i < population.length; i++) {
            population[i] += population[i-1];
            if (population[i] > maximum_population) {
                maximum_population = population[i];
                earliest_year = i;
            }
        }

        return earliest_year + 1950;
    }

    public static void main(String[] args) {
        int[][] testcase = {{1950, 1961}, {1950, 1980}};
        System.out.println(MaximumPopulationYear.maximumPopulation(testcase));
    }
}
