import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AxiomaticSet {
    private final List<AxiomaticSet> container;
    public static final AxiomaticSet EMPTY_SET;

    static {
        EMPTY_SET = new AxiomaticSet();
    }

    private AxiomaticSet() {
        container = new ArrayList<>();
    }

    public static AxiomaticSet singleton(AxiomaticSet set) {
        AxiomaticSet wrap = new AxiomaticSet();
        wrap.container.add(set);

        return wrap;
    }

    public static AxiomaticSet pair(AxiomaticSet setA, AxiomaticSet setB) {
        AxiomaticSet set = new AxiomaticSet();
        set.container.add(setA);

        if (!setA.equals(setB))
            set.container.add(setB);

        return set;
    }

    public static AxiomaticSet union(AxiomaticSet setA, AxiomaticSet setB) {
        AxiomaticSet set = new AxiomaticSet();
        set.container.addAll(setA.container);

        for (AxiomaticSet element : setB.container) {
            if (!setA.contains(element))
                set.container.add(element);
        }

        return set;
    }

    public static AxiomaticSet intersection(AxiomaticSet setA, AxiomaticSet setB) {
        AxiomaticSet set = new AxiomaticSet();

        for (AxiomaticSet element : setA.container) {
            if (setB.contains(element))
                set.container.add(element);
        }

        return set;
    }

    public AxiomaticSet successor() {
        return union(this, singleton(this));
    }

    @Nullable
    public static AxiomaticSet setTheoreticN(int n) {
        if (n < 0)
            return null;

        AxiomaticSet set = EMPTY_SET;
        for (int i = 0; i < n; i++)
            set = set.successor();

        return set;
    }

    public boolean contains(AxiomaticSet set) {
        return container.contains(set);
    }

    public boolean isSubset(AxiomaticSet set) {
        return container.containsAll(set.container);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (!(o instanceof AxiomaticSet anotherSet))
            return false;

        return isSubset(anotherSet) && anotherSet.isSubset(this);
    }

    @Override
    public String toString() {
        if (container.isEmpty())
            return "\u2205";

        StringBuilder builder = new StringBuilder();
        builder.append('{');

        Iterator<AxiomaticSet> iterator = container.iterator();
        while (iterator.hasNext()) {
            builder.append(iterator.next().toString());

            if (iterator.hasNext())
                builder.append(", ");
            else
                builder.append('}');
        }

        return builder.toString();
    }
}
