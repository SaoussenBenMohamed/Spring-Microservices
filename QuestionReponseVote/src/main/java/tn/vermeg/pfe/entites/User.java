package tn.vermeg.pfe.entites;

import java.util.Date;

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
public class User {
	 @Id
	    private String id;

	    private String username;

	    private String fullName;

	    private String email;

	    private Date registrationDate;

	    private String password;

	    @DBRef
	    private Question question;

		public boolean add(Question question) {
			// TODO Auto-generated method stub
			return false;
		}
}
