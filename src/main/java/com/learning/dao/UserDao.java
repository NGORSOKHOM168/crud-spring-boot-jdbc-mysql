package com.learning.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.learning.dto.ResponseObject;
import com.learning.dto.ResultMessage;
import com.learning.dto.UserRequest;
import com.learning.dto.UserRequestById;
import com.learning.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class UserDao {
	
	@Autowired 
	private DataSource dataSource;
	
	public ResultMessage addNewUser(UserRequest request){		
		 try (  
			 Connection con = dataSource.getConnection();	 
             CallableStatement stmt = con.prepareCall("{CALL usp_TBLUSERS_Insert(?,?,?)}");
         ) {
			 stmt.setString("p_USERNAME",   request.username());
			 stmt.setString("p_PASSWORD",   request.password());
			 stmt.setString("p_IS_ENABLED", request.isEnabled());
			 stmt.execute();
	        } catch (SQLException e) {   
	            log.error(" Error Inside addNewUser, {}, {}", e.getMessage(), request);
	            return ResultMessage.builder()
	    				.isSuccessFul(false)
	    				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	    				.message("Failed to save data!")
	    				.build();    
	        }		
		log.info("Inside addNewUser: Inserted successfully!");
		return ResultMessage.builder()
				.isSuccessFul(true)
				.code(HttpStatus.OK.value())
				.message("Inserted successfully")
				.build();
		
	}
	
	public ResponseObject<?> getUserList(){		
		ResultMessage resultMessage;
	    List<User> users = new ArrayList<>();
		try (  
			 Connection con = dataSource.getConnection();	 
             CallableStatement cstmt = con.prepareCall("{CALL usp_TBLUSERS_Select()}");
        ) {
			//Retrieving the result
		    ResultSet rs = cstmt.executeQuery();
		    while(rs.next()) {
		    	 User user = User.builder()
		    			 .userId(rs.getString("USER_ID"))
		    			 .username(rs.getString("USERNAME"))
		    			 .password(rs.getString("PASSWORD"))
		    			 .isEnabled(rs.getString("IS_ENABLED"))
		    			 .build();
		         users.add(user);
		    }
		    cstmt.close();
        } catch (SQLException e) {   
            log.error(" Error Inside getUserList, {}", e.getMessage());
            resultMessage = ResultMessage.builder()
    				.isSuccessFul(false)
    				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
    				.message("Failed to save data!")
    				.build();    
        }		
		 
	    log.info("Inside getUserList: successfully");
		resultMessage = ResultMessage.builder()
				.isSuccessFul(true)
				.code(HttpStatus.OK.value())
				.message("Successful")
				.build();
		
		return ResponseObject.builder().resultMessage(resultMessage).payload(users).build();				
		
	}

	public ResultMessage updateUser(UserRequest request){		
		 try (  
			 Connection con = dataSource.getConnection();	 
             CallableStatement stmt = con.prepareCall("{CALL usp_TBLUSERS_Update(?,?,?,?)}");
         ) {
			 stmt.setString("p_USER_ID",    request.userId());
			 stmt.setString("p_USERNAME",   request.username());
			 stmt.setString("p_PASSWORD",   request.password());
			 stmt.setString("p_IS_ENABLED", request.isEnabled());
			 stmt.execute();
	        } catch (SQLException e) {   
	            log.error(" Error Inside updateUser, {}, {}", e.getMessage(), request);
	            return ResultMessage.builder()
	    				.isSuccessFul(false)
	    				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	    				.message("Failed to save data!")
	    				.build();    
	        }		
		log.info("Inside updateUser: Updated successfully!");
		return ResultMessage.builder()
				.isSuccessFul(true)
				.code(HttpStatus.OK.value())
				.message("Updated successfully")
				.build();
		
	}

	public ResultMessage deleteUser(UserRequestById requestById){		
		 try (  
			 Connection con = dataSource.getConnection();	 
             CallableStatement stmt = con.prepareCall("{CALL usp_TBLUSERS_Delete(?)}");
         ) {
			 stmt.setString("p_USER_ID",   requestById.userId());
			 stmt.execute();
	        } catch (SQLException e) {   
	            log.error(" Error Inside deleteUser, {}, {}", e.getMessage(), requestById);
	            return ResultMessage.builder()
	    				.isSuccessFul(false)
	    				.code(HttpStatus.INTERNAL_SERVER_ERROR.value())
	    				.message("Failed to save data!")
	    				.build();    
	        }		
		log.info("Inside deleteUser: deleted successfully!");
		return ResultMessage.builder()
				.isSuccessFul(true)
				.code(HttpStatus.OK.value())
				.message("Deleted successfully")
				.build();
		
	}
}
