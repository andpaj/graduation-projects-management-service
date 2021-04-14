package ee.taltech.graduation.thesesmanagementservicebackend.thesesmanagementservicebackend.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Deprecated
@Embeddable
public class UserGroupPK implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "GROUP_ID")
    private Long groupId;

}
