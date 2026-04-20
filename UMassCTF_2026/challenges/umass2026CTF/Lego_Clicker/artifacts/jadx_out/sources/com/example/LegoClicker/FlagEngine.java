package com.example.LegoClicker;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class FlagEngine {
    private static final long[] kTierBlob = {68576273936416768L, 33514703552512L, 167772160000L, 1048576000, 0};
    private static final byte[] kLabelEnc = {30, 59, 39, 52, 37, 12, 25, 37, 52, 43, 62, 55, 12, 37, 53, 37, 59, 58, 47, 12};

    private FlagEngine() {
    }

    public static boolean a(double d, double d2) {
        return d >= d2;
    }

    public static int b(double d) {
        if (d >= 1000000.0d) {
            return 5;
        }
        if (d >= 100000.0d) {
            return 4;
        }
        if (d >= 10000.0d) {
            return 3;
        }
        return d >= 1000.0d ? 2 : 1;
    }

    public static String c(int i) {
        return i != 2 ? i != 3 ? i != 4 ? i != 5 ? "NOVICE" : "LEGEND" : "MASTER" : "EXPERT" : "BUILDER";
    }

    public static long d(double d) {
        long jDoubleToRawLongBits = Double.doubleToRawLongBits(d);
        long j = jDoubleToRawLongBits ^ kTierBlob[(int) (4 & jDoubleToRawLongBits)];
        long j2 = (j ^ (j >>> 30)) * (-4658895280553007687L);
        return j2 ^ (j2 >>> 31);
    }

    public static String e(int i) {
        byte[] bArr = new byte[kLabelEnc.length];
        int i2 = 0;
        while (true) {
            byte[] bArr2 = kLabelEnc;
            if (i2 >= bArr2.length) {
                return new String(bArr);
            }
            bArr[i2] = (byte) (bArr2[i2] ^ (i + i2));
            i2++;
        }
    }

    public static boolean f(String str) {
        if (str != null && str.length() >= 8) {
            int iCharAt = 0;
            for (int i = 0; i < str.length(); i++) {
                iCharAt ^= str.charAt(i) << (i & 7);
            }
            if ((iCharAt & 255) == 85) {
                return true;
            }
        }
        return false;
    }
}
