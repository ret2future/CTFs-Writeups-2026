package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.LegoClicker.R;
import com.example.LegoClicker.model.LeaderboardEntry;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final class zf extends jl {
    public final ArrayList c;

    public zf(ArrayList arrayList) {
        this.c = arrayList;
    }

    @Override // defpackage.jl
    public final int a() {
        return this.c.size();
    }

    @Override // defpackage.jl
    public final void b(em emVar, int i) {
        yf yfVar = (yf) emVar;
        try {
            LeaderboardEntry leaderboardEntry = (LeaderboardEntry) this.c.get(i);
            if (leaderboardEntry == null) {
                return;
            }
            TextView textView = yfVar.u;
            if (textView != null) {
                Locale locale = Locale.US;
                textView.setText("#" + (i + 1));
            }
            TextView textView2 = yfVar.v;
            if (textView2 != null) {
                String str = leaderboardEntry.username;
                if (str == null) {
                    str = "BrickBuilder";
                }
                textView2.setText(str);
            }
            TextView textView3 = yfVar.w;
            if (textView3 != null) {
                textView3.setText(d.s(leaderboardEntry.totalBricksEarned).concat(" bricks"));
            }
            LinearLayout linearLayout = yfVar.t;
            int i2 = 0;
            if (linearLayout != null) {
                linearLayout.setBackgroundColor(leaderboardEntry.isPlayer ? -3152 : 0);
            }
            TextView textView4 = yfVar.x;
            if (textView4 != null) {
                if (!leaderboardEntry.isPlayer) {
                    i2 = 8;
                }
                textView4.setVisibility(i2);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // defpackage.jl
    public final em c(ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_leaderboard, viewGroup, false);
        yf yfVar = new yf(viewInflate);
        yfVar.t = (LinearLayout) viewInflate.findViewById(R.id.row_root);
        yfVar.u = (TextView) viewInflate.findViewById(R.id.tv_rank);
        yfVar.v = (TextView) viewInflate.findViewById(R.id.tv_username);
        yfVar.w = (TextView) viewInflate.findViewById(R.id.tv_bricks);
        yfVar.x = (TextView) viewInflate.findViewById(R.id.tv_you_badge);
        return yfVar;
    }
}
