package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name = "likes")
@NamedQueries({
    @NamedQuery(
            name="getAllLikes",
            query="SELECT l FROM Like AS l ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name="getLikesCount",
            query="SELECT COUNT(l) FROM Like as l WHERE l.report_id = :report_id"
            ),

       @NamedQuery(
            name="getReportId",
            query="SELECT COUNT(l) FROM Like AS l WHERE l.report_id = :report_id AND l.employee_id = :employee_id"
            ),
       @NamedQuery(
            name="getLike",
            query="SELECT l FROM Like AS l WHERE l.report_id = :report_id AND l.employee_id = :employee_id"
               )

})

@Entity
public class Like {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="employee_id", nullable = false)
    private Integer employee_id;

    @Column(name="report_id", nullable = false)
    private Integer report_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(Integer report_id) {
        this.report_id = report_id;
    }


}