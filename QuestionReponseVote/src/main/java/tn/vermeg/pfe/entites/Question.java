package tn.vermeg.pfe.entites;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Question {
	 @Id
	    private String id;

	    private String header;

	    private String content;

	    private String imageId;

	    private Date createdDate;

	    private Date modifiedDate;
	    @DBRef
	    private User creator;
	    @DBRef
	    private List<Answer> answers;
}
