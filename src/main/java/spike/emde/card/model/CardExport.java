package spike.emde.card.model;

import spike.emde.utils.MyConstant;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CardExport {
    private final String id;
    private final String brief;
    private final String content;
    private final String assignedTo;
    private final String dueDate;
    private final String size;
    /**
     * This scheme should be the same order with toList method.
     */
    private static final List<String> scheme = Arrays.asList(
            "id", "brief", "content", "assignedTo", "dueDate", "size"
    );

    public CardExport(Builder builder) {
        this.id = builder.id;
        this.brief = builder.brief;
        this.content = builder.content;
        this.assignedTo = builder.assignedTo;
        this.dueDate = builder.dueDate;
        this.size = builder.size;
    }

    public static class Builder {
        private String id = "";
        private String brief = "";
        private String content = "";
        private String assignedTo = "";
        private String dueDate = "";
        private String size = "";

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setBrief(String brief) {
            this.brief = brief;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setAssignedTo(List<String> assignedTo) {
            this.assignedTo = (assignedTo == null) ? MyConstant.empty :
                    String.join(", ", assignedTo.toArray(new String[assignedTo.size()]));
            return this;
        }

        public Builder setDueDate(LocalDate dueDate) {
            this.dueDate = (dueDate == null) ? MyConstant.empty : dueDate.toString();
            return this;
        }

        public Builder setSize(String size) {
            this.size = size;
            return this;
        }

        public CardExport build() {
            return new CardExport(this);
        }
    }

    @Override
    public String toString() {
        Map<String, String> map = this.toMap();
        return scheme.stream()
                .filter(string -> !map.get(string).isEmpty())
                .map(string -> string+ ":" + map.get(string))
                .collect(Collectors.joining("\n"));
    }

    public List<String> toList() {
        List<String> arrayList = new ArrayList();
        arrayList.add(id);
        arrayList.add(brief);
        arrayList.add(content);
        arrayList.add(assignedTo);
        arrayList.add(dueDate);
        arrayList.add(size);
        return arrayList;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        ListIterator<String> schemeIterator = scheme.listIterator();
        ListIterator<String> cardExportIterator = this.toList().listIterator();
        while (schemeIterator.hasNext() && cardExportIterator.hasNext()) {
            map.put(schemeIterator.next(), cardExportIterator.next());
        }
        return map;
    }
}
