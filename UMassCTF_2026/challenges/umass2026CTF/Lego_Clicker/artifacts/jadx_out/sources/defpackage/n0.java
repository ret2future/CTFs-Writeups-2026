package defpackage;

import android.content.Intent;
import android.os.Bundle;
import com.example.LegoClicker.FlagEngine;
import com.example.LegoClicker.SessionValidator;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public abstract class n0 extends l2 {
    public static final int[][] b = {new int[]{124, 103, 91, 124, 91, 124, 81, 91, 124}, new int[]{124, 91, 124, 85, 96, 83, 124, 91, 124}, new int[]{124, 91, 124, 124, 103, 86, 91, 124, 91}};
    public static final long[] c = {-881947276258312191L, 2197031332398235650L, 3071330138412023811L, 6578700423459241988L};
    public final /* synthetic */ int a;

    public /* synthetic */ n0(int i) {
        this.a = i;
    }

    public static String d(int... iArr) {
        char[] cArr = new char[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            cArr[i] = (char) (iArr[i] ^ 58);
        }
        return new String(cArr);
    }

    @Override // defpackage.vc, androidx.activity.b, defpackage.q7, android.app.Activity
    public final void onCreate(Bundle bundle) {
        int i = 0;
        switch (this.a) {
            case 0:
                super.onCreate(bundle);
                int iB = FlagEngine.b(getIntent().getDoubleExtra(d(93, 88, 83, 92, 93, 83, 89, 83, 84, 91), 0.0d));
                int[] iArr = b[(iB - 1) % 3];
                char[] cArr = new char[iArr.length];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    cArr[i2] = (char) (iArr[i2] ^ 58);
                }
                String str = new String(cArr);
                if (str.isEmpty()) {
                    str = null;
                }
                if (str == null) {
                    finish();
                } else {
                    String strC = FlagEngine.c(iB);
                    try {
                        g2 g2Var = new g2(this);
                        c2 c2Var = (c2) g2Var.b;
                        c2Var.d = d(123, 84, 85, 83, 94, 84, 86, 85, 94, 93, 93);
                        c2Var.f = strC;
                        String strD = d(91, 68);
                        m0 m0Var = new m0(this, i);
                        c2Var.g = strD;
                        c2Var.h = m0Var;
                        c2Var.k = false;
                        g2Var.a().show();
                    } catch (Throwable unused) {
                        finish();
                        return;
                    }
                }
                break;
            default:
                super.onCreate(bundle);
                Intent intent = getIntent();
                int[] iArr2 = {83, 99, 111, 114, 101};
                char[] cArr2 = new char[5];
                for (int i3 = 0; i3 < 5; i3++) {
                    cArr2[i3] = (char) (32 + iArr2[i3]);
                }
                long longExtra = intent.getLongExtra(new String(cArr2), 0L);
                int i4 = SessionValidator.a ? 3 : 0;
                if ((i4 | (~i4)) != 0) {
                    long j = 0;
                    for (int i5 = 0; i5 < 4; i5++) {
                        j ^= c[i5] ^ (longExtra << (i5 & 7));
                    }
                    if (longExtra >= 1.0E9d) {
                        long j2 = (j ^ longExtra) ^ ((long) ((int) (4294967295L & longExtra)));
                        long j3 = (j2 ^ (j2 >>> 30)) * (-4658895280553007687L);
                        long j4 = (j3 ^ (j3 >>> 27)) * (-7723592293110705685L);
                        long j5 = j4 ^ (j4 >>> 31);
                        long j6 = (j5 ^ (j5 >>> 30)) * (-4658895280553007687L);
                        long j7 = (j6 ^ (j6 >>> 27)) * (-7723592293110705685L);
                        long j8 = j7 ^ (j7 >>> 31);
                        if ((1 & j8) == 0 && ((~j8) | j8) != 0) {
                            try {
                                String strA = SessionValidator.a(longExtra, j8);
                                if (strA != null && !strA.isEmpty() && strA.startsWith("UMASS{") && strA.endsWith("}") && strA.length() - 7 >= 3) {
                                    Intent intent2 = getIntent();
                                    int[] iArr3 = {82, 101, 115, 117, 108, 116};
                                    char[] cArr3 = new char[6];
                                    while (i < 6) {
                                        cArr3[i] = (char) (iArr3[i] + 32);
                                        i++;
                                    }
                                    setResult(-1, intent2.putExtra(new String(cArr3), strA));
                                }
                                break;
                            } catch (Throwable unused2) {
                            }
                        }
                        finish();
                    } else {
                        finish();
                    }
                } else {
                    finish();
                }
                break;
        }
    }
}
