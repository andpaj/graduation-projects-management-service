package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
public class TagEntity {

    @Id
    @GeneratedValue
    private long id;
    @Column()
    private String tagId;
    @Column()
    private String tagName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "tags")
    private Set<ThesisEntity> thesis = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<ThesisEntity> getThesis() {
        return thesis;
    }

    public void setThesis(Set<ThesisEntity> thesis) {
        this.thesis = thesis;
    }
}
