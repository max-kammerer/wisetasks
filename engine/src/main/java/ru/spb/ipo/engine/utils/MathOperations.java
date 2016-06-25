/*
 * This file is part of Wisetasks
 *
 * Copyright (C) 2006-2008, 2012  Michael Bogdanov
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ru.spb.ipo.engine.utils;

import java.math.BigInteger;

public class MathOperations {

    public static BigInteger combination (long n, long k) {

        if ((k == 0) || ( k == n)) return BigInteger.valueOf(1);
        if (k > n) return BigInteger.valueOf(0);
        if ((k < 0)  || (n <=0)) return BigInteger.valueOf(0);

        long a;
        long b;
        if (k > n - k) {
            a = k;
            b = n - k ;
        } else {
            a = n - k;
            b = k;
        }

        BigInteger bi = BigInteger.valueOf(a + 1);
        for (long i = a + 2; i <= n; i++) {
            bi = bi.multiply(BigInteger.valueOf(i));
        }

        for (long i = 2; i <= b; i++) {
            bi = bi.divide(BigInteger.valueOf(i));
        }
        return bi;
    }

    public static BigInteger layout (long n, long k) {
        if (n < k ) return BigInteger.valueOf(0);
        if ((n <= 0) || (k <= 0)) return BigInteger.valueOf(0);
        BigInteger bi;
        if (k == n)  bi = BigInteger.valueOf(1);
        else  bi = BigInteger.valueOf(n - k + 1);

        for (long i = n - k + 2; i <= n; i++) {
            bi = bi.multiply(BigInteger.valueOf(i));
        }

        return bi;
    }

    public static BigInteger factorial(long n) {
        return layout(n, n);
    }
}
