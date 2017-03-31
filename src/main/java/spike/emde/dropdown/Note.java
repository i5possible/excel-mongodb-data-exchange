package spike.emde.dropdown;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.OptionalInt;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

public class Note {
    private String name;
    private List<Note> children = newArrayList();

    private int level;

    public Note(String name, List<Note> children, int level) {
        this.name = name;
        this.children = children;
        this.level = level;
    }

    public Note(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getChildren() {
        return children;
    }

    public void setChildren(List<Note> children) {
        this.children = children;
    }

    public void addChildren(Note child) {
        this.children.add(child);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isTopLevel() {
        return this.level == 0;
    }

    public int count() {
        if (StringUtils.isEmpty(name)) {
            return 0;
        }
        if (CollectionUtils.isEmpty(children)) {
            return 1;
        } else {
            return children.stream().mapToInt(Note::count).sum();
        }
    }

    public int getTotalLevel() {
        if (CollectionUtils.isEmpty(children)) {
            return level;
        } else {
            OptionalInt max = children.stream().mapToInt(Note::getTotalLevel).max();
            if (max.isPresent()) {
                return max.getAsInt();
            } else {
                return level;
            }
        }
    }

    public boolean isLeaf() {
        return CollectionUtils.isEmpty(children);
    }
}
