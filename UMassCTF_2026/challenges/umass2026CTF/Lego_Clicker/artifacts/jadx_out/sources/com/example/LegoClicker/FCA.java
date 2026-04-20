package com.example.LegoClicker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.example.LegoClicker.FCA;
import defpackage.c2;
import defpackage.g2;
import defpackage.l2;
import defpackage.m0;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class FCA extends l2 {
    public static final int[] a = {179, 127, 193, 42, 94, 147, 68, 216};

    static {
        try {
            System.loadLibrary(d(86, 95, 93, 85, 89, 85, 72, 95));
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    private static native String a();

    private static native String b(long j);

    private static native boolean c(long j);

    public static String d(int... iArr) {
        char[] cArr = new char[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            cArr[i] = (char) (iArr[i] ^ 58);
        }
        return new String(cArr);
    }

    @Override // defpackage.vc, androidx.activity.b, defpackage.q7, android.app.Activity
    public final void onCreate(Bundle bundle) {
        final String strB;
        super.onCreate(bundle);
        long longExtra = getIntent().getLongExtra(d(73, 89, 85, 72, 95), 0L);
        long j = longExtra;
        for (int i = 0; i < 8; i++) {
            long j2 = j ^ (((long) a[i]) << ((int) (31 & j)));
            j = (j2 ^ (j2 >>> 27)) * (-7723592293110705685L);
        }
        if (j == -2401050962867404578L) {
            finish();
            return;
        }
        try {
            strB = c(longExtra) ? b(longExtra) : a();
        } catch (Throwable unused) {
            strB = "";
        }
        if (strB == null || strB.isEmpty()) {
            finish();
            return;
        }
        try {
            g2 g2Var = new g2(this);
            c2 c2Var = (c2) g2Var.b;
            c2Var.d = d(105, 89, 85, 72, 95, 26, 108, 95, 72, 83, 92, 83, 89, 91, 78, 83, 85, 84);
            c2Var.f = strB;
            String strD = d(121, 85, 74, 67);
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: tb
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    FCA fca = this.a;
                    String str = strB;
                    int[] iArr = FCA.a;
                    try {
                        ClipboardManager clipboardManager = (ClipboardManager) fca.getSystemService("clipboard");
                        if (clipboardManager != null) {
                            clipboardManager.setPrimaryClip(ClipData.newPlainText(FCA.d(89, 91, 74, 78, 79, 72, 95), str));
                        }
                    } catch (Throwable unused2) {
                    }
                }
            };
            c2Var.g = strD;
            c2Var.h = onClickListener;
            String strD2 = d(121, 91, 84, 89, 95, 86);
            m0 m0Var = new m0(this, 1);
            c2Var.i = strD2;
            c2Var.j = m0Var;
            c2Var.k = false;
            g2Var.a().show();
        } catch (Throwable unused2) {
            finish();
        }
    }
}
