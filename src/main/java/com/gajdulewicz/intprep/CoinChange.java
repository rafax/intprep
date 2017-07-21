package com.gajdulewicz.intprep;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by gajduler on 7/21/17.
 */
public class CoinChange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] coins = new int[m];
        for (int coins_i = 0; coins_i < m; coins_i++) {
            coins[coins_i] = in.nextInt();
        }
        System.out.println(giveChange(n, Arrays.stream(coins).sorted().boxed().collect(Collectors.toList())));
    }

    public static long giveChange(int n, List<Integer> coins) {
        return giveChangeHelper(n, coins, new HashMap<>());
    }

    private static long giveChangeHelper(int n, List<Integer> coins, HashMap<MemoKey, Long> memo) {
        if (n <= 0) return 0;
        MemoKey mk = new MemoKey(n, coins);
        if (!memo.containsKey(mk)) {
            long total = 0;
            for (Integer coin : coins) {
                total += giveChangeHelper(n - coin, coins.stream().filter(c -> c <= coin).collect(Collectors.toList()),memo);
            }
            memo.put(mk, total + (coins.contains(n) ? 1 : 0));
        }
        return memo.get(mk);
    }


    static class MemoKey {
        private final int n;
        private final List<Integer> coins;

        public MemoKey(int n, List<Integer> coins) {
            this.n = n;
            this.coins = coins;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MemoKey memoKey = (MemoKey) o;

            if (n != memoKey.n) return false;
            return coins.equals(memoKey.coins);
        }

        @Override
        public int hashCode() {
            int result = n;
            result = 31 * result + coins.hashCode();
            return result;
        }
    }
}
