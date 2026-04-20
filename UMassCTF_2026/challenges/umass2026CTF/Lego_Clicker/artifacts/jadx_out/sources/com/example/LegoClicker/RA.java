package com.example.LegoClicker;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.LegoClicker.RA;
import com.example.LegoClicker.model.LeaderboardEntry;
import defpackage.c2;
import defpackage.g2;
import defpackage.l2;
import defpackage.pn;
import defpackage.uh;
import defpackage.zf;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public class RA extends l2 {
    public static final /* synthetic */ int a = 0;

    public static String d(int... iArr) {
        char[] cArr = new char[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            cArr[i] = (char) (32 + iArr[i]);
        }
        return new String(cArr);
    }

    public final void e(final String str) {
        try {
            String strD = d(35, 79, 80, 89);
            String strD2 = d(35, 76, 79, 83, 69);
            final String strD3 = d(98, 69, 87, 65, 98, 68);
            g2 g2Var = new g2(this);
            c2 c2Var = (c2) g2Var.b;
            c2Var.d = getString(R.string.leaderboard_reward_title);
            c2Var.f = getString(R.string.leaderboard_reward_msg) + str;
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: gl
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    RA ra = this.a;
                    String str2 = strD3;
                    String str3 = str;
                    int i2 = RA.a;
                    try {
                        ClipboardManager clipboardManager = (ClipboardManager) ra.getSystemService("clipboard");
                        if (clipboardManager != null) {
                            clipboardManager.setPrimaryClip(ClipData.newPlainText(str2, str3));
                        }
                    } catch (Throwable unused) {
                    }
                }
            };
            c2Var.g = strD;
            c2Var.h = onClickListener;
            c2Var.i = strD2;
            c2Var.j = null;
            c2Var.k = false;
            g2Var.a().show();
        } catch (Throwable unused) {
        }
    }

    @Override // defpackage.vc, androidx.activity.b, defpackage.q7, android.app.Activity
    public final void onCreate(Bundle bundle) {
        ArrayList arrayList;
        super.onCreate(bundle);
        try {
            setContentView(R.layout.activity_leaderboard);
            try {
                findViewById(R.id.btn_back).setOnClickListener(new uh(this, 3));
            } catch (Throwable unused) {
            }
            try {
                arrayList = new pn(this).a();
            } catch (Throwable unused2) {
                arrayList = new ArrayList();
            }
            try {
                TextView textView = (TextView) findViewById(R.id.tv_empty);
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_leaderboard);
                if (arrayList.isEmpty()) {
                    textView.setVisibility(0);
                    recyclerView.setVisibility(8);
                } else {
                    textView.setVisibility(8);
                    recyclerView.setVisibility(0);
                    recyclerView.setLayoutManager(new LinearLayoutManager());
                    recyclerView.setAdapter(new zf(arrayList));
                }
            } catch (Throwable unused3) {
            }
            try {
                if (arrayList.isEmpty() || !((LeaderboardEntry) arrayList.get(0)).isPlayer) {
                    return;
                }
                long j = (long) ((LeaderboardEntry) arrayList.get(0)).totalBricksEarned;
                try {
                    String strValidateBrickToken = SessionValidator.validateBrickToken(j, j);
                    if (strValidateBrickToken == null) {
                        return;
                    }
                    if (strValidateBrickToken.isEmpty()) {
                        return;
                    }
                } catch (Throwable unused4) {
                }
                String strA = SessionValidator.a(j, j);
                if (strA == null || strA.isEmpty()) {
                    return;
                }
                e(strA);
            } catch (Throwable unused5) {
            }
        } catch (Throwable unused6) {
            finish();
        }
    }
}
