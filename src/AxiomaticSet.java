import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AxiomaticSet {
    private final Set<AxiomaticSet> container;
    public static final AxiomaticSet EMPTY_SET;

    static {
        EMPTY_SET = new AxiomaticSet();
    }

    private AxiomaticSet() {
        container = new HashSet<>();
    }

    public static AxiomaticSet singleton(AxiomaticSet set) {
        AxiomaticSet wrap = new AxiomaticSet();
        wrap.container.add(set);

        return wrap;
    }

    public static AxiomaticSet pair(AxiomaticSet setA, AxiomaticSet setB) {
        AxiomaticSet set = new AxiomaticSet();
        set.container.add(setA);
        set.container.add(setB);

        return set;
    }

    public static AxiomaticSet union(AxiomaticSet setA, AxiomaticSet setB) {
        AxiomaticSet set = new AxiomaticSet();
        set.container.addAll(setA.container);
        set.container.addAll(setB.container);

        return set;
    }

    public static AxiomaticSet intersection(AxiomaticSet setA, AxiomaticSet setB) {
        AxiomaticSet set = new AxiomaticSet();
        Iterator<AxiomaticSet> iterator = setA.container.iterator();

        AxiomaticSet element;
        while (iterator.hasNext()) {
            element = iterator.next();

            if (setB.container.contains(element))
                set.container.add(element);
        }

        return set;
    }

    public AxiomaticSet successor() {
        return union(this, singleton(this));
    }

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
