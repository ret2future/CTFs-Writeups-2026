package defpackage;

import com.example.LegoClicker.model.LeaderboardEntry;
import java.util.function.Predicate;

/* JADX INFO: compiled from: r8-map-id-51b54c9e59b63e0fb0abe04daff984b4fb85d07ef3fcb7973595bce215c10515 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class on implements Predicate {
    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return ((LeaderboardEntry) obj).isPlayer;
    }
}
